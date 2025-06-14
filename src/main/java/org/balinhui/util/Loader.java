package org.balinhui.util;

import lombok.Getter;
import org.balinhui.exceptions.DuplicateAdditionException;
import org.balinhui.json.widgets.Message;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Loader extends LogFile {
    private String name;
    private static boolean action = false;
    @Getter
    private static final Loader loader = new Loader();
    private int pos;
    private Loader() {
    }

    public Message[] load(int i) {
        if (action) throw new DuplicateAdditionException("你已经添加过一次了");
        String[] logList = getLogList();
        Objects.checkIndex(i, logList.length);
        name = logList[i];
        File chooseFile = new File(getLogDirectory(), logList[i]);
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(chooseFile);
            char[] charBuff = {1};
            while (fileReader.read(charBuff) != -1) {
                sb.append(charBuff);
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Message[] token = token(sb.toString());
        reviseLogger();
        action = true;
        return token;
    }

    private Message[] token(String in) {
        List<Message> messages = new ArrayList<>();
        while (pos < in.length()) {
            String user;
            String content;
            if (equalTip(in)) {
                pos++;
                user = get(in);
                pos += 8;
                content = get(in);
                messages.add(new Message(user, content));
            } else {
                pos++;
            }
        }
        return messages.toArray(new Message[0]);
    }

    private String get(String s) {
        StringBuilder sb = new StringBuilder();
        while (!equalTip(s)) {
            sb.append(s.charAt(pos));
            pos++;
        }
        return sb.toString();
    }

    private boolean equalTip(String s) {
        if (s.charAt(pos) == '<') {
            String get = s.substring(pos, pos + 6);
            if (get.equals(Store.roleSeparate) || get.equals(Store.contentSeparate)) {
                pos += 5;
                return true;
            }
            return false;
        }
        return false;
    }

    private void reviseLogger() {
        try {
            Class<?> cls = Class.forName("org.balinhui.util.Logger");
            Field name = cls.getDeclaredField("name");
            Field action = cls.getDeclaredField("action");
            name.setAccessible(true);
            action.setAccessible(true);
            name.set(Logger.getLogger(), this.name);
            action.setBoolean(Logger.getLogger(), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
