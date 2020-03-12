package com.darren.center.springboot.service.impl;

import com.darren.center.springboot.dao.read.UserReaderDao;
import com.darren.center.springboot.dao.write.UserWriterDao;
import com.darren.center.springboot.entity.User;
import com.darren.center.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReaderDao userReaderDao;

    @Autowired
    private UserWriterDao userWriterDao;

    @Override
    public User selectByPrimaryKey(Long id) {
        log.info("methodName:selectByPrimaryKey, id:{}", id);
        return userReaderDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectUserList(Map<String, Object> params) {
        return userReaderDao.selectUserList(params);
    }

    @Override
    public Integer selectUserListPageCount(Map<String, Object> params) {
        return userReaderDao.selectUserListPageCount(params);
    }

    @Override
    public int editUserById(User user) {
        log.info("methodName:editUserById, user:{}", user);
        return userWriterDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUserById(Long id) {
        log.info("methodName:deleteUserById, id:{}", id);
        return userWriterDao.deleteByPrimaryKey(id);
    }

    @Override
    public int addUser(User user) {
        log.info("methodName:addUser, user:{}", user);
        return userWriterDao.insertSelective(user);
    }
}
