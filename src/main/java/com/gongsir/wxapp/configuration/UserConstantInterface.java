package com.gongsir.wxapp.configuration;

/**
 * @author gongsir
 * wx小程序配置信息
 */
public interface UserConstantInterface {
    /**
     * 请求的wxApi
     */
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 微信小程序AppID
     */
    // 个人主体(陈瑶)
//    public static final String WX_APPID = "wx67e6577c6a614550";

    // 企业主体(炳钞)
    public static final String WX_APPID = "wx919fbf9a01e19c72";

    /**
     * 微信小程序secret
     */
    // 个人主体
//    public static final String WX_LOGIN_SECRET = "9bdbf0022027d4e341bc2804769f37e2";

    // 企业主体
    public static final String WX_LOGIN_SECRET = "440a0f3b78a0992e9985a6bb7d0ee926";

    /**
     * 小程序登录模式
     */
    public static final String WX_LOGIN__GRANT_TYPE = "authorization_code";

    /**
     * 微信获取access_token的接口地址
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 微信推送模板消息的接口
     */
    public static final String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    /**
     * 微信推送订阅消息的接口
     */
    public static final String SUBSCRIBE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";

    /**
     * 微信消息模板的ID
     */
    // 个人
//    public static final String TEMPLATE_ID = "AfhvatJqhsic7d-uTdLQ7fXmbHuBAU7tp0NgK1nXhAo";
    // 企业
    public static final String TEMPLATE_ID = "BqATFm8I0baZtW6ETtTdePDbmCewBnVGoVDy9deOYkY";

    /**
     * QQ请求login的Api
     */
    public static final String QQ_LOGIN_URL = "https://api.q.qq.com/sns/jscode2session";

    /**
     * QQ AppID
     */
    public static final String QQ_APPID = "1110061270";

    /**
     * QQ小程序secret
     */
    public static final String QQ_LOGIN_SECRET = "uTU6nJV0FyM9GpOw";

    /**
     * QQ小程序登录模式
     */
    public static final String QQ_LOGIN__GRANT_TYPE = "authorization_code";

    /**
     * QQ获取access_token的接口地址
     */
    public static final String QQ_ACCESS_TOKEN_URL = "https://api.q.qq.com/api/getToken?grant_type=client_credential";

    /**
     * QQ推送模板消息的接口
     */
    public static final String QQ_PUSH_URL = "https://api.q.qq.com/api/json/template/send?access_token=";

    /**
     * QQ消息模板的ID
     */
    public static final String QQ_TEMPLATE_ID = "3a3cdfa1192a75cc72a89efd3660cdf7";

    /**
     * 阿里云短信签名
     */
    public static final String SIGN_NAME = "西柚失寻";

    /**
     * 短信签名模板ID
     */
    public static final String TEMPLATE_CODE = "SMS_183267945";
}
