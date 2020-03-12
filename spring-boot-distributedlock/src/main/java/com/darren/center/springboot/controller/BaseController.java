package com.darren.center.springboot.controller;

import com.darren.center.springboot.context.Context;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public final static String CONTEXT = "darren.context";

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Context getContext(HttpServletRequest request) {
        return (Context) request.getAttribute(CONTEXT);
    }

}
