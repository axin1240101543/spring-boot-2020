package com.darren.center.springboot.config;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeetlConfig {

    /**
     * 模版根目录，比如：templates
     */
    @Value("${beetl.templatesPath}")
    String templatesPath;

    @Value("${beetl.title}")
    String title;

    /**
     * 设置共享变量
     * @param beetlGroupUtilConfiguration
     * @return
     */
    @Bean
    public GroupTemplate getGroupTemplate(BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        GroupTemplate gt = beetlGroupUtilConfiguration.getGroupTemplate();
        Map<String,Object> shared = new HashMap<>();
        shared.put("beetlTitle", title);
        gt.setSharedVars(shared);
        return gt;
    }

    /**
     * 配置Beetl的模板目录
     * @return
     */
    @Bean
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        //获取Spring Boot 的ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader == null){
            loader = BeetlConfig.class.getClassLoader();
        }
        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, templatesPath);
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
        return beetlGroupUtilConfiguration;

    }

    /**
     * 配置视图解析器
     * @param beetlGroupUtilConfiguration
     * @return
     */
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

}
