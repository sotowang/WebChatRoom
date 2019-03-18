package edu.xjtu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 实现对数据库的连接控制操作
 */
public class DataBase {
    DBInfo dbInfo = new DBInfo();
    Connection connection = null;

    /**
     * 打开数据库连接
     * @return
     */
    public boolean openConnection() {
        try {
            Class.forName(dbInfo.getDriver());
            connection = DriverManager.getConnection(dbInfo.getUrl(), dbInfo.getUsername(), dbInfo.getPassword());
            if (connection == null) {
                return false;
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeConnection() {
        if (connection != null) {
            try {
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Connection getConnection() {
        return connection;
    }
}
