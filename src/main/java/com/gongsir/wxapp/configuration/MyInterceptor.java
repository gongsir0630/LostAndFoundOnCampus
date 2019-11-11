package com.gongsir.wxapp.configuration;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.utils.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author 龚涛
 * @date 2019/10/25 21:48
 * 编码不要畏惧变化，要拥抱变化
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        String sessionKey = request.getParameter("sessionKey");
        logger.info("MyInterceptor>>>preHandle");
        logger.info("拦截器preHandle拦截的sessionKey:{}",sessionKey);
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionKey") != null){
            String session_key = redisTemplate.opsForValue().get("sessionKey:"+Base64Util.decode2Array(session.getAttribute("sessionKey").toString())[0]);
            if (session_key != null && session_key.equals(Base64Util.decode2Array(session.getAttribute("sessionKey").toString())[1])){
                logger.info("身份验证通过");
                return true;
            }
        }
        logger.info("身份验证未通过");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",401);jsonObject.put("msg","your sessionKey is null or has Invalided!");
        jsonObject.put("sessionKey",sessionKey);writer.println(jsonObject);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("MyInterceptor>>>postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("MyInterceptor>>>afterCompletion");
    }
}