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

//    小程序AppID
//    龚涛
//    public static final String WX_LOGIN_AppID = "wx2e1d0d6fb608404c";
//    陈瑶
    public static final String WX_LOGIN_AppID = "wx67e6577c6a614550";

//    小程序的secret
//    龚涛
//    public static final String WX_LOGIN_SECRET = "cf728447dc1c64bf2dde3fd96ddd9ab6";
//    陈瑶
    public static final String WX_LOGIN_SECRET = "9bdbf0022027d4e341bc2804769f37e2";

//    请求模式
    public static final String WX_LOGIN__GRANT_TYPE = "authorization_code";

//    获取token地址
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

//    推送消息地址
    public static final String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

//    消息模板id
    //龚涛
//    public static final String Template_ID = "xIvas2IvYY2UISlHFSM5uk61FG-6DH8bC-Dp7ILi4Aw";
    //陈瑶
    public static final String Template_ID = "_UrYFmB6FgMZPLyQLJ9n7sTGEQLUD8wCvZZTbh-f3a4";
}
