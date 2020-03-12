package com.darren.center.springboot.service.impl;

import com.darren.center.springboot.dao.read.DictReaderDao;
import com.darren.center.springboot.entity.Dict;
import com.darren.center.springboot.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DictServiceImpl implements DictService{

    @Autowired
    private DictReaderDao dictReaderDao;

    @Override
    public List<Dict> selectDictByType(String type) {
        return dictReaderDao.selectDictByType(type);
    }
}
