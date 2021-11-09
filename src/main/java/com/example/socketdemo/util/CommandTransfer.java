package com.example.socketdemo.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 030
 * @date 1:10 2021/11/10
 * @description CommandTransfer类 主要负责客户端向服务端发送数据以及服务器向客户端返回的数据
 */
@Data
public class CommandTransfer implements Serializable {
    private static final long serialVersionUID = -5551375256714790282L;
    private String cmd;//操作指令
    private Object data;//发送的数据
    private boolean flag;//操作是否成功
    private String result;//返回的结果
}
