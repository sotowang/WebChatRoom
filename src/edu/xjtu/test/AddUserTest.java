package edu.xjtu.test;

import edu.xjtu.service.impl.UserServiceImpl;
import edu.xjtu.vo.User;

public class AddUserTest {
    public static void main(String[] args) {
        User user = new User();
        user.setType("user");

        user.setPassword("test");
        user.setUsername("test");

        UserServiceImpl userService = new UserServiceImpl();

        System.out.println(userService.registerUser(user));

    }
}
