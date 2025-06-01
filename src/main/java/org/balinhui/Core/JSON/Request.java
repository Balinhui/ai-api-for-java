package org.balinhui.Core.JSON;

import org.balinhui.Core.JSON.Widgets.Message;

public class Request {
    private String model;

    /**
     * 发送的消息
     */
    private Message[] messages;
    private boolean stream;
    private double temperature;

    public Request(String model, Message... messages) {
        this.model = model;
        this.temperature = 1.0;
        this.stream = false;
        this.messages = messages;
    }

    public Request(String model, double temperature, Message... messages) {
        this.model = model;
        this.temperature = temperature;
        this.stream = false;
        this.messages = messages;
    }

    public Request(String model, boolean stream, Message... messages) {
        this.model = model;
        this.temperature = 1.0;
        this.stream = stream;
        this.messages = messages;
    }

    public Request(String model, double temperature, boolean stream, Message... messages) {
        this.model = model;
        this.temperature = temperature;
        this.stream = stream;
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

    public boolean getStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}
