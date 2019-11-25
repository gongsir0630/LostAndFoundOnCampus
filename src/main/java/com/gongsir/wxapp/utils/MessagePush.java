package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gongsir
 */
class MessagePush {
    private static final Logger logger = LoggerFactory.getLogger(MessagePush.class);

    /**
     * 消息推送
     * @param params 推送信息封装的json字符串
     * @param accessToken access_token
     * @return 返回发送成功/失败状态
     */
    static boolean push(String params, String accessToken) {
        boolean flag = false;
        String url = UserConstantInterface.PUSH_URL + accessToken;
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

    static JSONObject getAccessToken() {
        String url = UserConstantInterface.ACCESS_TOKEN_URL + "&appid=" + UserConstantInterface.WX_APPID + "&secret=" + UserConstantInterface.WX_LOGIN_SECRET;
        String result = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }
}
