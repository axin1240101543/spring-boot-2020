package com.darren.center.springboot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * spring 上下文工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class ApplicationContextUtils implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据名称获取IOC容器的Bean
     * @param name IOC容器bean名称
     * @return 返回Bean
     */
    public static Object getBean(String name){
        Assert.notNull(applicationContext, "applicationContext is must not be none");
        return applicationContext.getBean(name);
    }

    /**
     * 根据类型返回IOC容器的bean
     * @param c 需要返回bean 的类型
     * @param <T> 泛型
     * @return 返回Bean
     */
    public static <T> T getBean(Class<T> c){
        Assert.notNull(applicationContext, "applicationContext is must not be none");
        return applicationContext.getBean(c);
    }

}
