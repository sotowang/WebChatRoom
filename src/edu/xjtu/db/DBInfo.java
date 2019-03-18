package edu.xjtu.db;

/**
 * 这里存放数据库基本信息
 */
public class DBInfo {
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String username = "root";
    private final String password = "123456";
    private final String driver = "com.mysql.jdbc.Driver";

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
