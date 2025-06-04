package org.balinhui.core.json.widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
