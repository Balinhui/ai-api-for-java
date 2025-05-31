package org.balinhui.Core.JSON.Widgets;

public class Error {
    private String message;
    private String type;

    public Error() {
    }

    public Error(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
