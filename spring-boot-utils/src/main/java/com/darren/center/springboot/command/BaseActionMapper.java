package com.darren.center.springboot.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("Action-BaseActionMapper")
public class BaseActionMapper implements ActionMapper{

    protected Map<String, Action> mapper = new HashMap<>(40);

    @Override
    public synchronized void addAction(String key, Action action) {
        mapper.put(key, action);
    }

    @Override
    public Action findAction(String key) {
        return mapper.get(key);
    }
}
