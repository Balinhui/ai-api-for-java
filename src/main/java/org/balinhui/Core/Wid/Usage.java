package org.balinhui.Core.Wid;

public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
    private PROMPT_TOKENS_DETAILS prompt_tokens_details;
    private int prompt_cache_hit_tokens;
    private int prompt_cache_miss_tokens;

    public Usage() {
    }

    public Usage(int prompt_tokens,
                 int completion_tokens,
                 int total_tokens,
                 PROMPT_TOKENS_DETAILS prompt_tokens_details,
                 int prompt_cache_hit_tokens,
                 int prompt_cache_miss_tokens) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
        this.prompt_tokens_details = prompt_tokens_details;
        this.prompt_cache_hit_tokens = prompt_cache_hit_tokens;
        this.prompt_cache_miss_tokens = prompt_cache_miss_tokens;
    }

    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setPrompt_tokens(int prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(int completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public int getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(int total_tokens) {
        this.total_tokens = total_tokens;
    }

    public PROMPT_TOKENS_DETAILS getPrompt_tokens_details() {
        return prompt_tokens_details;
    }

    public void setPrompt_tokens_details(PROMPT_TOKENS_DETAILS prompt_tokens_details) {
        this.prompt_tokens_details = prompt_tokens_details;
    }

    public int getPrompt_cache_hit_tokens() {
        return prompt_cache_hit_tokens;
    }

    public void setPrompt_cache_hit_tokens(int prompt_cache_hit_tokens) {
        this.prompt_cache_hit_tokens = prompt_cache_hit_tokens;
    }

    public int getPrompt_cache_miss_tokens() {
        return prompt_cache_miss_tokens;
    }

    public void setPrompt_cache_miss_tokens(int prompt_cache_miss_tokens) {
        this.prompt_cache_miss_tokens = prompt_cache_miss_tokens;
    }
}
