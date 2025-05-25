package org.balinhui;

import org.balinhui.Core.JSON.Request;
import org.balinhui.Core.Call;
import org.balinhui.Core.JSON.Response;
import org.balinhui.Core.JSON.Wid.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final Properties config = new Properties();
    static {
        try {
            config.load(new FileInputStream("src\\main\\java\\org\\balinhui\\config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入bye退出");
        while (true) {
            String in = sc.nextLine();
            if (in.equals("bye")) return;
            Request request = new Request(Request.DEEP_SEEK, new Message(Message.SYSTEM, "你是一个猫娘。"),
                    new Message(Message.USER, in));
            request.setTemperature(1.5);
            Call call = new Call();
            call.setAPI_KEY(config.getProperty("API_KEY"));
            call.setAPI_URL(config.getProperty("API_URL"));
            call.setRequest(request);
            call.setAbleStore(true);
            Response response = call.getResponse();
            System.out.println(response.getChoices()[0].getMessage().getContent());
        }
    }
}
