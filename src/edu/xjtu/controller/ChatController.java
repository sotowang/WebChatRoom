package edu.xjtu.controller;


import edu.xjtu.model.ChatMessage;
import edu.xjtu.model.User;
import edu.xjtu.service.impl.UserServiceImpl;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @ServerEndpoint将修饰的类定义成一个WebSocket服务器端 注解的值将被用于监听用户连接的终端访问URI地址
 * @PathParam用来实现动态加载URI，它允许在URI路径中去映射方法中使用的参数
 */
@ServerEndpoint("/chat/{username}")
public class ChatController {

    //系统通知
    private final String MessageTypeSystemNotify = "SystemNotify";

    //聊天消息
    private final String MessageTypeChatMessage = "ChatMessage";

    //存储在线用户
    private static List<ChatController> connectedUsers = new CopyOnWriteArrayList<>();


    //WebSocket的session会话
    private Session session;

    //登陆用户
    private User user;

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username) {
        this.session = session;
        User user = new User();
        UserServiceImpl impl = new UserServiceImpl();
        user.setUsername(username);

        this.user = impl.queryUserWithUserName(username);
        user.setPassword(null);

        //集合中添加该用户
        connectedUsers.add(this);

        //发送系统消息
        sendNotifyMessage();

        System.out.println(user.getUsername() + "上线！");


    }

    @OnClose
    public void onClose() {
        connectedUsers.remove(this);
        sendNotifyMessage();
        System.out.println(user.getUsername() + "下线！");

    }


    //群发消息
    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject messageJson = new JSONObject(message);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.readFromJson(messageJson);
        if (chatMessage.getFrom() == null) {
            return;
        }
        chatMessage.setMessageType(MessageTypeChatMessage);
        chatMessage.setFromNick(user.getNickname());
        if (chatMessage.getTo() == null) {
            for (ChatController chatController : connectedUsers) {
                if (chatController.user.getUsername().trim().equals(chatMessage.getFrom())) {
                    //如果是消息发送者本身，不再转发
                    continue;
                }
                String msgContent = chatMessage.toJson().toString();
                try {
                    chatController.sendMessageText(msgContent);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());

    }

    /**
     * 同步发送消息
     * @param content
     * @throws IOException
     */
    private void sendMessageText(String content) throws IOException {
        this.session.getBasicRemote().sendText(content);

    }


    /**
     * 系统发送消息给所有人
     */
    private void sendNotifyMessage() {
        ChatMessage message = new ChatMessage();
        message.setMessageType(MessageTypeSystemNotify);
        message.setMessageContent(user.toString());
        message.setFrom("system");
        message.setTo(null);
        if (connectedUsers.size() > 0) {
            try {
                connectedUsers.iterator().next().session.getBasicRemote().sendText(message.toJson().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
