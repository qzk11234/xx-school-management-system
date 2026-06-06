package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验的拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 1. 获取请求路径
//        String requestURI = request.getRequestURI();
//
//        // 2. 判断是否是登录请求，如果路径中包含/login，直接放行
//        if(requestURI.contains("/login")){
//            log.info("登录请求，直接放行......");
//            return true;
//        }

        // 3. 获取请求头中的token
        String token = request.getHeader("token");

        // 4. 判断token是否存在，如果不存在，响应401状态码，提示用户未登录
        if(token == null || token.isEmpty()){
            log.info("token不存在，响应401状态码，提示用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        // 5. 如果token存在，验证token是否有效，如果无效，响应401状态码，提示用户未登录
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("token无效，响应401状态码，提示用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 6. 如果token有效，放行
        log.info("token有效，直接放行......");
        return true;
    }
}
