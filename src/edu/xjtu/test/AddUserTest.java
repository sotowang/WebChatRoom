package edu.xjtu.test;

import edu.xjtu.model.User;
import edu.xjtu.service.impl.UserServiceImpl;

public class AddUserTest {
    public static void main(String[] args) {
        User user = new User();
        user.setNickname("test");

        user.setPassword("test");
        user.setUsername("test");

        UserServiceImpl userService = new UserServiceImpl();

        System.out.println(userService.registerUser(user));

    }
}
