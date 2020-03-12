package com.darren.center.springboot.common;

public enum WebStatusEnum {

    SUCCESS("0", "成功"),

    FAILURE("1", "失败"),

    ERROR("2", "系统错误");

    private String code;

    private String msg;

    WebStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
