package com.darren.center.springboot.dao.write;

import com.darren.center.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserWriterDao {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}