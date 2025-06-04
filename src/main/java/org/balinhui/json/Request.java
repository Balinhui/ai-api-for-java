package org.balinhui.json;

import lombok.Getter;
import lombok.Setter;
import org.balinhui.json.widgets.Message;

@Getter
@Setter
public class Request {
    private String model;
    private Message[] messages;
    private boolean stream = false;
    private double temperature = 1.0;
    private int max_tokens = 4096;

    public Request() {
    }

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

    public boolean getStream() {
        return stream;
    }

    public Request writeModel(String model) {
        this.model = model;
        return this;
    }

    public Request writeMessages(Message... messages) {
        this.messages = messages;
        return this;
    }

    public Request writeStream(boolean stream) {
        this.stream = stream;
        return this;
    }

    public Request writeTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Request writeMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
        return this;
    }
}
