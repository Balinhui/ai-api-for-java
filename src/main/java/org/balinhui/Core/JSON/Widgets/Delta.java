package org.balinhui.Core.JSON.Widgets;

public class Delta {
    private String role;
    private String content;

    public Delta() {
    }

    public Delta(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
