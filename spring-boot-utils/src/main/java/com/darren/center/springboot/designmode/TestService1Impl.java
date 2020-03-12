package com.darren.center.springboot.designmode;

import com.darren.center.springboot.entity.TestRequest;
import com.darren.center.springboot.enums.TestEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 使用@Component注解对当前类进行标注，将其声明为Spring容器所管理的一个bean；
 */
@Slf4j
@Component
public class TestService1Impl implements TestService{

    @Override
    public boolean support(TestRequest request) {
        return request.getType() == TestEnum.TYPE1.getType();
    }

    @Override
    public void handle(TestRequest request) {
        log.info("type:{}", 1);
    }
}
