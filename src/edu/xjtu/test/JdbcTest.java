package edu.xjtu.test;

import edu.xjtu.db.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    private static   final String sql = "select * from user where username = ?";
    private static  Connection connection = null;
    public static boolean test() {
        DataBase db = new DataBase();
        if (!db.openConnection()) {
            System.out.println("连接失败");
            return false;
        }
        connection = db.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "aaa");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("nickname"));
                return true;

            }
            return false;

        } catch (SQLException e) {
            System.out.println("查询失败  " + e.getMessage());
            return false;
        }finally {
            db.closeConnection();
        }


    }


    public static void main(String[] args) {
        test();
    }

}
