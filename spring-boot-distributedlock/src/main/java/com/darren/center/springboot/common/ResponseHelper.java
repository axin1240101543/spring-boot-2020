package com.darren.center.springboot.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 响应类
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseHelper implements Serializable{

    private static final long serialVersionUID = -4908915966053299827L;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 总记录数（分页）
     */
    private Integer count;

    /**
     * 数据列表
     */
    private List<?> data;

    private static ResponseHelper response = null;
    private static List<?> list = new ArrayList<>();


    /**
     * 单例模式（懒汉型）
     * @return
     */
    public static ResponseHelper getInstanse(){
        if (null == response){
            synchronized (ResponseHelper.class){
                if (null == response){
                    response = new ResponseHelper();
                }
            }
        }
        init();
        return response;
    }

    /**
     * 初始化list
     */
    private static void init(){
        if (null == list){
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        response.setData(list);
    }

}
