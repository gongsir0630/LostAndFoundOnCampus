package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author gongsir
 */
class MessagePush {
    private static final Logger logger = LoggerFactory.getLogger(MessagePush.class);

    private static RedisTemplate<String,JSONObject> redisTemplate = RedisCacheUtil.redisTemplate;

    /**
     * 消息推送
     * @param params 推送信息封装的json字符串
     * @param accessToken access_token
     * @param app 小程序类型
     * @return 返回发送成功/失败状态
     */
    static boolean push(String params, String accessToken,String app) {
        boolean flag = false;
        String url;
        if ("qq".equalsIgnoreCase(app)){
            url = UserConstantInterface.QQ_PUSH_URL + accessToken;
        }else {
            url = UserConstantInterface.SUBSCRIBE_URL + accessToken;
        }
        String result = HttpClientUtil.doPostJson(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        int errorCode = jsonObject.getInteger("errcode");
        String errorMessage = jsonObject.getString("errmsg");
        if (errorCode == 0) {
            flag = true;
            logger.info("通知消息推送成功");
        } else {
            logger.info("消息发送失败:" + errorCode + "," + errorMessage);
            logger.info(result);
            flag = false;
        }
        return flag;
    }

    /**
     * 获取访问接口的权限
     * @param app 小程序类型
     * @return 令牌
     */
    static JSONObject getAccessToken(String app) {
        String url;
        if ("qq".equalsIgnoreCase(app)){
            url = UserConstantInterface.QQ_ACCESS_TOKEN_URL + "&appid=" + UserConstantInterface.QQ_APPID + "&secret=" + UserConstantInterface.QQ_LOGIN_SECRET;
            String result = HttpClientUtil.doGet(url);
            logger.info("QQ服务端token:{}",result);
            //qqToken存入redis
            redisTemplate.opsForValue().set("qqToken", JSONObject.parseObject(result),2, TimeUnit.HOURS);
            return JSONObject.parseObject(result);
        }else {
            url = UserConstantInterface.ACCESS_TOKEN_URL + "&appid=" + UserConstantInterface.WX_APPID + "&secret=" + UserConstantInterface.WX_LOGIN_SECRET;
            String result = HttpClientUtil.doGet(url);
            logger.info("微信服务端token:{}",result);
            //wxToken存入redis
            redisTemplate.opsForValue().set("wxToken", JSONObject.parseObject(result),2, TimeUnit.HOURS);
            return JSONObject.parseObject(result);
        }
    }
}
