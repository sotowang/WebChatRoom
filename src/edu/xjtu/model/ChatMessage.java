package edu.xjtu.model;

/**
 * 存储聊天信息
 * 包括：
 */
public class ChatMessage {
    //消息发送方
    private String from;
    //发绘谁
    private String to;
    //消息内容
    private String messageContent;
    //消息类型
    private String messageType;
    //发送者昵称
    private String fromNick;

    public ChatMessage(String from, String to, String messageContent) {
        this.from = from;
        this.to = to;
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageType='" + messageType + '\'' +
                ", fromNick='" + fromNick + '\'' +
                '}';
    }
}
