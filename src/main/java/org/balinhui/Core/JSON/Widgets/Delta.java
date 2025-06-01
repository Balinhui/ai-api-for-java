package org.balinhui.Core.JSON.Widgets;

public class Delta {
    private String role;
    private String content;
    private String reasoning_content;

    public Delta() {
    }

    public Delta(String role, String content, String reasoning_content) {
        this.role = role;
        this.content = content;
        this.reasoning_content = reasoning_content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public String getReasoning_content() {
        return reasoning_content;
    }
}
