package org.balinhui.Core.JSON;

import org.balinhui.Core.JSON.Widgets.Message;

public class Request {
    private String model;
    private Message[] messages;
    private boolean stream = false;
    private double temperature = 1.0;
    private int max_tokens = 4096;

    public Request(String model, Message... messages) {
        this.model = model;
        this.messages = messages;
    }

    public Request(String model, double temperature, Message... messages) {
        this.model = model;
        this.temperature = temperature;
        this.messages = messages;
    }

    public Request(String model, boolean stream, Message... messages) {
        this.model = model;
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

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }
}
