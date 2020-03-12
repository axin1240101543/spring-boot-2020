package com.darren.center.springboot.dao.read;

import com.darren.center.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserReaderDao {

    User selectByPrimaryKey(Long id);

    List<User> selectUserList(Map<String, Object> params);

    Integer selectUserListPageCount(Map<String, Object> params);

}