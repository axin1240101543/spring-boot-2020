package com.darren.center.springboot.service;

import com.darren.center.springboot.entity.User;

public interface UserService {

    User loadUserByUsername(String s);

}
