package com.darren.center.springboot.context;

import com.alibaba.fastjson.JSONObject;
import com.darren.center.springboot.controller.BaseController;
import com.darren.center.springboot.filter.AbstractFilter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ContextFilter extends AbstractFilter {

    @Autowired
    private ContextHelper contextHelper;

    @Override
    public void handlerFilter(HttpServletRequest request, HttpServletResponse response) {
        Context context = contextHelper.generatorContext();
        context.setClientIp(BaseController.getIpAddr(request));
        request.setAttribute(BaseController.CONTEXT, context);
        log.info("uri:{}, context:{}", request.getRequestURI(), JSONObject.toJSONString(context));
    }
}
