package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {



    /**
     * 生成jwt
     */
    @Test
    public void testGenerateJwt(){

        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");


        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"aXRoZWltYQ==")//设置签名算法和密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置过期时间
                .compact();//生成jwt字符串
        System.out.println(jwt);

    }


    /**
     * 解析jwt
     */
    @Test
    public void testParseJwt(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc4MDQwNTI4OX0.6H0dBQMnl-MbuyoT6Pv3MW1_OAb34_UsINmVQjcECfg";
        Claims claims= Jwts.parser()
                .setSigningKey("aXRoZWltYQ==")//设置密钥
                .parseClaimsJws(token)
                .getBody();//获取载荷信息
        System.out.println(claims);
    }





}
