package com.darren.center.springboot.command;


import com.darren.center.springboot.entity.WebParams;

/**
 * 什么是策略模式
 * 从本质上讲，策略模式就是一个接口下有多个实现类，而每种实现类会处理某一种情况。
 */
public interface Action {

     boolean action(WebParams params);

     String doAction(WebParams params);

}
