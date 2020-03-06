package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
/**
 * 描述：阿里云短信通知
 * @author gongsir
 * @date 2020/2/13 17:11
 * 编码不要畏惧变化，要拥抱变化
 */
public class AliMsgSend {
    /**
     * accesskey
     */
    private static final String ACCESSKEY_ID = "LTAI4Fjph5ogLR4ZQiA9dHNd";
    /**
     * accesskeySecret
     */
    private static final String ACCESSKEY_SECREAT = "KLGHvTSs5wEyJ15sDnQBCOl3ENiOpU";

    private static final Logger LOGGER = LoggerFactory.getLogger(AliMsgSend.class);

    /**
     * 验证短信
     */
    public static String sendVerification (final String singName, final String templateCode, final String tel, JSONObject params) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AliMsgSend.ACCESSKEY_ID, AliMsgSend.ACCESSKEY_SECREAT);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        //短信服务地址
        request.setDomain("dysmsapi.aliyuncs.com");
        //API版本号
        request.setVersion("2017-05-25");
        //API服务
        request.setAction("SendSms");
        //地域
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //电话号码
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", singName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", params.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            LOGGER.info("=====>>阿里云短信接口：{}",response.getData());
            JSONObject res = JSONObject.parseObject(response.getData());
            if ("OK".equals(res.get("Message"))){
                return "success";
            }else if ("触发分钟级流控Permits:1".equals(res.get("Message"))){
                return "请求过于频繁，请1分钟后重试";
            }else if ("无效号码".equals(res.get("Message"))){
                return "无效号码";
            }else if ("触发日发送限额".equals(res.get("Message"))){
                return "今日请求次数已达上限, 请明日再试";
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "failure";
    }

    public static JSONObject getCode(){
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i=0; i<n; i++){
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code.toString());
        return jsonObject;
    }
}
