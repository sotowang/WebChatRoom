# Java WebSocket 聊天室
## 关于WebSocket的使用

* @ServerEndpoint将修饰的类定义成一个WebSocket服务器端
* @PathParam用来实现动态加载URI，它允许在URI路径中去映射方法中使用的参数
* javax.websocket 中有Session的使用方法和四个基础的事件触发注解
  Session中用的是getBasicRemote()方法，它是用同步阻塞的方式进行消息发送。
* 四个基础的事件触发注解有  @OnOpen,@OnMessage,@Onclose 和OnError

@**OnOpen**： 当新的连接建立成功时，将会调用该注解修饰的方法。方法中的参数可以有Session，EndpointConfig，零或多个String类型的被@PathParam注释的参数。

@**OnMessage**：当服务器收到客户端发送的消息时，将会调用该注解修饰的方法。服务器接收的消息类型可以是text，binary，pong。由于这些信息的类型不，那么方法中的参数也会有所区别，可以是Sesion，零或多个String类型的被@PathParam注释的参数。

​	当消息类型是text时，参数类型可以是String，java的原始类或者等价类，String和布尔对，Reader，Decoder.Text或者Decoder.TextStream;当消息类型是binary时，参数类型可以是byte数组或者ByteBuffer，byte数组和布尔对或者ByteBuffer和布尔对，输入流，Decoder.Binary 或者Decoder.BinaryStream;当消息类型是pong时，参数类型是Pongmessage

@**OnClose**:当连接关闭时，会调用该注解修饰的方法。方法中的参数可以有Session，CloseReason，零或多个String类型的被PathParam修饰的参数

@**OnError**： 当WebSocket通信过程中发生了错误，会调用该注解修饰的方法。方法中的参数可以有session，Throwable，零或多个String类型的补发@PathParam注释的参数。



## 数据库设计

```sql
CREATE TABLE chatroom.`user` (
	id int PRIMARY KEY auto_increment,
	username VARCHAR(20),
	password VARCHAR(20),
	type VARCHAR(20)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;
```

```sql

use chatroom;
insert into user VALUES (null,'aaa','aaa','admin');
insert into user VALUES (null,'bbb','bbb','user');
insert into user VALUES (null,'ccc','ccc','admin');
insert into user VALUES (null,'ddd','ddd','user');
```

## 系统架构
Servlet+ JSP + JavaBean + JDBC
Jar包：
    * MYSQL
    * C3P0连接池
    * jstl
    * dbutils

