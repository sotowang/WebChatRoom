package edu.xjtu.service.impl;

import edu.xjtu.model.User;
import edu.xjtu.service.UserService;

public class UserServiceImpl {
    private UserService userService = new UserService();

    public boolean authenticateUSer(User user) {
        return userService.userExists(user);
    }

    public boolean authenticateUSer(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService.userExists(user);
    }

    //注册用户
    public boolean registerUser(User user) {
        return userService.addUser(user);
    }

    //查询用户
    public User queryUserWithUserName(String username) {
        return userService.querUserWithName(username);
    }

}
