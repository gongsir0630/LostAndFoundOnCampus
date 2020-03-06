package com.gongsir.wxapp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.AdminApiInterceptor;
import com.gongsir.wxapp.configuration.UserConstantInterface;
import com.gongsir.wxapp.model.Admin;
import com.gongsir.wxapp.model.Log;
import com.gongsir.wxapp.service.AdminService;
import com.gongsir.wxapp.utils.AliMsgSend;
import com.gongsir.wxapp.utils.IpInfoUtils;
import com.gongsir.wxapp.utils.JwtTokenUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author gongsir
 * @date 2020/2/15 13:20
 * 编码不要畏惧变化，要拥抱变化
 */
@Api(tags = "后台管理用户相关接口")
@RestController
@RequestMapping(value = "/admin/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 发送短信验证码
     * @param phone 手机号
     * @param request 请求信息
     * @return 验证码
     */
    @ApiOperation(value = "获取手机验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "大陆手机号")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "验证码发送状态：success表示发送成功，其他msg均为失败")
    })
    @PostMapping(path = "code")
    public JSONObject getMsgCode(@RequestParam("phone") String phone, HttpServletRequest request){
        JSONObject result = new JSONObject();
        String ip = getIpAddress(request);
        LOGGER.info("=====>>短信验证码请求ip地址：{}",ip);
        JSONObject params = new JSONObject();
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (code != null){
            params.put("code",code);
        }else {
            //生成新的验证码
            params = AliMsgSend.getCode();
            //15分钟有效
            redisTemplate.opsForValue().set(phone,params.get("code").toString(),15, TimeUnit.MINUTES);
        }
        String message = AliMsgSend.sendVerification(UserConstantInterface.SIGN_NAME, UserConstantInterface.TEMPLATE_CODE, phone, params);
        result.put("code",0);
        result.put("message",message);
        return result;
    }

    /**
     * 验证用户名是否存在
     * @param id 用户名
     * @return 存在状态
     */
    @ApiOperation(value = "验证用户是否已经存在")
    @ApiImplicitParam(name = "id",value = "用户账号")
    @ApiResponses({
            @ApiResponse(code = 0,message = "该账号已经存在"),
            @ApiResponse(code = 1,message = "该账号可以注册")
    })
    @GetMapping(path = "check")
    public JSONObject checkAdmId(String id){
        JSONObject result = new JSONObject();
        Admin admin = adminService.selectAdminByAdmId(id);
        if (admin != null){
            result.put("code",0);
            result.put("status",false);
            result.put("message","该账号已经存在");
        } else {
            result.put("code",1);
            result.put("status",true);
            result.put("message","该账号可以注册");
        }
        return result;
    }

    /**
     * 管理员注册
     * @param code 短信验证码
     * @param admin 管理员注册信息
     * @return 注册结果
     */
    @ApiOperation(value = "管理员注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "验证码")
    })
    @PostMapping(path = "register")
    public JSONObject register(@RequestParam("code") String code,
                               Admin admin){
        JSONObject result = new JSONObject();
        //验证码错误
        if (!code.equalsIgnoreCase(String.valueOf(redisTemplate.opsForValue().get(admin.getTelephone())))){
            result.put("status",false);
            result.put("message","短信验证码错误, 请重试");
            return result;
        }
        //验证通过
        //验证账户是否存在
        JSONObject checkResult = checkAdmId(admin.getAdmId());
        //账户存在
        if (!Boolean.valueOf(checkResult.get("status").toString())){
            return checkResult;
        }
        //账户不存在，进入注册
        //设置不可登陆，须有其他管理员审核才可登录
        admin.setStatus("no");
        try {
            int rs = adminService.saveAdmin(admin);
            if (rs > 0){
                result.put("code",rs);
                result.put("message","注册成功, 请等待其他管理员审核");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",1024);
            result.put("message","服务异常, 请稍后重试");
        }
        return result;
    }

    @ApiOperation(value = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "账号"),
            @ApiImplicitParam(name = "password",value = "密码")
    })
    @ApiResponses({
            @ApiResponse(code = -1,message = "can't find user"),
            @ApiResponse(code = 0,message = "password is error"),
            @ApiResponse(code = 1,message = "login success"),
            @ApiResponse(code = 2,message = "You cannot login system for the time being")
    })
    @PostMapping(path = "login")
    public JSONObject login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Admin admin = adminService.selectAdminByAdmId(username);
        //用户不存在
        if (admin == null){
            jsonObject.put("code",-1);
            jsonObject.put("message","can't find user");
            LOGGER.info("=====>> login:{}",jsonObject);
            return jsonObject;
        }
        //用户存在,验证密码
        if (!password.equals(admin.getAdmPass())){
            jsonObject.put("code",0);
            jsonObject.put("message","password is error");
            LOGGER.info("=====>> login:{}",jsonObject);
            return jsonObject;
        }
        //密码正确
        //检查是否允许login
        if ("no".equalsIgnoreCase(admin.getStatus())){
            jsonObject.put("code",2);
            jsonObject.put("message","You cannot login system for the time being");
            LOGGER.info("=====>> login:{}",jsonObject);
            return jsonObject;
        }
        //校验通过
        Map<String,Object> user = new HashMap<>(2);
        user.put("username",admin.getAdmId());
        user.put("name",admin.getRealName());
        user.put("status",admin.getStatus());
        //创建token
        String token = JwtTokenUtils.createToken(user,false);
        //获取ip存取
        String ip = getIpAddress(request);
        //解析ip地理位置
        String address = IpInfoUtils.getIpInfo(ip);
        //封装在线信息
        Log log = new Log(admin.getAdmId(),
                ip,address,new Date(),true);
        redisTemplate.opsForList().leftPush(admin.getAdmId(),log);
        //获取上次登录的ip等信息
        Log lastLog = (Log) redisTemplate.opsForList().index(admin.getAdmId(), 1);
        if (null == lastLog){
            lastLog = log;
        }
        JSONObject data = new JSONObject();
        data.put("token",token);
        data.put("username",admin);
        data.put("log",lastLog);
        jsonObject.put("code",1);
        jsonObject.put("message","login success");
        jsonObject.put("data",data);
        LOGGER.info("=====>> login:{}",jsonObject);
        return jsonObject;
    }

    @GetMapping(path = "admins")
    @ApiOperation(value = "获取所有管理员信息",notes = "如果不传递参数，则默认加载所有，传递参数则按条件匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户账号：201731061426"),
            @ApiImplicitParam(name = "status",value = "用户状态：超管（super）、管理员（admin）、禁止登录（no）"),
            @ApiImplicitParam(name = "page",value = "当前页码:1"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量:10、20、50等（默认10）"),
    })
    public JSONObject getAllAdmins(@RequestParam(value = "username",defaultValue = "",required = false) String username,
                                   @RequestParam(value = "status",defaultValue = "",required = false) String status,
                                   @RequestParam(value = "limit",defaultValue = "10") int limit,
                                   @RequestParam(value = "page",defaultValue = "1") int page){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("admins",adminService.getAllAdmins(username,status,limit,page));
        data.put("count",adminService.countAllAdmins(username,status));
        result.put("code",100);
        result.put("message","success");
        result.put("data",data);
        return result;
    }

    @PostMapping(path = "add")
    @ApiOperation(value = "添加系统管理员")
    public JSONObject addAdmin(Admin admin,HttpServletRequest request){
        JSONObject result = new JSONObject();
        if (!checkAuthentication("super",request)){
            result.put("code",103);
            result.put("message","对不起，你没有添加权限");
            return result;
        }
        //验证账户是否存在
        JSONObject checkResult = checkAdmId(admin.getAdmId());
        //账户存在
        if (!Boolean.valueOf(checkResult.get("status").toString())){
            return checkResult;
        }
        //管理员添加，可以直接登录
        admin.setStatus("admin");
        try {
            int rs = adminService.saveAdmin(admin);
            if (rs > 0){
                result.put("code",100);
                result.put("message","成功添加管理员："+admin.getRealName());
            }
        } catch (Exception e) {
            result.put("code",1024);
            result.put("message","服务异常, 请稍后重试");
        }
        return result;
    }

    @DeleteMapping(path = "del")
    @ApiOperation(value = "根据管理员id删除管理员信息", notes = "批量删除时，多个id使用英文逗号隔开")
    @ApiImplicitParam(name = "ids",value = "id组成的字符串，多个id用逗号隔开")
    public JSONObject deleteAdminsByIds(String ids,HttpServletRequest request){
        JSONObject result = new JSONObject();
        if (null == ids){
            result.put("code",101);
            result.put("message","请传入正确的id集合");
            return result;
        }
        String[] idArr = ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s:idArr){
            list.add(Integer.valueOf(s));
        }
        if (!checkAuthentication("super",request)){
            result.put("code",103);
            result.put("message","对不起，你没有删除权限");
            return result;
        }
        int rs;
        if (list.size()==1){
            rs = adminService.deleteAdminById(list.get(0));
        }else {
            rs = adminService.deleteAdminsByIds(list);
        }
        if (rs>0){
            result.put("code",100);
            result.put("message","删除成功");
        }
        return result;
    }

    @PutMapping(path = "update")
    @ApiOperation(value = "修改管理员信息")
    public JSONObject updateAdminById(Admin admin,HttpServletRequest request){
        JSONObject result = new JSONObject();
        if (!checkAuthentication("super",request)){
            result.put("code",103);
            result.put("message","对不起，你没有修改权限");
            return result;
        }
        int rs = adminService.updateAdminByAdmId(admin);
        if (rs>0){
            result.put("code",100);
            result.put("message","信息修改成功");
        }
        return result;
    }


    private Boolean checkAuthentication(String role, HttpServletRequest request){
        Map user = (Map) request.getAttribute(AdminApiInterceptor.USER_INFO_KEY);
        /* 简单权限验证 */
        if (null != user){
            return role.equalsIgnoreCase(user.get("status").toString());
        }
        return false;
    }

    /**
     * 获取客户端IP地址
     * @param request 请求信息
     * @return ip地址
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost ();
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                ip = inet != null ? inet.getHostAddress() : null;
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length () > 15) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));
            }
        }
        return ip;
    }
}
