package com.example.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author shawn
 * @version 1.0
 * @ClassName JwtTokenUtils
 * Description:
 * @date 2023/2/28 21:49
 */
@Slf4j
public class JwtTokenUtils {

    // 令牌签名密钥
    private static final String tokenSignKey = "gremlin";

    // token有效时长30分钟
    private static final long tokenExpiration = 30*60*1000;

    /**
     * 生成token
     */
    public static String generateToken(String subject, Map<String,Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                // 签名算法
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                // 压缩格式
                .compressWith(CompressionCodecs.GZIP).compact();
    }
    /**
     * 判断令牌是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            return  getTokenBody(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解析token,获得subject中的信息
     */
    public static String parseToken(String token) {
        String subject = null;
        try {
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
        return subject;
    }

    /**
     * 获取token自定义属性
     */
    public static Map<String,Object> getClaims(String token){
        Map<String,Object> claims = null;
        try {
            claims = getTokenBody(token);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static Date getTokenTime(String token){
        return getTokenBody(token).getExpiration();
    }

    /**
     * 是否已过期
     */
    public static boolean isExpiration(String expirationTime){
        //通过redis中的失效时间进行判断
        String currentTime = DateUtil.getTime();
        if(DateUtil.compareDate(currentTime,expirationTime)){
            //当前时间比过期时间大，失效
            return true;
        }else{
            return false;
        }
    }

    private static Claims getTokenBody(String token){
        return  Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
    }

    /**
     * 根据token字符串获取账号
     * @param request
     * @return
     */
    public static String getUserByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(!StringUtils.hasText(jwtToken)) {
            return "";
        }
        Claims tokenBody = getTokenBody(jwtToken);
        return tokenBody.getSubject();
    }
}
