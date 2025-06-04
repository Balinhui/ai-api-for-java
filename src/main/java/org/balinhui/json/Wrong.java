package org.balinhui.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.balinhui.json.widgets.Error;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wrong {
    private Error error;
    private String event_id;
    private String error_msg;
}
