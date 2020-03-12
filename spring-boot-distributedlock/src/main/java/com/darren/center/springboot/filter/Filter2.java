package com.darren.center.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Filter2 extends AbstractFilter{

    @Override
    public void handlerFilter(HttpServletRequest request, HttpServletResponse response) {
        log.info("Filter2 request url:{}", request.getRequestURL());
    }
}
