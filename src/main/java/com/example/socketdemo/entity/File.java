package com.example.socketdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 030
 * @date 0:57 2021/11/10
 * @description 文件的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {

    private static final long serialVersionUID = 3205614871184076152L;

    private String filename;

    private String username;

    private byte[] filecontent;
}
