package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.logging.Logger;


//@WebFilter(urlPatterns = "/*") // 过滤所有请求
@Slf4j
public class DemoFilter implements Filter {


    // 初始化方法，web服务器启动时调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法......");
    }

    // 过滤方法，每次请求都会调用一次
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到了请求......");
        // 放行请求
        chain.doFilter(request, response);
        log.info("放行请求......");
    }

    // 销毁方法，web服务器关闭时调用一次
    @Override
    public void destroy() {
        log.info("destroy 销毁方法......");
    }
}
