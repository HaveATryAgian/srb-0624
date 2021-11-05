package com.atguigu.jwt;

import io.jsonwebtoken.*;
import org.junit.Test;

/**
 * 包名：com.atguigu.jwt
 *
 * @author Fun
 * 日期2021-11-05 19:09
 */

public class JwtTests {
    //秘钥
    String serviceKey = "atguigu0624";
    String ip = "127.0.0.1";
    String name = "张三";
    int age = 18;

    //加密
    @Test
    public void a(){
        String token = Jwts.builder()
                .setHeaderParam("alg", "Hs256")
                .setHeaderParam("type", "JWT")
                .claim("name", name)
                .claim("age", age)
                .signWith(SignatureAlgorithm.HS256, MD5.encrypt(serviceKey + ip))
                .compact();

        System.out.println(token);
    }


    //解密
    @Test
    public void b(){
        String token1 = "eyJhbGciOiJIUzI1NiIsInR5cGUiOiJKV1QifQ.eyJuYW1lIjoi5byg5LiJIiwiYWdlIjoxOH0.9ouBRfBqi6-VPOiGe94NGtc5Ny2B-dwNWSXKLZN1nxU";
        String token2 = "eyJhbGciOiJIUzI1NiIsInR5cGUiOiJKV1QifQ.eyJuYW1lIjoi5byg5LiJIiwiYWdlIjoxOH0.Btk4HDw7SvZ42IPkMiLof8vW_Re0cSOP0PFSryc6eaA";
        String serviceKey = "atguigu0624";
        String ip = "127.0.0.1";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(MD5.encrypt(serviceKey+ip)).parseClaimsJws(token2);
        Claims body = claimsJws.getBody();
        System.out.println(body);
    }
}
