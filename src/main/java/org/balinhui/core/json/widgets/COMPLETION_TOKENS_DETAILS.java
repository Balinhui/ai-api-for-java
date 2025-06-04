package org.balinhui.core.json.widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class COMPLETION_TOKENS_DETAILS {
    private int text_tokens;
    private int audio_tokens;
    private int reasoning_tokens;
    private int accepted_prediction_tokens;
    private int rejected_prediction_tokens;
}
