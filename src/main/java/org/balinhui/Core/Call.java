package org.balinhui.Core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.balinhui.Core.JSON.Request;
import org.balinhui.Core.JSON.Response;
import org.balinhui.Core.JSON.Wid.Message;
import org.balinhui.Core.JSON.Wrong;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Call {
    private String API_URL;
    private String API_KEY;
    private Request request;
    private static final ObjectMapper mapper = new ObjectMapper();
    private final Store store = Store.getStore();
    private boolean ableStore = false;

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

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setAbleStore(boolean b) {
        this.ableStore = b;
    }

    public boolean getAbleStore() {
        return ableStore;
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @param request 请求
     * @return 响应JSON的Java类
     */
    public Response getResponse(Request request) {
        if (this.request != request)
            this.request = request;
        return getResponse();
    }

    /**
     * 向<code>API_URL</code>发送请求，获得Java类
     * @return 响应JSON的Java类
     */
    public Response getResponse() {
        //检查必须的项
        if (API_KEY == null) {
            if ((API_KEY = System.getenv("API_KEY")) == null)
                throw new RuntimeException("API_KEY的值为null");
        }
        if (API_URL == null) throw new RuntimeException("API_URL的值为null");
        if (request == null) throw new RuntimeException("没有初始化Request");
        String _return = "null";//返回的JSON
        try {
            if (ableStore) storeMessage();
            _return = callApi(mapper.writeValueAsString(request));
            //将响应的JSON转化成相应的Java类
            Response response = mapper.readValue(_return, Response.class);
            if (ableStore) storeMessage(response.getChoices()[0].getMessage());
            return response;
        } catch (Exception e) {
            try {
                Wrong wrongInfo = mapper.readValue(_return, Wrong.class);
                throw new RuntimeException("API_URL错误。\nevent_id:" + wrongInfo.getEvent_id() +
                        ".\nerror_msg:" + wrongInfo.getError_msg());
            } catch (JsonProcessingException ex) {
                throw new RuntimeException("传回的JSON "+ _return + " 无法解析，请检查您的API_KEY和API_URL");
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

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
