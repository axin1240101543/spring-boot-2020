package com.darren.center.springboot.enums;

import lombok.Getter;

@Getter
public enum ActionEnum {

    /**
     * 1
     */
    SERVICE_1("Action1"),

    /**
     * 2
     */
    SERVICE_2("Action2");

    private String service;

    ActionEnum(String service) {
        this.service = service;
    }
}
