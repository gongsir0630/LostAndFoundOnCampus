package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gongsir
 */
class WxMessagePush {
    private static final Logger logger = LoggerFactory.getLogger(WxMessagePush.class);

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
            url = UserConstantInterface.PUSH_URL + accessToken;
        }
        String result = HttpClientUtil.doPostJson(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        int errorCode = jsonObject.getInteger("errcode");
        String errorMessage = jsonObject.getString("errmsg");
        if (errorCode == 0) {
            flag = true;
            logger.info("通知消息推送成功");
        } else {
            logger.info("模板消息发送失败:" + errorCode + "," + errorMessage);
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
        }else {
            url = UserConstantInterface.ACCESS_TOKEN_URL + "&appid=" + UserConstantInterface.WX_APPID + "&secret=" + UserConstantInterface.WX_LOGIN_SECRET;
        }
        String result = HttpClientUtil.doGet(url);
        return JSONObject.parseObject(result);
    }
}
