package com.gongsir.wxapp.configuration;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.gongsir.wxapp.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 龚涛
 * @date 2019/10/25 21:48
 * 编码不要畏惧变化，要拥抱变化
 */
public class AdminApiInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminApiInterceptor.class);

    public static final String USER_INFO_KEY = "user_info_key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JwtTokenUtils.getTokenHeader());
        if (StringUtils.isEmpty(token)){
            token = request.getParameter(JwtTokenUtils.getTokenHeader());
        }

        //token is null
        if (StringUtils.isEmpty(token)){
            this.writerErrorMsg("1024",
                    JwtTokenUtils.getTokenHeader() + " can not be empty",
                    response);
            return false;
        }

        // 校验并解析token，如果token过期或者篡改，则会返回null
        Claims claims = JwtTokenUtils.verifyAndGetClaimsByToken(token);
        if (null == claims){
            this.writerErrorMsg("1024",
                    JwtTokenUtils.getTokenHeader() + "失效, 请重新登录",
                    response);
            return false;
        }
        // 校验通过后，设置用户信息到request里，在Controller中从Request域中获取用户信息
        request.setAttribute(USER_INFO_KEY,claims);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * 利用response直接输出错误信息
     * @param code code
     * @param msg msg
     * @param response response
     * @throws IOException io异常
     */
    private void writerErrorMsg (String code, String msg, HttpServletResponse response) throws IOException {
        JSONObject rs = new JSONObject();
        rs.put("code",code);
        rs.put("message",msg);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(rs.toJSONString());
    }
}