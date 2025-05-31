package org.balinhui.Core.JSON.Widgets;

public class COMPLETION_TOKENS_DETAILS {
    private int text_tokens;
    private int audio_tokens;
    private int reasoning_tokens;
    private int accepted_prediction_tokens;
    private int rejected_prediction_tokens;

    public COMPLETION_TOKENS_DETAILS() {
    }

    public COMPLETION_TOKENS_DETAILS(int text_tokens,
                                     int audio_tokens,
                                     int reasoning_tokens,
                                     int accepted_prediction_tokens,
                                     int rejected_prediction_tokens) {
        this.text_tokens = text_tokens;
        this.audio_tokens = audio_tokens;
        this.reasoning_tokens = reasoning_tokens;
        this.accepted_prediction_tokens = accepted_prediction_tokens;
        this.rejected_prediction_tokens = rejected_prediction_tokens;
    }

    public int getText_tokens() {
        return text_tokens;
    }

    public int getAudio_tokens() {
        return audio_tokens;
    }

    public int getReasoning_tokens() {
        return reasoning_tokens;
    }

    public int getAccepted_prediction_tokens() {
        return accepted_prediction_tokens;
    }

    public int getRejected_prediction_tokens() {
        return rejected_prediction_tokens;
    }
}
