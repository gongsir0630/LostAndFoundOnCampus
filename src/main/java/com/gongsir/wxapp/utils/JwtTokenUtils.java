package com.gongsir.wxapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gongsir
 * @date 2020/2/16 21:11
 * 编码不要畏惧变化，要拥抱变化
 */
public class JwtTokenUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class);

    private static final String TOKEN_HEADER = "Authorization";

    private static final String SECRET = "LssILoveYou";
    private static final String ISS = "gongSir";

    /**
     * 过期时间是3600秒，即是1个小时
     */
    private static final long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建Token
     * @param isRememberMe 是否选择记住token
     * @return token
     */
    public static String createToken(Map<String,Object> userInfo, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        if (Objects.isNull(userInfo)){
            userInfo = new HashMap<>();
        }

        return Jwts.builder()
                //设置头部信息
                .setHeaderParam("typ","JWT")
                .setIssuer(ISS)
                .setSubject("swpu-lostAndFound-app")
                //装入自定义的用户信息
                .setClaims(userInfo)
                .setIssuedAt(new Date())
                //token过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    /**
     * 校验token并解析token
     * @param token 令牌
     * @return Claims：它继承了Map,而且里面存放了生成token时放入的用户信息
     */
    public static Claims verifyAndGetClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.debug("verify token error:[{}] ", e.getMessage());
            return null;
        }
    }

    public static String getTokenHeader() {
        return TOKEN_HEADER;
    }

}
