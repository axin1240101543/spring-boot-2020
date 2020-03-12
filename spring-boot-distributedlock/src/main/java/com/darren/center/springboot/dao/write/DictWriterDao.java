package com.darren.center.springboot.dao.write;

import com.darren.center.springboot.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictWriterDao {

    int deleteByPrimaryKey(Long id);

    int insert(Dict record);

    int insertSelective(Dict record);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}