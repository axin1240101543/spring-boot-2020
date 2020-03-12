package com.darren.center.springboot.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FilterChain {

    /**
     * 构建处理链
     * @param request
     * @param response
     */
    void handler(HttpServletRequest request, HttpServletResponse response);

}
