package org.balinhui.Core.JSON.Wid;

public class PROMPT_TOKENS_DETAILS {
    private int cached_tokens;
    private int text_tokens;
    private int audio_tokens;
    private int image_tokens;

    public PROMPT_TOKENS_DETAILS() {
    }

    public PROMPT_TOKENS_DETAILS(int cached_tokens, int text_tokens, int audio_tokens, int image_tokens) {
        this.cached_tokens = cached_tokens;
        this.text_tokens = text_tokens;
        this.audio_tokens = audio_tokens;
        this.image_tokens = image_tokens;
    }

    public int getCached_tokens() {
        return cached_tokens;
    }

    public int getText_tokens() {
        return text_tokens;
    }

    public int getAudio_tokens() {
        return audio_tokens;
    }

    public int getImage_tokens() {
        return image_tokens;
    }
}
