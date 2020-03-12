package com.darren.center.springboot.entity;

import com.darren.center.springboot.designmode.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Builder模式
 */
@Slf4j
public class TestRequest {

    private final int type;
    private final long userId;
    private final String userName;
    private final String password;
    private final String mobile;
    private final int age;

    public TestRequest(int type, long userId, String userName, String password, String mobile, int age) {
        this.type = type;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.age = age;
    }

    /**
     * 如果在各个模式bean中能够注入Spring的bean，如果能够注入，那么将大大的扩展其使用方式。
     * 因为我们就可以真的实现通过传入的简单的几个参数，然后结合Spring注入的bean进行一定的处理后，以构造出我们所需要的某个bean。
     *
     * 使用Spring维护一个Builder模式的实例，添加@Component和@Scope("prototype")注解。
     * 1、必须使用prototype类型，因为Builder是有状态的，无法被多线程共享。
     * 2、在build()方法中，通过传入的参数和注入的bean来完成业务处理，最后得到需要的bean。
     * 3、Builder需要使用static修饰，因为内部类不用static修饰会依赖外部类的一个实例，此处是为了使用内部类来构建外部类的一个实例（内部类实例存在时，外部类实例还不存在）。
     * 4、外部类的参数需要使用final修饰，只需要生成getter方法即可。
     */
    @Component
    @Scope("prototype")
    public static class Builder{

        @Autowired
        UserService userService;

        private long userId;
        private int type;

        public Builder userId(long userId){
            this.userId = userId;
            return this;
        }

        public Builder type(int type){
            this.type = type;
            return this;
        }

        public TestRequest build(){
            //业务处理后，得到想要的bean
            Users user = userService.getUserById(userId);
            log.info("userId:{}, user:{}", userId, user);
            return new TestRequest(type, userId, user.getUserName(), user.getPassword(), user.getMobile(), user.getAge());
        }

    }

    public int getType() {
        return type;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public int getAge() {
        return age;
    }
}
