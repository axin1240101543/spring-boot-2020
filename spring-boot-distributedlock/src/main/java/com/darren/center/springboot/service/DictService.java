package com.darren.center.springboot.service;

import com.darren.center.springboot.entity.Dict;

import java.util.List;

public interface DictService {
    /**
     * 查询字典（根据类型）
     * @param type
     * @return
     */
    List<Dict> selectDictByType(String type);

}
