package com.darren.center.springboot.enums;

import lombok.Getter;

@Getter
public enum  TestEnum {

    TYPE1(1), TYPE2(2), TYPE3(3);

    private int type;

    TestEnum(int type) {
        this.type = type;
    }
}
