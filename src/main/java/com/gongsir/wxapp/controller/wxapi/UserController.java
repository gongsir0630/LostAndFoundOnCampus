package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.service.UserService;
import com.gongsir.wxapp.utils.Base64Util;
import com.gongsir.wxapp.utils.HttpClientUtil;
import com.vdurmont.emoji.EmojiParser;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 龚涛
 * @date 2019-11-01 08:27:54
 */

@Api(tags = "小程序用户相关接口")
@RestController
@RequestMapping(value = "/wxApi/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录注册,存储用户信息
     * @param code 小程序登录code
     * @param name 微信昵称
     * @param headImg 微信头像
     * @param app 小程序类型，微信或者QQ，微:wx QQ:qq
     * @return 返回自定义登录状态
     */
    @ApiOperation(value = "小程序用户登录")
    @PostMapping(path = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "wx.login()的code",required = true),
            @ApiImplicitParam(name = "nickName",value = "微信昵称",required = true),
            @ApiImplicitParam(name = "headImg",value = "微信头像",required = true),
            @ApiImplicitParam(name = "app",value = "小程序类型,分为wx/qq",required = true,defaultValue = "wx"),
    })
    @ApiResponses({
            @ApiResponse(code = -1,message = "同学你好,欢迎使用西柚失物招领小程序,首次使用请绑定学号,谢谢!"),
            @ApiResponse(code = 1024,message = "昵称不合法,不能包含特殊字符")
    })
    public JSONObject wxLogin(@RequestParam("code") String code,
                              @RequestParam(value = "nickName") String name,
                              @RequestParam("headImg")String headImg,
                              @RequestParam(value = "app",defaultValue = "wx")String app){
        Map<String,String> param = new HashMap<>(4);
        //接收微信或者qq接口处理结果
        String result = null;
        if ("qq".equals(app)){
            //封装请求参数
            param.put("appid", UserConstantInterface.QQ_APPID);
            param.put("secret",UserConstantInterface.QQ_LOGIN_SECRET);
            param.put("js_code",code);
            param.put("grant_type",UserConstantInterface.QQ_LOGIN__GRANT_TYPE);
            //调用QQ接口
            result = HttpClientUtil.doGet(UserConstantInterface.QQ_LOGIN_URL,param);
        }else {
            //封装请求参数
            param.put("appid", UserConstantInterface.WX_APPID);
            param.put("secret",UserConstantInterface.WX_LOGIN_SECRET);
            param.put("js_code",code);
            param.put("grant_type",UserConstantInterface.WX_LOGIN__GRANT_TYPE);
            //调用微信接口
            result = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL,param);
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        logger.info("apiResult:{}",jsonObject);
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
        try {
            if (user==null){
                user = new User();
                user.setUserOpenid(Base64Util.encodeData(openid));
                //过滤微信昵称的emoji表情
                user.setUserName(EmojiParser.removeAllEmojis(name));
                user.setUserHead(headImg);
                user.setUserApp(app);
                user.setUserStatus(0);
                userService.saveUser(user);
            }
        }catch (Exception e){
            jsonObject.put("code",1024);
            jsonObject.put("msg","昵称不合法,不能包含特殊字符");
            return jsonObject;
        }
        //判断是否绑定学号
        if (null==user.getStuNum() || "".equals(user.getStuNum())){
            res.put("code",-1);
            res.put("msg","同学你好,欢迎使用西柚失物招领小程序,首次使用请绑定学号,谢谢!");
        }else {
            res.put("msg","登陆成功");
            //返回个人信息
            res.put("user",user);
        }
        //返回自定义登录状态,加密数据
        res.put("sessionKey", Base64Util.encodeOpenIDAndSessionKey(openid,session_key));
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
