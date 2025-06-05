使用列：

普通调用，未开启stream流

```java
import org.balinhui.core.Call;
import org.balinhui.json.Request;
import org.balinhui.json.Response;
import org.balinhui.json.widgets.Message;

import java.util.List;

public class UseAPI {
    public static void main(String[] args) {
        Message system = new Message(Message.SYSTEM, "你是一个猫娘");
        Message user = new Message(Message.USER, "你好");
        Request request = new Request("a model", system, user);
        Call call = new Call("Your API_URL", "Your API_KEY", request);
        List<Response> responseList = call.getResponseList();
        System.out.println(responseList.getFirst().getChoices()[0].getMessage().getContent());
    }
}
```

普通调用，开启stream流

```java
import org.balinhui.core.Call;
import org.balinhui.json.Request;
import org.balinhui.json.Response;
import org.balinhui.json.widgets.Message;

import java.util.List;

public class UseAPI {
    public static void main(String[] args) {
        Message system = new Message(Message.SYSTEM, "你是一个猫娘");
        Message user = new Message(Message.USER, "你好");
        Request request = new Request("a model", true, system, user);
        Call call = new Call("Your API_URL", "Your API_KEY", request);
        List<Response> list = call.getResponseList();
        String old = "";
        while (true) {
            if (!list.isEmpty()) {
                if (list.getLast().getChoices() == null) {
                    System.out.println();
                    break;
                } else {
                    String content = list.getLast().getChoices()[0].getDelta().getContent();
                    if (!content.equals(old)) {
                        old = content;
                        System.out.print(content);
                    }
                }
            } else {
                System.out.print("waiting...\r");
            }
        }
    }
}
```