package com.example.socketdemo.socket;

import com.example.socketdemo.util.CommandTransfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 030
 * @date 1:02 2021/11/10
 * @description Client类实现与服务端的通信
 */
public class SocketClient {

    public static Socket socket = null;
    public static int port = 8888;
    public static String address = "127.0.0.1";

    //获得服务器的数据
    public static CommandTransfer getData() {
        // TODO Auto-generated method stub
        ObjectInputStream ois = null;
        CommandTransfer res = null;
        try {
            ois = new ObjectInputStream(SocketClient.socket.getInputStream());
            res = (CommandTransfer) ois.readObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    //向服务器发送数据
    public static void sendData(CommandTransfer cmd) {
        // TODO Auto-generated method stub
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(SocketClient.socket.getOutputStream());
            oos.writeObject(cmd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
