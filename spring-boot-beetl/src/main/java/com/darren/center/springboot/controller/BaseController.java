package com.darren.center.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.springboot.common.Constants;
import com.darren.center.springboot.entity.Blog;
import com.darren.center.springboot.service.DictService;
import com.darren.center.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private DictService dictService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        log.info("index --- begin ---");
        request.setAttribute("beetl","官网www.ibeetl.com");
        request.setAttribute("test","springboot 集成 beetl 一起来学呀");
        Map<String, Object> params = new HashMap<>();
        params.put("offset", "0");
        params.put("limit", "10");
        request.setAttribute("users", userService.selectUserList(params));
        log.info("index --- end ---");
        return "index.html";
    }

    @GetMapping("/index2")
    public String index2(HttpServletRequest request){
        log.info("index2 --- begin ---");
        Map<String, Object> params = new HashMap<>();
        params.put("offset", "0");
        params.put("limit", "10");
        request.setAttribute("users", userService.selectUserList(params));
        log.info("index2 --- end ---");
        return "index2.html";
    }

    /**
     * ajax局部渲染
     * 前端语法：#ajax tags:{……}
     * 后台语法：@GetMapping("/tags") …… return "index3.html#tags";
     * @param request
     * @return
     */
    @GetMapping("/tags")
    public String tags(HttpServletRequest request){
        log.info("index3 --- begin ---");
        request.setAttribute("dicts", dictService.selectDictByType(Constants.USER_SEX));
        log.info("index3 --- end ---");
        return "index3.html#tags";
    }

    @GetMapping("/createBlog")
    public String createBlog(HttpServletRequest request){
        return "create.html";
    }

    @PostMapping("/saveBlog")
    public String saveBlog(Blog blog, HttpServletRequest request){
        log.info(JSONObject.toJSONString(blog));
        return "redirect:/createBlog";
    }

}
