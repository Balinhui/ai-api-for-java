package org.balinhui.Core.JSON.Widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String message;
    private String type;
    private String param;
    private String code;
}
