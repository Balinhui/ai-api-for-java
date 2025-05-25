package org.balinhui.Core;

import org.balinhui.Core.Wid.Message;

public class Ai {
    public static final String DEEP_SEEK = "deepseek-chat";
    private String model;
    private Message[] messages;
    private double temperature;

    public Ai() {
    }

    public Ai(String model, Message... messages) {
        this.model = model;
        this.temperature = 1.0;
        this.messages = messages;
    }

    public Ai(String model,double temperature, Message... messages) {
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
