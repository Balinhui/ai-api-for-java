package org.balinhui.util;

import lombok.Getter;
import org.balinhui.json.widgets.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    public static final String roleSeparate = "<ROLE>";
    public static final String contentSeparate = "<CONT>";
    private final List<Message> dialogues = new ArrayList<>();
    @Getter
    private static final Store store = new Store();

    private Store() {
    }

    public void add(Message... messages) {
        if (messages.length == 0) return;
        Collections.addAll(dialogues, messages);
    }

    public Message[] getMessages() {
        return dialogues.toArray(new Message[0]);
    }

    public int getSize() {
        return dialogues.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Message message : dialogues) {
            sb.append(roleSeparate).append(message.getRole()).append(roleSeparate).append(":")
                    .append(contentSeparate).append(message.getContent()
                    .replaceAll("\\\\\\[|\\\\]", "\\$"))
                    .append(contentSeparate).append("\n\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }
}
