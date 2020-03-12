package com.darren.center.springboot.filter;

import java.util.List;

public class WebComponentFilterChain extends AbstractFilterChain{

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
