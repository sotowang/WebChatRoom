package edu.xjtu.model;
/**
 * 这个是用户
 */
public class User {
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;


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
}
