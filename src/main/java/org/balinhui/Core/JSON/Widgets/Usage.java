package org.balinhui.Core.JSON.Widgets;

public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
    private PROMPT_TOKENS_DETAILS prompt_tokens_details;
    private COMPLETION_TOKENS_DETAILS completion_tokens_details;
    private int prompt_cache_hit_tokens;
    private int prompt_cache_miss_tokens;
    private int input_tokens;
    private int output_tokens;
    private INPUT_TOKENS_DETAILS input_tokens_details;

    public Usage() {
    }

    public Usage(int prompt_tokens,
                 int completion_tokens,
                 int total_tokens,
                 PROMPT_TOKENS_DETAILS prompt_tokens_details,
                 COMPLETION_TOKENS_DETAILS completion_tokens_details,
                 int prompt_cache_hit_tokens,
                 int prompt_cache_miss_tokens,
                 int input_tokens,
                 int output_tokens,
                 INPUT_TOKENS_DETAILS input_tokens_details) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
        this.prompt_tokens_details = prompt_tokens_details;
        this.completion_tokens_details = completion_tokens_details;
        this.prompt_cache_hit_tokens = prompt_cache_hit_tokens;
        this.prompt_cache_miss_tokens = prompt_cache_miss_tokens;
        this.input_tokens = input_tokens;
        this.output_tokens = output_tokens;
        this.input_tokens_details = input_tokens_details;
    }

    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public int getTotal_tokens() {
        return total_tokens;
    }

    public PROMPT_TOKENS_DETAILS getPrompt_tokens_details() {
        return prompt_tokens_details;
    }
    public COMPLETION_TOKENS_DETAILS getCompletion_tokens_details() {
        return completion_tokens_details;
    }

    public int getPrompt_cache_hit_tokens() {
        return prompt_cache_hit_tokens;
    }

    public int getPrompt_cache_miss_tokens() {
        return prompt_cache_miss_tokens;
    }

    public int getInput_tokens() {
        return input_tokens;
    }

    public int getOutput_tokens() {
        return output_tokens;
    }

    public INPUT_TOKENS_DETAILS getInput_tokens_details() {
        return input_tokens_details;
    }
}
