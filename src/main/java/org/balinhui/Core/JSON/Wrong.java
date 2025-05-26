package org.balinhui.Core.JSON;

public class Wrong {
    private String event_id;
    private String error_msg;

    public Wrong() {
    }

    public Wrong(String event_id, String error_msg) {
        this.event_id = event_id;
        this.error_msg = error_msg;
    }

    public String getEvent_id() {
        return event_id;
    }
    public String getError_msg() {
        return error_msg;
    }
}
