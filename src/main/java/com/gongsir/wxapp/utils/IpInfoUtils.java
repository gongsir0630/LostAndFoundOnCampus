package com.gongsir.wxapp.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：根据IP地址解析地理位置
 * api地址：https://market.aliyun.com/products/57002002/cmapi00035184.html?spm=5176.2020520132.101.1.21267218cXuQZs#sku=yuncode2918400001
 * @author gongsir
 * @date 2020/2/17 12:57
 * 编码不要畏惧变化，要拥抱变化
 */
public class IpInfoUtils {
    private static final String APP_CODE = "e9e249da68794023bf9929df37f3fed2";

    private static final String HOST = "https://ips.market.alicloudapi.com";

    private static final String path = "/iplocaltion";

    private static final String SUCCESS_CODE = "100";

    public static String getIpInfo(String ip) {
        String url = HOST + path;
        //ip参数
        Map<String,String> params = new HashMap<>();
        params.put("ip",ip);

        //请求头header
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","APPCODE "+APP_CODE);
        String rs = HttpClientUtil.doGet(url, params, headers);
        JSONObject jsonObject = JSONObject.parseObject(rs);
        if (SUCCESS_CODE.equals(jsonObject.get("code").toString())){
            //ip解析成功
            JSONObject result = JSONObject.parseObject(jsonObject.get("result").toString());
            return result.get("province") + "-" + result.get("city");
        }
        return jsonObject.get("message").toString();
    }

    /**
     * 测试
     * @param args args
     */
    public static void main(String[] args) {
        String rs = getIpInfo("171.208.60.217");
        System.out.println(rs);
    }
}
