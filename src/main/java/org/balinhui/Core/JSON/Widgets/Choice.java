package org.balinhui.Core.JSON.Widgets;

public class Choice {
    private int index;
    private Message message;
    private String logprobs;
    private String finish_reason;

    public Choice() {
    }

    public Choice(int index,
                  Message message,
                  String logprobs,
                  String finish_reason) {
        this.index = index;
        this.message = message;
        this.logprobs = logprobs;
        this.finish_reason = finish_reason;
    }

    public int getIndex() {
        return index;
    }

    public Message getMessage() {
        return message;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }
}
