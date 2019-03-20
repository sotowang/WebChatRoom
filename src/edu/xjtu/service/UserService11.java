package edu.xjtu.service;

import edu.xjtu.db.DataBase;
import edu.xjtu.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService11 {

    private DataBase db = new DataBase();

    private final String sql1 = "select * from user where username = ?";

    /**
     * 判断用户是否存在
     * @param user
     * @return
     */
    public boolean userExists(User user) {

        //数据库连接不上
        if (!db.openConnection()) {
            return false;
        }
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql1);

            String username = user.getUsername();
            ps.setString(1, username);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            db.closeConnection();
        }

    }

    private final String sql2 = "insert into user values(?,?,?,?)";


    public boolean addUser(User user) {
        if (userExists(user)) {
            return false;
        }
        if (!db.openConnection()) {
            return false;
        }
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql2);
            String username = user.getUsername();
            String password = user.getPassword();


            ps.setString(1, null);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, "user");


            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("添加用户失败！ " + e.getMessage());
            return false;
        }finally {
            db.closeConnection();
        }
    }


    public User querUserWithName(String username) {
        if (!db.openConnection()) {
            return null;
        }
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(null);
                user.setType(resultSet.getString("type"));
                return user;
            }
            return null;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            db.closeConnection();
        }
    }

    public static void main(String[] args) {
        User user = new User("test","test","user");
        UserService11 userService11 = new UserService11();
        System.out.println(userService11.addUser(user));
        System.out.println( userService11.userExists(user));
    }

}
