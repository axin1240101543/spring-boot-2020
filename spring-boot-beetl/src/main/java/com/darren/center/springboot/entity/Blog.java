package com.darren.center.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable{

    private String title;

    private String category;

    private String img;

    private String content;

}
