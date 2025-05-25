package org.balinhui.Core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Call {
    private String API_URL;
    private String API_KEY;
    private Ai ai;
    private static final ObjectMapper mapper = new ObjectMapper();

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

    public Response getResponse() throws Exception {
        if (API_KEY == null) throw new RuntimeException("API_KEY的值为null");
        if (API_URL == null) throw new RuntimeException("API_URL的值为null");
        if (ai == null) throw new RuntimeException("没有初始化Ai");
        String _return = callApi(mapper.writeValueAsString(ai));
        return mapper.readValue(_return, Response.class);
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
