package org.balinhui.Core;

import org.balinhui.Core.Wid.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private final List<Message> dialogues;
    private static final Store store = new Store();

    private Store() {
        this.dialogues = new ArrayList<>();
    }

    public static Store getStore() {
        return store;
    }

    public void add(Message... messages) {
        if (messages.length == 1) dialogues.add(messages[0]);
        else Collections.addAll(dialogues, messages);
    }

    public Message[] getMessages() {
        Message[] messages = new Message[dialogues.size()];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = dialogues.get(i);
        }
        return messages;
    }

    public int getSize() {
        return dialogues.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Message message : dialogues) {
            sb.append(message.getRole()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
