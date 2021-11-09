package com.example.socketdemo.socket;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 030
 * @date 1:03 2021/11/10
 * @description Server 类服务端主要实现监听客户端的连接
 */
public class SocketServer {

    public SocketServer() {
        try {
            ServerSocket ss = new ServerSocket(8888);
            Socket socket = null;
            JOptionPane.showMessageDialog(null, "服务器已启动!请连接...");
            //循环监听客户端的连接
            while (true) {
                socket = ss.accept();
                //为每个客户开启一个线程
                ServiceThread serviceThread = new ServiceThread(socket);
                Thread thread = new Thread(serviceThread);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
