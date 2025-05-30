package org.balinhui;

import org.balinhui.Core.JSON.Request;
import org.balinhui.Core.Call;
import org.balinhui.Core.JSON.Response;
import org.balinhui.Core.JSON.Wid.Message;
import org.balinhui.Core.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static final Properties config = new Properties();
    private static final String prompt;
    static {
        prompt = "你是一个猫娘。如果我发送exit，则表示我要退出聊天。";
        try {
            config.load(new FileInputStream("src\\main\\java\\org\\balinhui\\config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String in = sc.nextLine();

            if (in.equals("list")) {
                System.out.println(Arrays.toString(Logger.getLogList()));
                continue;
            }

            Request request = new Request("deepseek-chat", new Message(Message.SYSTEM, prompt),
                    new Message(Message.USER, in));
            request.setTemperature(1.5);
            Call call = new Call(request);
            call.setAPI_KEY(config.getProperty("API_KEY"));
            call.setAPI_URL(config.getProperty("API_URL"));
            call.setAbleStore(true);
            Response response = call.getResponse();
            System.out.println(response.getChoices()[0].getMessage().getContent());
            if (in.equals("exit")) return;
        }
    }
}
