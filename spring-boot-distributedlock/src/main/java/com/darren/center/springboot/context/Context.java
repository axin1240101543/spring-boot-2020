package com.darren.center.springboot.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Context implements Serializable{

    private String msgSeq;

    private String clientIp;

    private Map<String, Object> attrs;


    public <T> void setAttr(String key, T value) {
        if (attrs == null){
            attrs = new HashMap<>();
        }
        this.attrs.put(key, value);
    }

    public <T> T getAttr(String key) {
        if (attrs != null){
            return (T) this.attrs.get(key);
        }
        return null;
    }


}
