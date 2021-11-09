package com.example.socketdemo.ui;

import com.example.socketdemo.socket.SocketServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 030
 * @date 1:08 2021/11/10
 * @description 开启服务端界面，需要注意一点、
 * 在线程中开启服务器 避免使用main线程 服务器一直开启
 * main线程一直阻塞 无法对其它事物进行处理
 */
public class StartServer extends JFrame implements ActionListener {

    private static final long serialVersionUID = 3254784569816648178L;
    private JButton startServer_btn;
    private JButton endServer_btn;
    private SocketServer startService;

    public StartServer() {
        setLayout(new FlowLayout());
        startServer_btn = new JButton("开启服务");
        endServer_btn = new JButton("关闭服务");
        add(startServer_btn);
        add(endServer_btn);
        setTitle("服务端");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        startServer_btn.addActionListener(this);
        endServer_btn.addActionListener(this);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new StartServer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == startServer_btn) {
            if (startService == null) {
                /*
                 * 在线程中开启服务器  避免使用main线程 服务器一直开启
                 * main线程一直阻塞  无法对其它事物进行处理
                 */
                new startServerThread().start();
            }
        }
        //退出服务器
        if (e.getSource() == endServer_btn) {
            startService = null;
            System.exit(0);
        }
    }

    private class startServerThread extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            startService = new SocketServer();
        }
    }

}
