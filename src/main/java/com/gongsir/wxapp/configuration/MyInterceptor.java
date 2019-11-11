package com.gongsir.wxapp.configuration;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 龚涛
 * @date 2019/10/25 21:48
 * 编码不要畏惧变化，要拥抱变化
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        String sessionKey = request.getParameter("sessionKey");
        logger.info("MyInterceptor>>>preHandle");
        logger.info("拦截器preHandle拦截的sessionKey:{}",sessionKey);
        if (sessionKey == null){
            PrintWriter writer = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",1024);
            jsonObject.put("msg","your sessionKey is null!");
            jsonObject.put("sessionKey",sessionKey);
            writer.println(jsonObject);
            return false;
        }
        return true;
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
