package com.example.socketdemo.service;

import com.example.socketdemo.entity.File;
import com.example.socketdemo.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 030
 * @date 0:59 2021/11/10
 * @description FileServiceImpl 类实现客户端文件向服务器数据库的上传
 */
public class FileServiceImpl {
    //上传文件
    public boolean uploadFile(File file) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = DBHelper.getConnection();
        String sql = "insert into file (username,filename,filecontent) values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, file.getUsername().trim());
            ps.setString(2, file.getFilename().trim());
            ps.setBytes(3, file.getFilecontent());
            int n = ps.executeUpdate();
            if (n > 0) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
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
