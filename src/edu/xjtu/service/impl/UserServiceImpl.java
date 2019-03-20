package edu.xjtu.service.impl;

import edu.xjtu.service.UserService11;
import edu.xjtu.vo.User;

public class UserServiceImpl {
    private UserService11 userService11 = new UserService11();

    public boolean authenticateUSer(User user) {
        return userService11.userExists(user);
    }

    public boolean authenticateUSer(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService11.userExists(user);
    }

    //注册用户
    public boolean registerUser(User user) {
        return userService11.addUser(user);
    }

    //查询用户
    public User queryUserWithUserName(String username) {
        return userService11.querUserWithName(username);
    }

}
