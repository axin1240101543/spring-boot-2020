package com.darren.center.springboot.command;

public interface ActionMapper {

    void addAction(String key, Action action);

    Action findAction(String key);

}
