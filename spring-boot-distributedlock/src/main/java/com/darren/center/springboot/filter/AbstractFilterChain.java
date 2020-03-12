package com.darren.center.springboot.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractFilterChain implements FilterChain{

    protected List<Filter> filters;

    @Override
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        if (filters != null && filters.size() > 0){
            Filter firstFilter = null;
            Filter tmpFilter = null;
            for (int i = 0; i < filters.size(); i++){
                if (i == 0){
                    firstFilter = filters.get(0);
                }else {
                    tmpFilter.setSuccessor(filters.get(i));
                }
                tmpFilter = filters.get(i);
            }
            firstFilter.doFilter(request, response);
        }
    }
}
