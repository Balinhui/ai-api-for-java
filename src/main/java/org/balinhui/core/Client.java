package org.balinhui.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.balinhui.json.Request;
import org.balinhui.json.Response;
import org.balinhui.json.widgets.Message;
import org.balinhui.json.Wrong;
import org.balinhui.util.Logger;
import org.balinhui.util.Store;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Flow;

public class Client {
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
    private ResponseList<Response> responseList;

    public Client() {
    }

    public Client(@NotNull Request request) {
        this.request = request;
    }

    public Client(@NotNull String API_URL,
                  @NotNull String API_KEY) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
    }

    public Client(@NotNull String API_URL,
                  @NotNull String API_KEY,
                  @NotNull Request request) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
        this.request = request;
    }

    public final boolean getAbleStore() {
        return ableStore;
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @return 响应JSON的Java类
     */
    public final ResponseList<Response> getResponseList() {
        return getResponseList(null);
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @param request 用户的请求
     * @return 响应JSON的Java类
     */
    public final ResponseList<Response> getResponseList(@NotNull Request request,
                                                        @Nullable OnAddAction<Response> onAddAction) {
        if (this.request != request)
            this.request = request;
        return getResponseList(onAddAction);
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @return 响应JSON的Java类
     */
    public final ResponseList<Response> getResponseList(@Nullable OnAddAction<Response> onAddAction) {
        checkConfig();//检查必须的项
        reviseURL();//为URL后加上特定分页

        String _send = "null";//发送的JSON
        String _return = "null";//返回的JSON

        if (ableStore) storeMessage();
        responseList = new ResponseList<>(onAddAction);

        try {
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
            } catch (Exception ex) {
                System.out.println("发送: " + _send);
                System.out.println("接收: " + _return);
                throw new RuntimeException(e);
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
            //已打开Stream流
            final RunState runState = new RunState(true);
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
                                    Response get = mapper.readValue(item.substring(6).trim(), Response.class);
                                    if (responseList.isEmpty() || responseList.getFirst().getId().equals(get.getId()))
                                        responseList.add(get);
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
                            if (ableStore) {
                                StringBuilder sb = new StringBuilder();
                                for (Response response : responseList) {
                                    sb.append(response.getChoices()[0].getDelta().getContent());
                                }
                                Message message = new Message(Message.ASSISTANT, sb.toString());
                                storeMessage(message);
                            }
                            runState.state = false;
                        }
                    }));

            // 保持请求线程运行，等待响应
            while (runState.state) {
                try {
                    Thread.sleep(900); // 等待900毫秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return "NONE";
        }
    }

    private void reviseURL() {
        if (API_URL.endsWith("/")) API_URL = API_URL + "chat/completions";
        else API_URL = API_URL + "/chat/completions";
    }

    private static class RunState {
        private boolean state;
        public RunState(boolean state) {
            this.state = state;
        }
    }

    private void checkConfig() {
        if (API_KEY == null) {
            if ((API_KEY = System.getenv("API_KEY")) == null)
                throw new RuntimeException("API_KEY的值为null");
        }
        if (API_URL == null) throw new RuntimeException("API_URL的值为null");
        if (request == null) throw new RuntimeException("没有初始化Request");
        if (request.getModel() == null) throw new RuntimeException("未指定model");
        if (request.getMessages() == null) throw new RuntimeException("Request中没有Messages");
    }

    @Override
    public String toString() {
        return "API_URL:" + API_URL + " API_KEY:" + API_KEY + " ableStore:" + ableStore;
    }
}
