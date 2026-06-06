package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 令牌操作工具类
 */
public class JwtUtils {

    // 密钥，与测试类中保持一致
    private static final String SECRET_KEY = "aXRoZWltYQ==";

    // 令牌过期时间：12小时（单位：毫秒）
    // 注意：末尾的 L 表示 long 类型，防止计算时发生整型溢出
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000L; 

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义载荷信息（如 id, username 等）
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 设置签名算法和密钥
                .addClaims(claims) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .compact(); // 生成jwt字符串
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token JWT 字符串
     * @return Claims 载荷信息
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 设置密钥
                .parseClaimsJws(token)
                .getBody(); // 获取载荷信息
    }
}
