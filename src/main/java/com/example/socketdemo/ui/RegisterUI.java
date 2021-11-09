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
 * @date 1:07 2021/11/10
 * @description 客户端注册界面RegisterUI类
 */
public class RegisterUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = -717590965372186957L;
    private Box box1, box2, baseBox;
    private JLabel username, password, password2;
    private JTextField username_txt;
    private JPasswordField password_txt, password_txt2;
    private JButton cancel_btn, register_btn;

    public RegisterUI() {
        setLayout(new FlowLayout());
        init();
        setTitle("注册");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void init() {
        // TODO Auto-generated method stub
        username = new JLabel("账户");
        password = new JLabel("密码");
        password2 = new JLabel("再次输入密码");
        username_txt = new JTextField(10);
        password_txt = new JPasswordField(10);
        password_txt2 = new JPasswordField(10);
        cancel_btn = new JButton("取消");
        register_btn = new JButton("注册");
        box1 = Box.createVerticalBox();
        box2 = Box.createVerticalBox();
        box1.add(username);
        box1.add(Box.createVerticalStrut(10));
        box1.add(password);
        box1.add(Box.createVerticalStrut(10));
        box1.add(password2);
        box1.add(Box.createVerticalStrut(10));

        box2.add(username_txt);
        box2.add(Box.createVerticalStrut(10));
        box2.add(password_txt);
        box2.add(Box.createVerticalStrut(10));
        box2.add(password_txt2);
        box2.add(Box.createVerticalStrut(10));
        baseBox = Box.createHorizontalBox();
        baseBox.add(box1);
        baseBox.add(Box.createHorizontalStrut(5));
        baseBox.add(box2);
        add(baseBox);
        add(register_btn);
        add(cancel_btn);
        cancel_btn.addActionListener(this);
        register_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == register_btn) {
            String username = username_txt.getText().trim();
            String password1 = new String(password_txt.getPassword()).trim();
            String password2 = new String(password_txt2.getPassword()).trim();
            if (username == null || "".equals(username)) {
                JOptionPane.showMessageDialog(null, "请输入用户名！！");
                return;
            }
            if (password1 == null || "".equals(password1)) {
                JOptionPane.showMessageDialog(null, "请输入密码！！");
                return;
            }
            if (!(password1.equals(password2))) {
                JOptionPane.showMessageDialog(null, "两次输入的密码不一致！！");
                return;
            }
            //向服务器发送注册信息
            User user = new User(username, password1);
            CommandTransfer cmd = new CommandTransfer();
            cmd.setCmd("register");
            cmd.setData(user);
            try{
                SocketClient.socket = new Socket(SocketClient.address, SocketClient.port);
                //向服务器发送数据
                SocketClient.sendData(cmd);
                //获得服务器发送的数据
                cmd = SocketClient.getData();
                JOptionPane.showMessageDialog(null, cmd.getResult());
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        //初始化文本框
        if (e.getSource() == cancel_btn) {
            username_txt.setText(null);
            password_txt.setText(null);
            password_txt2.setText(null);
        }
    }

}
