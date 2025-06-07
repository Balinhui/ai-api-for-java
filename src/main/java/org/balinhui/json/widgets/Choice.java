package org.balinhui.json.widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
    private int index;
    private Message message;
    private Delta delta;
    private String logprobs;
    private String finish_reason;
    private CONTENT_FILTER_RESULTS content_filter_results;
}
