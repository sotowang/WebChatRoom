package edu.xjtu.test;

import edu.xjtu.model.User;
import edu.xjtu.service.UserService;
import edu.xjtu.service.impl.UserServiceImpl;

public class userExistTest {
    public static void main(String[] args) {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setPassword("111");
        user1.setUsername("111");


        user2.setUsername("aaa");
        user2.setPassword("aaa");

        user3.setUsername("sotowang");
        user3.setPassword("sotowang");
        user3.setNickname("nickname");

        UserServiceImpl userService = new UserServiceImpl();


//        UserService userService = new UserService();
        System.out.println(userService.authenticateUSer(user1));

        System.out.println(userService.authenticateUSer(user2));
        System.out.println(userService.authenticateUSer(user3));


    }
}
