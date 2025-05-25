package org.balinhui.Core.Wid;

public class PROMPT_TOKENS_DETAILS {
    private int cached_tokens;

    public PROMPT_TOKENS_DETAILS() {
    }

    public PROMPT_TOKENS_DETAILS(int cached_tokens) {
        this.cached_tokens = cached_tokens;
    }

    public int getCached_tokens() {
        return cached_tokens;
    }

    public void setCached_tokens(int cached_tokens) {
        this.cached_tokens = cached_tokens;
    }
}
