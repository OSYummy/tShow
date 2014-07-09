package com.wisedu.core.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: YUMMY
 * Date: 14-7-9
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public class ProcessTimeFilter implements Filter {
    private final static Logger log = LoggerFactory.getLogger(ProcessTimeFilter.class);

    protected FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        // 转交控制权
        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();

        // 输出请求信息
        System.out.println("request process info:");
        System.out.println("begin-----------------");
        System.out.println("time=[" + (endTime - startTime) + "]");
        System.out.println("url=[" + ((HttpServletRequest)request).getRequestURL() + "]");
        System.out.println("client=[" + request.getRemoteAddr() +"]");
        System.out.println("method=[" + ((HttpServletRequest) request).getMethod() + "]");
        System.out.println("end--------------");
    }

    @Override
    public void destroy() {
    }
}