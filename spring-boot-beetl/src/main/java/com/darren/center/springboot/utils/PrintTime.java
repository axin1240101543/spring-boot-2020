package com.darren.center.springboot.utils;

import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义方法
 * 注册：在beetl.properties 中，增加 FN.printTime = com.ibeetl.blog.function.PrintTime 进行注册。
 * 页面使用：${printTime(msg.createTime!,"yyyy-MM-dd HH:mm:ss")}  注意使用时，是首字母是小写
 */
public class PrintTime implements Function{

    /**
     *
     * @param objects 模板传递过来的参数列表
     *                （byteWriter：输出流、template：模板本身、
     *                  gt：GroupTemplate、
     *                  globalVar：该模板对应的全局变量、
     *                  byteOutputMode：模板的输出模式，字符还是字节、
     *                  safeOutput：模板当前是否处于安全输出模式）
     * @param context beetl模板的上下文context
     * @return Object
     *  如果无返回，返回null即可
     *  为了便于类型判断，最好返回一个具体的类
     *  方法中任何异常应该抛出Runtime异常
     */
    @Override
    public Object call(Object[] objects, Context context) {
        Date date = (Date)objects[0];
        Date now = new Date();
        Long fiveM = date.getTime() + (5*60*1000);
        Long thM = date.getTime() + (30*60*1000);
        Long oneH = date.getTime() + (60*60*1000);
        if(now.getTime() < fiveM){
            return "刚刚";
        }
        if(now.getTime() < thM){
            return "半小时前";
        }
        if(now.getTime() < oneH){
            return "一小时前";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(objects[1].toString());
        return sdf.format(date);
    }
}
