package com.darren.center.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用普通的java类
 * 1、从beetl效率角度来讲，采用普通类效率不如实现Function调用
 * 2、采用的普通java类尽量少同名方法。这样效率更低。beetl调用到第一个适合的同名方法。而不像java那样找到最匹配的
 * 3、方法名支持可变数组作为参数
 * 4、方法名最后一个参数如果是Context，则beetl会传入这个参数。
 *
 * 页面使用：${@com.darren.center.springboot.utils.TimeUtils.formatDate(users[0].createTime!,"yyyy-MM-dd HH:mm:ss")}
 */
public class TimeUtils {

    public static String formatDate(Date date, String format){
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
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
