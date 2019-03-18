package edu.xjtu.model;

import org.json.JSONObject;

/**
 * 存储聊天信息
 * 包括：
 */
public class ChatMessage implements IJsonSerialize{

    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String MESSAGE_CONTENT = "messageContent";
    public static final String MESSAGE_TYPE = "messageType";
    public static final String FROM_NICK = "fromNick";


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

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(FROM, from);
        jsonObject.put(TO, to);
        jsonObject.put(MESSAGE_CONTENT, messageContent);
        jsonObject.put(MESSAGE_TYPE, messageType);
        jsonObject.put(FROM_NICK, fromNick);
        return jsonObject;
    }

    @Override
    public void readFromJson(JSONObject jsonObject) {
        if (jsonObject.has(FROM)) {
            this.from = jsonObject.getString(FROM);
        }
        if (jsonObject.has(TO)) {
            this.to = jsonObject.getString(TO);
        }
        if (jsonObject.has(MESSAGE_TYPE)) {
            this.messageType = jsonObject.getString(messageType);
        }
        if (jsonObject.has(FROM_NICK)) {
            this.fromNick = jsonObject.getString(fromNick);
        }
        if (jsonObject.has(MESSAGE_CONTENT)) {
            this.messageContent = jsonObject.getString(MESSAGE_CONTENT);

        }

    }
}
