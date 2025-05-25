package org.balinhui.Core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.balinhui.Core.JSON.Ai;
import org.balinhui.Core.JSON.Response;
import org.balinhui.Core.JSON.Wid.Message;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Call {
    private String API_URL;
    private String API_KEY;
    private Ai ai;
    private static final ObjectMapper mapper = new ObjectMapper();
    private final Store store = Store.getStore();
    private boolean ableStore = false;

    public Call() {
    }

    public Call(String API_URL, String API_KEY) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
    }

    public Call(String API_URL, String API_KEY, Ai ai) {
        this.API_URL = API_URL;
        this.API_KEY = API_KEY;
        this.ai = ai;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public void setAi(Ai ai) {
        this.ai = ai;
    }

    public void setAbleStore(boolean b) {
        this.ableStore = b;
    }

    public boolean getAbleStore() {
        return ableStore;
    }

    public Response getResponse() {
        if (API_KEY == null) throw new RuntimeException("API_KEY的值为null");
        if (API_URL == null) throw new RuntimeException("API_URL的值为null");
        if (ai == null) throw new RuntimeException("没有初始化Ai");
        String _return = "null";
        try {
            if (ableStore) storeMessage();
            _return = callApi(mapper.writeValueAsString(ai));
            Response response = mapper.readValue(_return, Response.class);
            if (ableStore) storeMessage(response.getChoices()[0].getMessage());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("传回的JSON无法解析，请检查您的API_KEY和API_URL，内容为" + _return);
        }
    }

    private void storeMessage() {
        for (Message message : ai.getMessages()) {
            if (store.getSize() != 0) {
                if (!message.getRole().equals(Message.SYSTEM)) store.add(message);
            } else {
                store.add(ai.getMessages());
                return;
            }
        }
        ai.setMessages(store.getMessages());
    }

    private void storeMessage(Message message) {
        message.setRole(Message.ASSISTANT);
        store.add(message);
        Logger.getLogger().log(store.toString());
    }

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
