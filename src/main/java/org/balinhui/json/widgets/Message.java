package org.balinhui.json.widgets;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Message {
    public static final String USER = "user";
    public static final String SYSTEM = "system";
    public static final String ASSISTANT = "assistant";
    public static final String TOOL = "tool";
    /**
     * 选择的角色，有<code>user</code>，<code>system</code>和<code>assistant</code>
     */
    @Setter
    private String role;
    private String content;
    private String reasoning_content;
    private String refusal;
    private String tool_calls;
    private String function_call;
    private Annotations[] annotations;

    private Message() {
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    private Message(String role, String content, String reasoning_content, String tool_calls, String function_call,
                    Annotations[] annotations, String refusal) {
        this.role = role;
        this.content = content;
        this.reasoning_content = reasoning_content;
        this.tool_calls = tool_calls;
        this.function_call = function_call;
        this.annotations = annotations;
        this.refusal = refusal;
    }
}
