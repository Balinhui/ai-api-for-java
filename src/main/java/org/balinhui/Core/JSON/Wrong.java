package org.balinhui.Core.JSON;

import org.balinhui.Core.JSON.Widgets.Error;

public class Wrong {
    private Error error;
    private String event_id;
    private String error_msg;

    public Wrong() {
    }

    public Wrong(Error error, String event_id, String error_msg) {
        this.error = error;
        this.event_id = event_id;
        this.error_msg = error_msg;
    }

    public Error getError() {
        return error;
    }

    public String getEvent_id() {
        return event_id;
    }
    public String getError_msg() {
        return error_msg;
    }
}
