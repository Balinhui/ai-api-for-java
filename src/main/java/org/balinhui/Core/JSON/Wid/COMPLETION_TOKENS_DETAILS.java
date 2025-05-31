package org.balinhui.Core.JSON.Wid;

public class COMPLETION_TOKENS_DETAILS {
    private int text_tokens;
    private int audio_tokens;
    private int reasoning_tokens;

    public COMPLETION_TOKENS_DETAILS() {
    }

    public COMPLETION_TOKENS_DETAILS(int text_tokens, int audio_tokens, int reasoning_tokens) {
        this.text_tokens = text_tokens;
        this.audio_tokens = audio_tokens;
        this.reasoning_tokens = reasoning_tokens;
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
}
