package com.example.demo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;

import java.time.Duration;
import java.util.Date;

/**
 * @author CTL
 * <p>JwtUtil工具类 </p>
 * 创建日期：2020-10-20 17:57
 */
public class JwtUtil {
    /**
     * 这个秘钥是防止JWT被篡改的关键，随便写什么都好，但决不能泄露
     * PHP Laravel框架是系统自动生成的
     */
    private final static String secretKey = "alice";
    /**
     * 过期时间目前设置成2小时吧，这个配置随业务需求而定
     */
    private final static Duration expiration = Duration.ofHours(2);
    /**
     * 生成JWT
     * @param userName 用户名
     * @return JWT
     */
    public static String generate(String userName) {
        //System.currentTimeMillis() //返回以毫秒为单位的当前时间
        //expiration.toMillis() 将持续时间改为毫秒级别
        // 过期时间  当前时间加上2小时
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());
        //生产JWT ，包含username，签发时间，过期时间，加密后的内容
        return Jwts.builder()
                .setSubject(userName) // 将userName放进JWT
                .setIssuedAt(new Date()) // 设置JWT签发时间
                .setExpiration(expiryDate)  // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, secretKey) // 设置加密算法和秘钥，
                .compact();
    }
    /**
     * 解析JWT
     * @param token JWT字符串
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parse(String token) {
        // 如果是空字符串直接返回null
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        // 这个Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) // 设置秘钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // 这里应该用日志输出，为了演示方便就直接打印了
            System.err.println("解析失败！");
        }
        return claims;
    }
}
