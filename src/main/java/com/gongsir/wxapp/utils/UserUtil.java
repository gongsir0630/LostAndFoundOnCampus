package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import com.gongsir.wxapp.controller.wxapi.UserController;
import com.gongsir.wxapp.model.Card;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author 龚涛
 * @date 2019/10/24 14:54
 * 编码不要畏惧变化，要拥抱变化
 */
public class UserUtil {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static RedisTemplate<String,JSONObject> redisTemplate = RedisCacheUtil.redisTemplate;

//    /**
//     * 请求微信接口需要的全局access_token
//     */
//    private static JSONObject wxToken = null;
//    private static JSONObject qqToken = null;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 微信推送模板消息给用户
     * @param openid 用户信息(加密后的)
     * @param card 证件信息
     * @return 操作成功与否
     */
    public static boolean wxMessagePush(String openid, Card card){
        logger.info("=====> 调用messagePush开始推送微信消息通知");
        JSONObject wxToken = redisTemplate.opsForValue().get("wxToken");
        if (wxToken==null){
            wxToken = MessagePush.getAccessToken("wx");
        }
        logger.info("访问微信消息推送接口的token:{}",wxToken);

//        if (wxToken==null){
//            wxToken = MessagePush.getAccessToken("wx");
//            wxToken.put("getTime",System.currentTimeMillis());
//            if (wxToken.get("access_token")==null || wxToken.get("access_token").toString().equals("")){
//                logger.error("=====> 获取access_token失败");
//                return false;
//            }
//        }else {
//            //如果token失效，重新获取
//            long dx = System.currentTimeMillis()-Long.parseLong(wxToken.get("getTime").toString());
//            if (dx/1000 >= Long.parseLong(wxToken.get("expires_in").toString())){
//                wxToken = MessagePush.getAccessToken("wx");
//                wxToken.put("getTime",System.currentTimeMillis());
//            }
//        }

        //封装模板消息
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("touser",Base64Util.decodeData(openid));
        jsonObject1.put("template_id", UserConstantInterface.TEMPLATE_ID);
        //小程序页面跳转
        jsonObject1.put("page","/pages/foundCard/foundCard?id="+card.getId());

        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();

        //证件类型
        if ("stuCard".equals(card.getCardType())){
            jsonObject3.put("value","西南石油校园一卡通 / stuCard");
        }else {
            jsonObject3.put("value","身份证 / idCard");
        }
        jsonObject2.put("thing4",jsonObject3);

        //证件信息
        jsonObject3 = new JSONObject();
        // 只能20个字符以内
        jsonObject3.put("value",card.getCardNum());
        jsonObject2.put("thing5",jsonObject3);

        //发布时间
        jsonObject3 = new JSONObject();
        jsonObject3.put("value",sdf.format(card.getCardTime()));
        jsonObject2.put("date6",jsonObject3);

        //联系方式
        jsonObject3 = new JSONObject();
        jsonObject3.put("value",card.getRelation().replaceFirst("place","指定地点领取").replaceFirst("tel","电话").replaceFirst("qq","QQ"));
        jsonObject2.put("thing7",jsonObject3);

        //备注
        jsonObject3 = new JSONObject();
        jsonObject3.put("value","感谢使用西柚失物招领,欢迎推广!");
        jsonObject2.put("thing1",jsonObject3);

        logger.info(jsonObject2.toJSONString());
        jsonObject1.put("data",jsonObject2);

        logger.info(wxToken.get("access_token").toString());
        logger.info(jsonObject1.toJSONString());
        return MessagePush.push(jsonObject1.toJSONString(), wxToken.get("access_token").toString(),"wx");
    }

    /**
     * QQ推送模板消息给用户
     * @param openid 用户信息(加密后的)
     * @param card 证件信息
     * @return 操作成功与否
     */
    public static boolean qqMessagePush(String openid, Card card, String formId){
        logger.info("=====> 调用messagePush开始推送QQ消息通知");

        JSONObject qqToken = redisTemplate.opsForValue().get("qqToken");
        if (qqToken==null){
            qqToken = MessagePush.getAccessToken("qq");
        }
        logger.info("访问QQ消息推送接口的token:{}",qqToken);
//        if (qqToken==null){
//            qqToken = MessagePush.getAccessToken("qq");
//            qqToken.put("getTime",System.currentTimeMillis());
//            if (qqToken.get("access_token")==null || qqToken.get("access_token").toString().equals("")){
//                logger.error("=====> 获取access_token失败");
//                return false;
//            }
//        }else {
//            //如果token失效，重新获取
//            long dx = System.currentTimeMillis()-Long.parseLong(qqToken.get("getTime").toString());
//            if (dx/1000 >= Long.parseLong(qqToken.get("expires_in").toString())){
//                qqToken = MessagePush.getAccessToken("qq");
//                qqToken.put("getTime",System.currentTimeMillis());
//            }
//        }

        //封装模板消息
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("touser",Base64Util.decodeData(openid));
        jsonObject1.put("template_id", UserConstantInterface.QQ_TEMPLATE_ID);
        jsonObject1.put("form_id",formId);
        //小程序页面跳转
        jsonObject1.put("page","/pages/foundCard/foundCard?id="+card.getId());

        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();

        //证件类型
        if ("stuCard".equals(card.getCardType())){
            jsonObject3.put("value","西南石油校园一卡通 / stuCard");
        }else {
            jsonObject3.put("value","身份证 / idCard");
        }
        jsonObject2.put("keyword3",jsonObject3);

        //证件信息
        jsonObject3 = new JSONObject();
        jsonObject3.put("value","证件号:"+card.getCardNum()+"\n姓  名:"+card.getCardName());
        jsonObject2.put("keyword1",jsonObject3);


        //联系方式
        jsonObject3 = new JSONObject();
        jsonObject3.put("value",card.getRelation().replaceFirst("place","指定地点领取").replaceFirst("tel","电话").replaceFirst("qq","QQ"));
        jsonObject2.put("keyword2",jsonObject3);


        logger.info(jsonObject2.toJSONString());
        jsonObject1.put("data",jsonObject2);

        logger.info(qqToken.get("access_token").toString());
        logger.info(jsonObject1.toJSONString());
        return MessagePush.push(jsonObject1.toJSONString(), qqToken.get("access_token").toString(),"qq");
    }

    /**
     * 解析用户的个人信息
     * @param sessionKey 唯一标识
     * @param encryptedData 加密数据
     * @param iv 偏移量
     * @return 个人信息
     */
    public static JSONObject getUserInfo(String sessionKey, String encryptedData, String iv){
        byte[] dataByte = Base64.decode(encryptedData);
        byte[] keyByte = Base64.decode(sessionKey);
        byte[] ivByte = Base64.decode(iv);
        System.out.println(encryptedData);
        System.out.println(sessionKey);
        System.out.println(iv);
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + 1;
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                //解析得到的所有铭感个人信息
                String result = new String(resultByte, StandardCharsets.UTF_8);
                JSONObject jsonObject = JSONObject.parseObject(result);
                System.out.println("个人信息:"+jsonObject.toJSONString());
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
