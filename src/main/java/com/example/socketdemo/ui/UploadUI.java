package com.example.socketdemo.ui;

import com.example.socketdemo.entity.File;
import com.example.socketdemo.socket.SocketClient;
import com.example.socketdemo.util.CommandTransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 030
 * @date 1:09 2021/11/10
 * @description 文件上传界面，主要控件有
 * 一个选择文件按钮  一个确认上传按钮，一个显示文件路径的文本框
 */
public class UploadUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = -8830003248247613172L;
    private JFileChooser fileChooser;//文件选择对话框
    private JButton upload_btn, choose_btn;//上传按钮 和选择按钮
    private JTextField path_txt;//文件的绝对路径
    private String username;//成功登录的用户名

    public UploadUI(String username) {
        this.username = username;
        init();
        setTitle("上传文件");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void init() {
        // TODO Auto-generated method stub
        setLayout(new FlowLayout());
        choose_btn = new JButton("选择");
        upload_btn = new JButton("上传");
        fileChooser = new JFileChooser();
        path_txt = new JTextField(20);
        add(path_txt);
        add(choose_btn);
        add(upload_btn);
        choose_btn.addActionListener(this);
        upload_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // 如果点击了选择文件
        if (e.getSource() == choose_btn) {
            //打开选择文件对话框
            int state = fileChooser.showSaveDialog(null);
            if (state == 0) {
                String pathChoose = fileChooser.getSelectedFile().getPath();
                path_txt.setText(pathChoose);
            }
        }
        // 如果点击了上传按钮
        if (e.getSource() == upload_btn) {
            if (path_txt.getText().trim() == null || "".equals(path_txt.getText().trim())) {
                JOptionPane.showMessageDialog(null, "请先选择文件再上传！！");
                return;
            }
            uploadFile();
        }
    }

    //上传文件
    private void uploadFile() {
        // TODO Auto-generated method stub
        File file = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        String path = path_txt.getText().trim();
        String fname = path.substring(path.lastIndexOf("\\") + 1);
        try {
            fis = new FileInputStream(path);
            // fis.available返回文件的总大小
            byte[] fcontent = new byte[fis.available()];
            bis = new BufferedInputStream(fis);
            //读取文件内容
            bis.read(fcontent);
            //实例化file对象
            file = new File(fname, username, fcontent);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        //向服务器发送文件
        CommandTransfer cmd = new CommandTransfer();
        cmd.setData(file);
        cmd.setCmd("uploadFile");
        try {
            SocketClient.socket = new Socket(SocketClient.address,
                    SocketClient.port);
            //向服务器发送数据
            SocketClient.sendData(cmd);
            //获得服务器反馈信息
            cmd = SocketClient.getData();
            JOptionPane.showMessageDialog(null, cmd.getResult());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
