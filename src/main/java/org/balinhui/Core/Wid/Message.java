package org.balinhui.Core.Wid;

public class Message {
    public static final String USER = "user";
    public static final String SYSTEM = "system";
    public static final String ASSISTANT = "assistant";
    /**
     * 选择的角色，有<code>user</code>，<code>system</code>和<code>assistant</code>
     */
    private String role;

    /**
     * 消息的内容
     */
    private String content;

    public Message() {
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }
}
