package com.darren.center.springboot.command;

public abstract class AbstractAction implements Action{

    public AbstractAction(String serviceCode, ActionMapper actionMapper) {
        super();
        if (null == serviceCode){
            throw new IllegalArgumentException("no path for " + this.getClass());
        }
        if (null != actionMapper){
            actionMapper.addAction(serviceCode, this);
        }
    }
}
