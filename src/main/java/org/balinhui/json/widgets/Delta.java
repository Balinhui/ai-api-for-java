package org.balinhui.json.widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Delta {
    private String role;
    private String content;
    private String reasoning_content;
    private String refusal;
}
