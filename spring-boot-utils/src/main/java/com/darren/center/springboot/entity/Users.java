package com.darren.center.springboot.entity;

import lombok.Data;

@Data
public class Users {

    private long userId;

    private String userName;

    private String password;

    private int age;

    private String mobile;

    private int type;

}
