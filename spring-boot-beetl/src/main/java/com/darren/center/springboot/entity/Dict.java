package com.darren.center.springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 */
@Data
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 字典编码 */
    private String dictCode;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

    /** 字典详情 */
    private String dictDesc;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 扩展字段 */
    private String extFields;

}