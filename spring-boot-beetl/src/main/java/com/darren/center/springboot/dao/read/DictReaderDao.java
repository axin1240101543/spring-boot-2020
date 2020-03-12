package com.darren.center.springboot.dao.read;

import com.darren.center.springboot.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictReaderDao {

    Dict selectByPrimaryKey(Long id);

    List<Dict> selectDictByType(String type);
}