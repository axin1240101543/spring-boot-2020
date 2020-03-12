package com.darren.center.springboot.controller;

import com.darren.center.springboot.annotation.RequestLimit;
import com.darren.center.springboot.common.Constants;
import com.darren.center.springboot.common.ResponseHelper;
import com.darren.center.springboot.common.WebStatusEnum;
import com.darren.center.springboot.entity.Dict;
import com.darren.center.springboot.entity.User;
import com.darren.center.springboot.service.DictService;
import com.darren.center.springboot.service.UserService;
import com.darren.center.springboot.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * http://localhost:8881/spring-boot/user/list
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private DictService dictService;

    @RequestMapping("/test")
    public String test(){
        return "user/test";
    }

    /**
     * 访问主页
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        List<Dict> data = dictService.selectDictByType(Constants.USER_SEX);
        model.addAttribute("data", data);
        return "user/list";
    }

    /**
     * 返回json数据
     * @param params
     * @return
     */
    @RequestMapping("/listPage")
    @ResponseBody
    public ResponseHelper listPage(@RequestParam Map<String, Object> params){
        List<User> users = userService.selectUserList(params);
        Integer pageCount = userService.selectUserListPageCount(params);
        return ResponseUtils.response(WebStatusEnum.SUCCESS.getCode(), WebStatusEnum.SUCCESS.getMsg(), users, pageCount);
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        User user = userService.selectByPrimaryKey(Long.valueOf(id));
        model.addAttribute("user", user);
        return "user/edit";
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    @RequestLimit(count = 5)
    public ResponseHelper save(User user){
        int flag;
        if (null == user.getId()){
            flag = userService.addUser(user);
        }else {
            flag = userService.editUserById(user);
        }
        return ResponseUtils.responseWrap(flag);
    }
}
