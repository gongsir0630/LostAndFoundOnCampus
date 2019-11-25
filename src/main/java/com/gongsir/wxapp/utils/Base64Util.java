package com.gongsir.wxapp.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author 龚涛
 * @date 2019/10/24 11:23
 * 编码不要畏惧变化，要拥抱变化
 */
public class Base64Util {

    // 字符串编码
    private static final String UTF_8 = "UTF-8";

    private static final String SPLIT_FLAG = "/gongSir/";

    /**
     * 加密字符串
     * @param inputData 预加密的字符串
     * @return 加密后的字符串
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密字符串
     * @param inputData 加密字符串
     * @return 解密后的串
     */
    static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密openId和session_key
     * @param openID 身份标识
     * @param sessionKey 会话session
     * @return 加密串
     */
    public static String encodeOpenIDAndSessionKey(String openID,String sessionKey){
        return encodeData(openID)+SPLIT_FLAG+encodeData(sessionKey);
    }

    /**
     * 解密openID和session_key
     * @param encode 加密的串
     * @return 解密数组
     */
    public static String[] decode2Array(String encode){
        String[] res = encode.split(SPLIT_FLAG);
        String[] des = new String[res.length];
        for (int i=0;i<des.length;i++){
            des[i] = decodeData(res[i]);
        }
        return des;
    }

    /**
     * 测试
     * @param args 参数
     */
    public static void main(String[] args) {
        System.out.println(encodeOpenIDAndSessionKey("龚涛","李诗雅"));
        String encode = encodeOpenIDAndSessionKey("龚涛","李诗雅");
        String[] res = decode2Array(encode);
        System.out.println(Arrays.toString(res));
        System.out.println(encodeData("olAW-4vIdX8DTkzftHveDWIlR4zU"));
    }
}
