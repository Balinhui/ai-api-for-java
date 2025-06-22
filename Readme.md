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
# 内容
## package
core(项目中主要的功能)
> [Client](src/main/java/org/balinhui/core/Client.java): 发送请求[Request](src/main/java/org/balinhui/json/Request.java)并返回响应[Response](src/main/java/org/balinhui/json/Response.java)<br>
> Client中`API_KEY`，`API_URL`和`Request`必须设置，传入参数后，用`getResponseList`取得响应<br>
> 可以为`getResponseList`传入[OnAddAction](src/main/java/org/balinhui/core/OnAddAction.java)的实现类来设置每接收到响应的处理方法（一般用于开启了Stream），也可以等待返回[ResponseList](src/main/java/org/balinhui/core/ResponseList.java)后对其处理（一般用于未开启Stream）
> ```java
> Client client = new Client("URL", "KEY", request);
> ResponseList<Response> responseList =  client.getResponseList();//对responseList处理
> //或
> client.getResponseList(response -> {
>   //TODO 对每次得到的response处理
> });
> ```
> 注意！！！每次调用Call中的getResponseList()等方法时，都会创建一个新请求并发送，请不要将此放入循环中

> [OnAddAction](src/main/java/org/balinhui/core/OnAddAction.java): 在[Response](src/main/java/org/balinhui/json/Response.java)被添加进[ResponseList](src/main/java/org/balinhui/core/ResponseList.java)时调用，实现它即可实现添加后的操作
> ```java
> import org.balinhui.core.OnAddAction;
> import org.balinhui.json.Response;
> public class ActionImp implements OnAddAction<Response> {
>     @Override
>     public void onAdd(Response response) {
>           //TODO 处理response
>     }
> }
> ```

> [ResponseList](src/main/java/org/balinhui/core/ResponseList.java): 用于存[response](src/main/java/org/balinhui/json/Response.java)的集合

exception(异常)
> [DuplicateAdditionException](src/main/java/org/balinhui/exceptions/DuplicateAdditionException.java): 在使用[Loader](src/main/java/org/balinhui/util/Loader.java)重复加载对话时抛出。所以请小心使用哦~

json(Java类与json的互相转换，通过[`jackson`](https://github.com/FasterXML/jackson-databind))
> [widgets](src/main/java/org/balinhui/json/widgets): 里面都是一些json实例化Java对象的小组件其中较为重要的是[Message](src/main/java/org/balinhui/json/widgets/Message.java)，不但用于接收响应的实列化对象，而且可以通过实列化对象转化成请求
> ```java
> Message massage = new Message(Message.USER, "你好");
> ```

> [Request](src/main/java/org/balinhui/json/Request.java): 发送的请求
> ```java
> Request request = new Request("model", message);
> ```

> [Response](src/main/java/org/balinhui/json/Response.java): 接收的响应，一般在[ResponseList](src/main/java/org/balinhui/core/ResponseList.java)中取得

> [Wrong](src/main/java/org/balinhui/json/Wrong.java): 部分报错信息

util(工具)
> [Loader](src/main/java/org/balinhui/util/Loader.java): 加载历史对话，与[LogFile](src/main/java/org/balinhui/util/LogFile.java)中的`getLogList()`使用。`load(int i)`需要传入`getLogList()`的索引
> ```java
> System.out.println(LogFile.getLogList());
> Loader.getLoader.load(0);
> ```

> [Logger](src/main/java/org/balinhui/util/Logger.java): 将字符串存入`ai.log.md`文件

> [Store](src/main/java/org/balinhui/util/Store.java): 保存对话记录。要开启此功能需要设置[Client](src/main/java/org/balinhui/core/Client.java)中的`ableStore`为`true`。

> [Reader](src/main/java/org/balinhui/util/Reader.java): 可以用于加载配置，输入一个格式为apiKey=...，apiUrl=...的.properties文件，用`getConfig`获取。