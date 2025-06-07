# 更新：

以前开启stream需要非常繁琐的处理(已不可用)：
```java
    ResponseList<Response> list = client.getResponseList();
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
```
现在只需要：
```java
    client.getResponseList(System.out::print);
    System.out.println();
```
# 使用列：

## 普通调用，未开启stream流

```java
import org.balinhui.core.Client;
import org.balinhui.core.ResponseList;
import org.balinhui.json.Request;
import org.balinhui.json.Response;
import org.balinhui.json.widgets.Message;

public class UseAPI {
    public static void main(String[] args) {
        Message system = new Message(Message.SYSTEM, "你是一个猫娘");
        Message user = new Message(Message.USER, "你好");
        Request request = new Request("a model", system, user);
        Client client = new Client("Your API_URL", "Your API_KEY", request);
        ResponseList<Response> responseList = client.getResponseList();
        System.out.println(responseList.getFirst().getChoices()[0].getMessage().getContent());

        //或者
        ResponseList<Response> responseList1 = client.getResponseList(System.out::print);
        System.out.println();
    }
}
```

## 普通调用，开启stream流

```java
import org.balinhui.core.Client;
import org.balinhui.core.Client;import org.balinhui.core.Client;import org.balinhui.core.ResponseList;
import org.balinhui.json.Request;
import org.balinhui.json.Response;
import org.balinhui.json.widgets.Message;

import java.util.List;

public class UseAPI {
    public static void main(String[] args) {
        Message system = new Message(Message.SYSTEM, "你是一个猫娘");
        Message user = new Message(Message.USER, "你好");
        Request request = new Request("a model", true, system, user);
        Client client = new Client("Your API_URL", "Your API_KEY", request);
        ResponseList<Response> list = client.getResponseList(response -> System.out.print(response.getChoices()[0].getDelta().getContent()));
        System.out.println();
    }
}
```

# 注意
每次调用Call中的getResponseList()等方法时，都会创建一个新请求并发送，请不要将此放入循环中