package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*") // 过滤所有请求
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取请求路径
        String requestURI = request.getRequestURI();

        // 2. 判断是否是登录请求，如果路径中包含/login，直接放行
        if(requestURI.contains("/login")){
            log.info("登录请求，直接放行......");
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 获取请求头中的token
        String token = request.getHeader("token");

        // 4. 判断token是否存在，如果不存在，响应401状态码，提示用户未登录
        if(token == null || token.isEmpty()){
            log.info("token不存在，响应401状态码，提示用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        // 5. 如果token存在，验证token是否有效，如果无效，响应401状态码，提示用户未登录
        try {
            Claims claims = JwtUtils.parseJwt(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("token有效，当前用户ID: {}", empId);
        } catch (Exception e) {
            log.info("token无效，响应401状态码，提示用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 6. 如果token有效，放行
        log.info("token有效，直接放行......");
        filterChain.doFilter(request, response);

        //7. 从当前线程中移除用户ID
        CurrentHolder.remove();
        log.info("Threadlocal中存储的当前用户ID已移除");
    }
}
