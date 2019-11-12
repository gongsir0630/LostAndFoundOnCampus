package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.service.UserService;
import com.gongsir.wxapp.utils.Base64Util;
import com.gongsir.wxapp.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 龚涛
 * @date 2019-11-01 08:27:54
 */

@RestController
@RequestMapping(value = "/wxApi/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping(path = "hello")
    public String hello(){
        return "hello wxApp!";
    }

    @GetMapping(path = "index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 登录注册,存储用户信息
     * @param code 小程序登录code
     * @param name 微信昵称
     * @param headImg 微信头像
     * @return 返回自定义登录状态
     */
    @PostMapping(path = "login")
    public JSONObject wxLogin(@RequestParam("code") String code,
                              @RequestParam(value = "nickName") String name,
                              @RequestParam("headImg")String headImg){
        Map<String,String> param = new HashMap<>();
        //封装请求参数
        param.put("appid", UserConstantInterface.WX_LOGIN_AppID);
        param.put("secret",UserConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code",code);
        param.put("grant_type",UserConstantInterface.WX_LOGIN__GRANT_TYPE);
        //调用微信接口
        String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL,param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        logger.info("wxResult:{}",jsonObject);
        if (jsonObject.containsKey("errcode") && Integer.parseInt(jsonObject.get("errcode").toString())!=0){
            logger.info("=====>>接口信息错误:"+jsonObject.get("errcode")+","+jsonObject.get("errmsg"));
            JSONObject response = new JSONObject();
            response.put("errcode",jsonObject.get("errcode"));
            response.put("errmsg", jsonObject.get("errmsg"));
            return response;
        }
        //取出openid和session_key
        String openid = jsonObject.get("openid").toString();
        String session_key = jsonObject.get("session_key").toString();
        //封装返回数据集
        JSONObject res = new JSONObject();
        res.put("msg","小程序登录认证成功!");
        //判断用户是否存在
        User user = userService.selectUserByOpenID(Base64Util.encodeData(openid));
        //不存在,插入数据
        if (user==null){
            user = new User();
            user.setUserOpenid(Base64Util.encodeData(openid));
            user.setUserName(name);
            user.setUserHead(headImg);
            userService.saveUser(user);
        }
        //判断是否绑定学号
        if (null==user.getStuNum() || "".equals(user.getStuNum())){
            res.put("msg","同学你好,欢迎使用西柚失物招领小程序,首次使用请绑定学号,谢谢!");
        }
        //返回自定义登录状态,加密数据
        res.put("sessionKey", Base64Util.encodeOpenIDAndSessionKey(openid,session_key));
        res.put("code",user.getId());
        //存储用户id-session_key到redis中.30min有效
        redisTemplate.opsForValue().set("sessionKey:"+openid, session_key,60*30, TimeUnit.SECONDS);
        logger.info("返回信息:{}",res);
        return res;
    }

    /**
     * 更新个人信息
     * @param sessionKey 登录状态
     * @param user 用户信息
     * @return code\data\msg
     */
    @PostMapping(path = "/update")
    public JSONObject updateInfo(String sessionKey,User user){
        JSONObject jsonObject = new JSONObject();
        int i = userService.updateUserByOpenID(user, Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]));
        if (i>0){
            jsonObject.put("code",i);
            jsonObject.put("msg","信息更新成功");
            jsonObject.put("data",userService.selectUserByOpenID(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0])));
        } else {
            jsonObject.put("code",1024);
            jsonObject.put("msg","error");
        }
        logger.info("res返回信息:{}",jsonObject.toString());
        return jsonObject;
    }
}
