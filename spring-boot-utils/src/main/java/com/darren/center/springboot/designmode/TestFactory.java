package com.darren.center.springboot.designmode;

import com.darren.center.springboot.entity.TestRequest;
import com.darren.center.springboot.exceptions.UtilsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 工厂方法模式：工厂方法模式，就是定义一个工厂方法，通过传入的参数，返回某个实例，然后通过该实例来处理后续的业务逻辑。
 * 工厂方法的返回值类型是一个接口类型，而选择具体子类实例的逻辑则封装到了工厂方法中了。通过这种方式，来将外层调用逻辑与具体的子类的获取逻辑进行分离。
 *
 */
@Slf4j
@Component
public class TestFactory {

    @Autowired
    private List<TestService> services;

    public TestService getService(TestRequest request){
        if (CollectionUtils.isEmpty(services)){
            throw UtilsException.UNKNOWN_EXCEPTION;
        }
        for (TestService service : services){
            //当前实现了TestService的bean是否支持当前request的处理，是由具体的子类实现的
            if (service.support(request)){
                return service;
            }
        }
        throw new UnsupportedOperationException("unsupported request: " + request);
    }

}
