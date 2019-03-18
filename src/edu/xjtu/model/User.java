package edu.xjtu.model;

import org.json.JSONObject;

/**
 * 这个是用户
 */
public class User implements IJsonSerialize{
    public static final String USERNAME = "username";
    public static final String PASSWORD  = "password";
    public static final String NICKNAME = "nickname";


    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    public User() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    //将字段转换 为JSON格式存储
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(USERNAME, username);
        jsonObject.put(PASSWORD, password);
        jsonObject.put(NICKNAME, nickname);

        return jsonObject;
    }


    //将对象属性从JSON读出
    @Override
    public void readFromJson(JSONObject jsonObject) {
        if (jsonObject.has(USERNAME)) {
            this.username = jsonObject.getString(USERNAME);
        }
        if (jsonObject.has(PASSWORD)) {
            this.password = jsonObject.getString(PASSWORD);
        }
        if (jsonObject.has(NICKNAME)) {
            this.nickname = jsonObject.getString(NICKNAME);
        }
    }
}
