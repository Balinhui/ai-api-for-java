package org.balinhui.Core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.balinhui.Core.JSON.Request;
import org.balinhui.Core.JSON.Response;
import org.balinhui.Core.JSON.Widgets.Message;
import org.balinhui.Core.JSON.Wrong;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class Call {
    private final String NONE = "StreamIsOpen";
    public static final String DONE = "[DONE]";
    @Setter
    private String API_URL;
    @Setter
    private String API_KEY;
    @Setter
    private Request request;
    private static final ObjectMapper mapper = new ObjectMapper();
    private final Store store = Store.getStore();
    @Setter
    private boolean ableStore = false;
    private List<Response> responseList;

    public Call() {
    }

    public Call(Request request) {
        this.request = request;
    }

    public Call(String API_URL, String API_KEY) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
    }

    public Call(String API_URL, String API_KEY, Request request) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
        this.request = request;
    }

    public boolean getAbleStore() {
        return ableStore;
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @param request 请求
     * @return 响应JSON的Java类
     */
    public List<Response> getResponseList(Request request) {
        if (this.request != request)
            this.request = request;
        return getResponseList();
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @return 响应JSON的Java类
     */
    public List<Response> getResponseList() {
        //检查必须的项
        if (API_KEY == null) {
            if ((API_KEY = System.getenv("API_KEY")) == null)
                throw new RuntimeException("API_KEY的值为null");
        }
        if (API_URL == null) throw new RuntimeException("API_URL的值为null");
        if (request == null) throw new RuntimeException("没有初始化Request");
        reviseURL();//为URL后加上特定分页

        String _send = "null";//发送的JSON
        String _return = "null";//返回的JSON
        try {
            if (ableStore) storeMessage();
            responseList = new ArrayList<>();
            _return = callApi(_send = mapper.writeValueAsString(request));
            if (!this.request.getStream()) {
                //将响应的JSON解析成相应的Java类
                Response response = mapper.readValue(_return, Response.class);
                responseList.add(response);
                if (ableStore) storeMessage(response.getChoices()[0].getMessage());
            }
            return responseList;
        } catch (Exception e) {
            try {
                Wrong wrongInfo = mapper.readValue(_return, Wrong.class);
                if (wrongInfo.getError() != null)
                    throw new RuntimeException(wrongInfo.getError().getMessage());
                throw new RuntimeException("API_URL错误。\nevent_id:" + wrongInfo.getEvent_id() +
                        ".\nerror_msg:" + wrongInfo.getError_msg());
            } catch (JsonProcessingException ex) {
                System.out.println("发送: " + _send);
                System.out.println("接收: " + _return);
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 在发送Message时整合所有历史Messages，并加入原Request中的Message
     */
    private void storeMessage() {
        for (Message message : request.getMessages()) {
            //如果对话库中的消息为0，则把系统提示也输入
            if (store.getSize() != 0) {
                //一般情况下，忽略系统提示
                if (!message.getRole().equals(Message.SYSTEM)) store.add(message);
            } else {
                store.add(request.getMessages());
                return;
            }
        }
        request.setMessages(store.getMessages());//从对话库中取得完整的对话
    }

    /**
     * 将响应的Message存起来
     * @param message 响应的Message
     */
    private void storeMessage(Message message) {
        //将返回的消息存入库中
        store.add(message);
        Logger.getLogger().log(store.toString());
    }

    /**
     * 向<code>API_URL</code>发送请求，请确保<code>API_URL</code>正确
     * @param requestBody 请求，已转成JSON格式
     * @return 响应的JSON
     * @throws Exception 响应失败抛出<code>Exception</code>
     */
    private String callApi(String requestBody) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        if (!this.request.getStream()) {
            //未打开Stream，普通发送.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } else {
            final RunState runState = new RunState(true);
            //已打开Stream，开一个线程
            Thread stream = new Thread(() -> {
                client.sendAsync(request, HttpResponse.BodyHandlers.fromLineSubscriber(
                        new Flow.Subscriber<>() {
                            private Flow.Subscription subscription;

                            @Override
                            public void onSubscribe(Flow.Subscription subscription) {
                                this.subscription = subscription;
                                subscription.request(1); // 请求第一个数据项
                            }

                            @Override
                            public void onNext(String item) {
                                // 处理每个数据块
                                if (!item.isEmpty() && !item.equals("data: [DONE]")) {
                                    try {
                                        responseList.add(mapper.readValue(item.substring(6).trim(), Response.class));
                                    } catch (JsonProcessingException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                subscription.request(1); // 请求下一个数据项
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                System.err.println("错误: " + throwable.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                StringBuilder sb = new StringBuilder();
                                for (Response response : responseList) {
                                    sb.append(response.getChoices()[0].getDelta().getContent());
                                }
                                Message message = new Message(Message.ASSISTANT, sb.toString());
                                if (ableStore) storeMessage(message);
                                responseList.add(new Response(DONE, "", 0,
                                        "", null, null, "", ""));
                                runState.state = false;
                            }
                        }));

                // 保持请求线程运行，等待响应
                while (runState.state) {
                    try {
                        Thread.sleep(3000); // 等待3秒
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            stream.start();
            return NONE;
        }
    }

    private void reviseURL() {
        if (!API_URL.endsWith("/chat/completions"))
            if (API_URL.endsWith("/")) API_URL = API_URL + "chat/completions";
            else API_URL = API_URL + "/chat/completions";
    }

    private static class RunState {
        private boolean state;
        public RunState(boolean state) {
            this.state = state;
        }
    }
}
