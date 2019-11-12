package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import com.gongsir.wxapp.controller.wxapi.UserController;
import com.gongsir.wxapp.model.Card;
import com.gongsir.wxapp.model.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    /**
     * 请求微信接口需要的全局access_token
     */
    private static JSONObject token = null;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 推送模板消息给用户
     * @param openid 用户信息(加密后的)
     * @param card 证件信息
     * @param formid form表单id
     * @return 操作成功与否
     */
    public static boolean messagePush(String openid, Card card, String formid){
        logger.info("=====> 调用messagePush开始推送消息通知");
        if (token==null){
            token = MessagePush.getAccessToken();
            token.put("getTime",System.currentTimeMillis());
            if (token.get("access_token")==null || token.get("access_token").toString().equals("")){
                logger.error("=====> 获取access_token失败");
                return false;
            }
        }else {
            //如果token失效，重新获取
            long dx = System.currentTimeMillis()-Long.parseLong(token.get("getTime").toString());
            if (dx/1000 >= Long.parseLong(token.get("expires_in").toString())){
                token = MessagePush.getAccessToken();
                token.put("getTime",System.currentTimeMillis());
            }
        }

        //封装模板消息
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("touser",Base64Util.decodeData(openid));
        jsonObject1.put("template_id", UserConstantInterface.Template_ID);
        jsonObject1.put("formid",formid);

        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();

        if ("stuCard".equals(card.getCardType())){
            jsonObject3.put("value","西南石油校园一卡通 / stuCard");
        }else {
            jsonObject3.put("value","身份证 / idCard");
        }
        jsonObject2.put("keyword1",jsonObject3);

        jsonObject3 = new JSONObject();
        jsonObject3.put("value","证件号:"+card.getCardNum()+"\n姓名:"+card.getCardName());
        jsonObject2.put("keyword2",jsonObject3);

        jsonObject3 = new JSONObject();
        jsonObject3.put("value","失物招领");
        jsonObject2.put("keyword3",jsonObject3);

        jsonObject3 = new JSONObject();
        jsonObject3.put("value",card.getRelation());
        jsonObject2.put("keyword4",jsonObject3);

        jsonObject3 = new JSONObject();
        jsonObject3.put("value",card.getCardTime());
        jsonObject2.put("keyword5",sdf.format(card.getCardTime()));

        jsonObject3 = new JSONObject();
        jsonObject3.put("value","感谢使用西柚失物招领,欢迎推广!");
        jsonObject2.put("keyword6",jsonObject3);

        logger.info(jsonObject2.toJSONString());
        jsonObject1.put("data",jsonObject2);

        logger.info(token.get("access_token").toString());
        logger.info(jsonObject1.toJSONString());
        return MessagePush.Push(jsonObject1.toJSONString(), token.get("access_token").toString());
    }

    /**
     * 解析用户的个人信息
     * @param session_keu 唯一标识
     * @param encryptedData 加密数据
     * @param iv 偏移量
     * @return 个人信息
     */
    public static JSONObject getUserInfo(String session_keu, String encryptedData, String iv){
        byte[] dataByte = Base64.decode(encryptedData);
        byte[] keyByte = Base64.decode(session_keu);
        byte[] ivByte = Base64.decode(iv);
        System.out.println(encryptedData);
        System.out.println(session_keu);
        System.out.println(iv);
        try {
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
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
