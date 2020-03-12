package com.darren.center.springboot.security;

import com.darren.center.springboot.entity.Menu;
import com.darren.center.springboot.entity.User;
import com.darren.center.springboot.service.MenuService;
import com.darren.center.springboot.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //认证帐号
        User user = userService.loadUserByUsername(s);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("帐号不存在");
        }
        //开始授权
        List<Menu> menuList = menuService.getMenuByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(menuList)){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Menu menu:menuList){
                //将权限信息添加到GrantedAuthority对象中
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
                grantedAuthorities.add(grantedAuthority);
            }
            user.setAuthorities(grantedAuthorities);
        }
        return user;
    }
}
