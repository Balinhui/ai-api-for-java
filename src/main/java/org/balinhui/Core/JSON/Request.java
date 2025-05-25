package org.balinhui.Core.JSON;

import org.balinhui.Core.JSON.Wid.Message;

public class Request {
    /**
     * deepseek的模型
     */
    public static final String DEEP_SEEK = "deepseek-chat";
    private String model;

    /**
     * 发送的消息
     */
    private Message[] messages;
    private double temperature;

    public Request() {
    }

    public Request(String model, Message... messages) {
        this.model = model;
        this.temperature = 1.0;
        this.messages = messages;
    }

    public Request(String model, double temperature, Message... messages) {
        this.model = model;
        this.temperature = temperature;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}
