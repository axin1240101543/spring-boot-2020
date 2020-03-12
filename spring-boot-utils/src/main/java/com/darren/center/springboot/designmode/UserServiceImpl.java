package com.darren.center.springboot.designmode;

import com.darren.center.springboot.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Override
    public Users getUserById(long userId) {
        Users user = new Users();
        user.setUserId(123456);
        user.setUserName("Darren");
        user.setPassword("666666");
        user.setAge(22);
        user.setMobile("13838380438");
        user.setType(1);
        return user;
    }
}
