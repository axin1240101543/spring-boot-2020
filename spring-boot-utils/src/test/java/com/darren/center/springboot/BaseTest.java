package com.darren.center.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @RunWith : 运行器
 * @RunWith(JUnit4.class)就是指用JUnit4来运行
 * @RunWith(SpringJUnit4ClassRunner.class),让测试运行于Spring测试环境
 * @RunWith(Suite.class)的话就是一套测试集合，
 *
 * @WebAppConfiguration("src/main/resources"):
 * 注解在类上,用来声明加载的ApplicationContex 是一个WebApplicationContext ,它的属性指定的是Web资源的位置,默认为 src/main/webapp ,自定义修改为 resource
 *
 * @Before : 在 xxx 前初始化
 *
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration("src/main/resources")
public class BaseTest {

    @Before
    public void init(){
        log.info("测试开始");
    }

    @After
    public void destroy(){
        log.info("测试结束");
    }

    @Test
    public void getHello(){
        log.info("oh, my god!");
    }

}
