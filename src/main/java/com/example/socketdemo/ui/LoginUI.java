package com.example.socketdemo.ui;

import com.example.socketdemo.entity.User;
import com.example.socketdemo.socket.SocketClient;
import com.example.socketdemo.util.CommandTransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 030
 * @date 1:06 2021/11/10
 * @description 客户端登录界面loginUI类
 */
public class LoginUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = -2686222552198867018L;
    private Box box1, box2, box3, baseBox;
    private JLabel username, password;
    private JTextField username_txt;
    private JPasswordField password_txt;
    private JButton login_btn, register_btn;

    public LoginUI() {
        setLayout(new FlowLayout());
        init();
        setTitle("客户端");
        setSize(200, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void init() {
        username = new JLabel("账户");
        password = new JLabel("密码");
        username_txt = new JTextField(10);
        password_txt = new JPasswordField(10);
        login_btn = new JButton("登录");
        register_btn = new JButton("注册");
        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();
        box3 = Box.createHorizontalBox();
        box1.add(username);
        box1.add(Box.createHorizontalStrut(8));
        box1.add(username_txt);
        box2.add(password);
        box2.add(Box.createHorizontalStrut(8));
        box2.add(password_txt);
        box3.add(login_btn);
        box3.add(Box.createHorizontalStrut(8));
        box3.add(register_btn);
        baseBox = Box.createVerticalBox();
        baseBox.add(box1);
        baseBox.add(Box.createVerticalStrut(5));
        baseBox.add(box2);
        baseBox.add(Box.createVerticalStrut(5));
        baseBox.add(box3);
        baseBox.add(Box.createVerticalStrut(5));
        add(baseBox);
        login_btn.addActionListener(this);
        register_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //如果点击了登录按钮  判断账户在服务器是否存在
        if (e.getSource() == login_btn) {
            User user = new User();
            user.setUsername(username_txt.getText().trim());
            user.setPassword(new String(password_txt.getPassword()).trim());
            CommandTransfer cmd = new CommandTransfer();
            cmd.setData(user);
            cmd.setCmd("login");
            try {
                SocketClient.socket = new Socket(SocketClient.address, SocketClient.port);
                //向服务器发送数据
                SocketClient.sendData(cmd);
                //获得服务器发送的数据
                cmd = SocketClient.getData();
                JOptionPane.showMessageDialog(null, cmd.getResult());
                //如果登录成功  关闭此窗口 开启上传窗口
                if (cmd.isFlag()) {
                    this.dispose();
                    new UploadUI(user.getUsername());
                }
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "服务端未开启！");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "服务端未开启！");
            }
        }
        //如果点击了注册按钮  打开注册界面
        if (e.getSource() == register_btn) {
            new RegisterUI();
        }
    }
}
