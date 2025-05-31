package org.balinhui.Core.JSON.Widgets;

public class Choice {
    private int index;
    private Message message;
    private Delta delta;
    private String logprobs;
    private String finish_reason;

    public Choice() {
    }

    public Choice(int index,
                  Message message,
                  Delta delta,
                  String logprobs,
                  String finish_reason) {
        this.index = index;
        this.message = message;
        this.delta = delta;
        this.logprobs = logprobs;
        this.finish_reason = finish_reason;
    }

    public int getIndex() {
        return index;
    }

    public Message getMessage() {
        return message;
    }

    public Delta getDelta() {
        return delta;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }
}
