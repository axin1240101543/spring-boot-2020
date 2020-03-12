package com.darren.center.springboot;

import com.alibaba.fastjson.JSON;
import com.darren.center.springboot.command.Action;
import com.darren.center.springboot.command.ActionMapper;
import com.darren.center.springboot.designmode.TestFactory;
import com.darren.center.springboot.designmode.TestService;
import com.darren.center.springboot.entity.TestRequest;
import com.darren.center.springboot.entity.WebParams;
import com.darren.center.springboot.exceptions.UtilsException;
import com.darren.center.springboot.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration("src/main/resources")
public class DesignModeTest {

    @Resource(name = "Action-BaseActionMapper")
    private ActionMapper mapper;

    @Autowired
    private TestFactory factory;

    /**
     * 老方式
     */
    @Test
    public void test(){
        WebParams webParams = WebParams.getWebparams();
        webParams.setType("Action1");
        Action action = mapper.findAction(webParams.getType());
        String result = null;
        try {
            boolean flag = action.action(webParams);
            if (flag){
                result = action.doAction(webParams);
            }
            log.info(result);
        }catch (UtilsException e){
            UtilsException biz = UtilsException.UNKNOWN_EXCEPTION;
            log.error("code:{}, msg:{}", biz.getCode(), biz.getMsg());
        }
    }

    /**
     * 新方式
     */
    @Test
    public void factory(){
        TestRequest request = ApplicationContextUtils.getBean(TestRequest.Builder.class)
                .type(1)
                .userId(123456)
                .build();
        System.out.println(JSON.toJSONString(request));
        TestService service = factory.getService(request);
        service.handle(request);
    }

}
