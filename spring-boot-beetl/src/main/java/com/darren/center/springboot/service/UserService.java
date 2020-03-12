package com.darren.center.springboot.service;

import com.darren.center.springboot.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User selectByPrimaryKey(Long id);


    List<User> selectUserList(Map<String, Object> params);

    Integer selectUserListPageCount(Map<String, Object> params);

    int editUserById(User user);

    int deleteUserById(Long id);

    int addUser(User user);

}
