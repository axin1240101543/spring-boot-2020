package com.darren.center.springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 姓名 */
    private String name;

    /** 性别 */
    private String sex;

    /** 年龄 */
    private Integer age;

    /** 号码 */
    private String mobile;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 扩展字段 */
    private String extFields;

}