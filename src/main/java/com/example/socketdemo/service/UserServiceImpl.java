package com.example.socketdemo.service;

import com.example.socketdemo.entity.User;
import com.example.socketdemo.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 030
 * @date 1:01 2021/11/10
 * @description UserService类主要实现用户的登录和注册
 */
public class UserServiceImpl {
    private Connection conn = null;
    private PreparedStatement ps = null;

    //检测账户在数据库是否存在
    public boolean checkUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        try {
            conn = DBHelper.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int n = rs.getRow();
            if (n > 0) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    //在数据库中添加注册的用户
    public boolean addUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        try {
            conn = DBHelper.getConnection();
            String sql = "insert into user (username,password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }
}
