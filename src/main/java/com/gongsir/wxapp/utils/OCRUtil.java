package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;

/**
 * @author gongsir
 */
public class OCRUtil {
    /**
     * 设置百度云AppID/AK/SK
     */
    private static final String APP_ID = "17561118";
    private static final String API_KEY = "CS5A1mgog1wtjBSayhFtPSDc";
    private static final String SECRET_KEY = "1fiTNow1wXNBWNGo2cRGR0WNp0lY4flR";
    /**
     * 设置校园卡的模板id
     */
    private static final String STU_CARD_FONT = "7564c672a47256ca981e1df42480c077";

    public static JSONObject ocr(MultipartFile file, String type) throws Exception {
        if (type.equals("idCard")){
            return idCard(file);
        }
        if (type.equals("stuCard")){
            return stuCard(file);
        }
        return null;
    }

    /**
     * 身份证识别
     * @param file image
     * @return info
     * @throws Exception exception
     */
    private static JSONObject idCard(MultipartFile file) throws Exception{
        //将文件转化为二进制
        byte[] buf = file.getBytes();
        //初始化百度接口
        AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
        //调用身份证接口识别身份证
        String res = client.idcard(buf,"front",new HashMap<>()).toString();
        System.out.println(res);
        return JSONObject.parseObject(res);
    }

    /**
     * 学生证识别
     * @param file 学生证证件图片
     * @return 识别信息json
     * @throws Exception 异常
     */
    private static JSONObject stuCard(MultipartFile file) throws Exception{
//        将文件转为二进制
        byte[] buf = file.getBytes();
//        初始化ocr接口
        AipOcr aipOcr = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
//        调用接口识别信息
        String res = aipOcr.custom(buf,STU_CARD_FONT,new HashMap<>()).toString();
//        打印信息
        System.out.println(res);
        return JSONObject.parseObject(res);
    }

}
