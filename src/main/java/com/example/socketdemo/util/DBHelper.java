package com.example.socketdemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 030
 * @date 1:10 2021/11/10
 * @description 数据库工具类
 */
public class DBHelper {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/socket_upload_demo?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "123456";
    private static Connection con = null;

    //静态块代码负责加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        if (con == null) {
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return con;
    }

}
