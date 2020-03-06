package com.gongsir.wxapp.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 龚涛
 * @date 2019-11-01 09:16:01
 * 发送http请求的工具类
 */
public class HttpClientUtil {

    public static String doGet(String url, Map<String,String> param, Map<String,String> headers){
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建URI
            URIBuilder builder = new URIBuilder(url);
            if (param != null){
                for (String key :
                        param.keySet()) {
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri = builder.build();

            //创建http get请求
            HttpGet httpGet = new HttpGet(uri);

            //加入请求头
            if (headers != null){
                for (Map.Entry<String,String> e : headers.entrySet()){
                    httpGet.addHeader(e.getKey(),e.getValue());
                }
            }

            //执行请求
            response = httpClient.execute(httpGet);

            //判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200){
                resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, Map<String,String> param){
        return doGet(url,param,null);
    }

    static String doGet(String url){
        return doGet(url,null);
    }

    private static String doPost(String url, Map<String, String> param){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            HttpPost httpPost = new HttpPost(url);
            if (param != null){
                List<NameValuePair> pairList = new ArrayList<>();
                for (String key :
                        param.keySet()) {
                    pairList.add(new BasicNameValuePair(key,param.get(key)));
                }
                //模拟表单
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairList);
                httpPost.setEntity(formEntity);
            }
            //执行http post请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode()==200){
                resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (Exception e){
            e.printStackTrace();;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url){
        return doPost(url,null);
    }

    static String doPostJson(String url, String json){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return resultString;
    }
}
