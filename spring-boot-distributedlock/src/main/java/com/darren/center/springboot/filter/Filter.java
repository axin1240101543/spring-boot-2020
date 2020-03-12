package com.darren.center.springboot.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Filter {

    public boolean useDebug = true;

    /**
     * 处理过滤方法
     * @param request
     * @param response
     */
    public void doFilter(HttpServletRequest request, HttpServletResponse response);

    /**
     * 设置过滤类
     * @param successor
     */
    public void setSuccessor(Filter successor);

}
