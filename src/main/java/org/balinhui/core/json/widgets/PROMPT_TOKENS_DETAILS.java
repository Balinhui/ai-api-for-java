package org.balinhui.core.json.widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PROMPT_TOKENS_DETAILS {
    private int cached_tokens;
    private int text_tokens;
    private int audio_tokens;
    private int image_tokens;
}
