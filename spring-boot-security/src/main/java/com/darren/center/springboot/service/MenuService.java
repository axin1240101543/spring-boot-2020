package com.darren.center.springboot.service;

import com.darren.center.springboot.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenuByUserId(long id);

}
