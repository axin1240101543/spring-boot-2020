package com.darren.center.springboot.filter;

import com.darren.center.springboot.context.ContextFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WebComponentFilter implements Filter{

    @Autowired
    private WebComponentFilterChain webComponentFilterChain;
    @Autowired
    private ContextFilter contextFilter;
    @Autowired
    private Filter1 filter1;
    @Autowired
    private Filter2 filter2;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
/*        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        ContextFilter contextFilter = (ContextFilter) applicationContext.getBean("contextFilter");*/
        List<com.darren.center.springboot.filter.Filter> filters = new ArrayList<>();
        filters.add(contextFilter);
        filters.add(filter1);
        filters.add(filter2);
        webComponentFilterChain.setFilters(filters);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (webComponentFilterChain != null){
            webComponentFilterChain.handler(request, response);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
