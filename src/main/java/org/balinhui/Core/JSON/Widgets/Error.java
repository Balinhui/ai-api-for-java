package org.balinhui.Core.JSON.Widgets;

public class Error {
    private String message;
    private String type;
    private String param;
    private String code;

    public Error() {
    }

    public Error(String message, String type, String param, String code) {
        this.message = message;
        this.type = type;
        this.param = param;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getParam() {
        return param;
    }

    public String getCode() {
        return code;
    }
}
