package org.balinhui.Core.JSON;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.balinhui.Core.JSON.Widgets.Error;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wrong {
    private Error error;
    private String event_id;
    private String error_msg;
}
