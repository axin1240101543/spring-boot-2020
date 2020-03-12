package com.darren.center.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebParams {

    private static WebParams webParams = null;
    public WebParams() {
    }

    /**
     * 类型
     */
    private String type;

    /**
     * 饿汉模式
     * 双检锁（double checked locking pattern）
     * 为什么要检查两次？
     * 因为可能会有多个线程一起进入同步块外的 if，如果在同步块内不进行二次检验的话就会生成多个实例了
     * @return
     */
    public static WebParams getWebparams(){
        if (null == webParams){
            synchronized (WebParams.class){
                if (null == webParams){
                    webParams = new WebParams();
                }
            }
        }
        return webParams;
    }

}
