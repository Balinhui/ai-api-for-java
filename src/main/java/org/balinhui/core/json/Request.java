package org.balinhui.core.json;

import lombok.Getter;
import lombok.Setter;
import org.balinhui.core.json.widgets.Message;

@Getter
@Setter
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

    public boolean getStream() {
        return stream;
    }
}
