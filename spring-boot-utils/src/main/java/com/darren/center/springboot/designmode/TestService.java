package com.darren.center.springboot.designmode;

import com.darren.center.springboot.entity.TestRequest;

/**
 * 策略模式：策略模式就是一个接口下有多个实现类，而每种实现类会处理某一种情况。
 *
 * 无论是support()方法还是handle()方法，都需要传一个对象进行，而不是简简单单的基本类型的变量，
 * 这样做的好处是后续如果要在Request中新增字段，那么就不需要修改接口的定义和已经实现的各个子类的逻辑；
 */
public interface TestService {

    /**
     * 声明一个返回boolean值的类似于support()的方法，通过这个方法来控制当前实例是否为处理目标request的实例；
     * @param request
     * @return
     */
    boolean support(TestRequest request);

    /**
     * 声明一个类似于handle()的方法用于处理业务逻辑，当然根据各个业务的不同声明的方法名肯定是不同的，这里只是一个对统一的业务处理的抽象；
     * @param request
     */
    void handle(TestRequest request);

}
