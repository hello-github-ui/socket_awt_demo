package com.example.socketdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 030
 * @date 0:51 2021/11/10
 * @description 用户的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1270658002188172957L;

    private String username;

    private String password;
}
