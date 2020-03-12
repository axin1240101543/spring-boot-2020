package com.darren.center.springboot.designmode;

import com.darren.center.springboot.entity.TestRequest;
import com.darren.center.springboot.enums.TestEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestService2Impl implements TestService{

    @Override
    public boolean support(TestRequest request) {
        return request.getType() == TestEnum.TYPE2.getType();
    }

    @Override
    public void handle(TestRequest request) {
        log.info("type:{}", 2);
    }
}
