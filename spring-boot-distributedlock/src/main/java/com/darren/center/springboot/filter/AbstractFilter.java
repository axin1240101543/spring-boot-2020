package com.darren.center.springboot.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractFilter implements Filter {

    protected Filter successor;

    public Filter getSuccessor() {
        return successor;
    }

    @Override
    public void setSuccessor(Filter successor) {
        this.successor = successor;
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response) {
        handlerFilter(request, response);
        if (getSuccessor() != null){
            getSuccessor().doFilter(request, response);
        }
    }

    public abstract void handlerFilter(HttpServletRequest request, HttpServletResponse response);
}
