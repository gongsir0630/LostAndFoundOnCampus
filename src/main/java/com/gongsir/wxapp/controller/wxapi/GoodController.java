package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Good;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.service.GoodService;
import com.gongsir.wxapp.service.UserService;
import com.gongsir.wxapp.utils.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 龚涛
 * @date 2019/10/25 14:34
 * 编码不要畏惧变化，要拥抱变化
 */
@RestController
@RequestMapping("/wxApi/good")
public class GoodController {
    private static final Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Resource
    GoodService goodService;
    @Resource
    UserService userService;

    /**
     * 读取配置文件的图片存储路径
     */
    @Value("${upload.location}")
    private String filePath;

    /**
     * 用此格式的日期对上传的图片进行分类
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    /**
     * 返回所有的goods数据
     * @param page 页数
     * @param limit 数量
     * @param sort 排序规则，sort=1 升序，sort=2 降序
     * @return list集合
     */
    @GetMapping("all")
    public JSONObject all(int page, int limit, @RequestParam(value = "sort",defaultValue = "2") int sort){
        JSONObject jsonObject = new JSONObject();
        List<Good> goods = goodService.selectAllGoods(page,limit,sort);
        jsonObject.put("msg","data access success");
        jsonObject.put("count",goodService.getAllCount());
        //查询发布者信息
        for (Good good :
                goods) {
            User user = userService.selectUserByOpenID(good.getOpenid());
            good.setUser(user);
        }
        jsonObject.put("goods",goods);
        return jsonObject;
    }

    /**
     * 添加丢失物品信息
     * @param file 图片文件
     * @param sessionKey 登录状态,加密的openID
     * @param good 信息
     * @return json结果集
     */
    @PostMapping("add")
    public JSONObject addGood(@RequestParam("file") MultipartFile file,
                              @RequestParam("sessionKey")String sessionKey,
                              Good good){
        //解析出openID,然后加密存进数据库
        good.setOpenid(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]));
        JSONObject jsonObject = new JSONObject();
        logger.info("图片上传请求");
        if (file.isEmpty()){
            jsonObject.put("code",1024);
            jsonObject.put("msg","图片上传失败,请重新选择图片");
            return jsonObject;
        }
        //根据日期创建文件夹,对文件进行分类
        String format = sdf.format(new Date());
        File folder = new File(filePath+format);
        //文件夹不存在,新建
        if (!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        //文件重命名,防止重复
        String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        //文件保存,并将路径写入数据库
        try {
            file.transferTo(new File(folder,newName));
            logger.info("图片上传成功");
            good.setGoodImage("/uploadImg/"+format+newName);
            good.setTime(new Date());
            good.setGoodStatus("no");
            good.setRelation(good.getRelation().replaceFirst("place","指定地点领取").replaceFirst("tel","联系电话").replaceFirst("qq","联系QQ"));
            int rs = goodService.saveGood(good);
            jsonObject.put("msg","信息发布成功");
            jsonObject.put("code",rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 用户删除自己发布的信息
     * @param id 发布的信息id
     * @param page 当前页数
     * @param limit 每页显示数量
     * @param sessionKey 身份标识
     * @return 删除后新的数据
     */
    @PostMapping(path = "del")
    public JSONObject deleteGoodByPk(@RequestParam("id") String id,
                                     @RequestParam("page") int page,
                                     @RequestParam("limit") int limit,
                                     @RequestParam("sessionKey") String sessionKey){
        JSONObject jsonObject = new JSONObject();
        if (Objects.equals(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]), goodService.selectBGoodyPk(Integer.valueOf(id)).getOpenid())){
            int rs = goodService.deleteGoodByPk(Integer.valueOf(id));
            jsonObject.put("code",rs);
            jsonObject.put("msg","删除成功");
            jsonObject.put("goods",goodService.selectGoodsByOpenID(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]),page,limit));
            return jsonObject;
        }else {
            jsonObject.put("code",1024);
            jsonObject.put("msg","抱歉,您不具备权限删除该数据,如有疑问,可咨询管理员");
            return jsonObject;
        }
    }

    /**
     * 查询用户自己已经发布的所有信息
     * @param sessionKey 身份认证标识
     * @param page 页码
     * @param limit 每页显示数量
     * @return goods集合
     */
    @GetMapping(path = "my")
    public JSONObject myAdd(@RequestParam("sessionKey") String sessionKey,
                            @RequestParam("page") int page,
                            @RequestParam("limit") int limit){
        JSONObject jsonObject = new JSONObject();
        String openid = Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]);
        return getJsonObject(page, limit, openid, jsonObject);
    }

    /**
     * 查询某位用户已经发布的所有信息
     * @param page 页数
     * @param limit 数量
     * @param openid 用户id
     * @return json
     */
    @GetMapping(path = "his")
    public JSONObject someoneAdd(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit,
                                 @RequestParam("openid") String openid){
        JSONObject jsonObject = new JSONObject();
        return getJsonObject(page, limit, openid, jsonObject);
    }

    private JSONObject getJsonObject(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("openid") String openid, JSONObject jsonObject) {
        List<Good> goods = goodService.selectGoodsByOpenID(openid, page, limit);
        User user = userService.selectUserByOpenID(openid);
        for (Good good :
                goods) {
            good.setUser(user);
        }
        jsonObject.put("count",goodService.countByOpenID(openid));
        jsonObject.put("msg","data access success");
        jsonObject.put("goods",goods);
        return jsonObject;
    }

    /**
     * 分类查询
     * @param goodClass 类别
     * @param page 页码
     * @param limit 数量
     * @return 数据
     */
    @GetMapping(path = "class")
    public JSONObject selectByGoodClass(@RequestParam("class") String goodClass,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "8") int limit){
        logger.info("分类查询,类别:{}",goodClass);
        List<Good> goods = goodService.selectGoodsByClass(goodClass, page, limit);
        //查询发布者信息
        for (Good good :
                goods) {
            User user = userService.selectUserByOpenID(good.getOpenid());
            good.setUser(user);
        }
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("count",goodService.countByClass(goodClass));
        jsonObject.put("goods",goods);
        jsonObject.put("msg","data access success");
        return jsonObject;
    }

    /**
     * 关键字查询
     * @param keyword 关键字
     * @param page 页码
     * @param limit 数量
     * @return 搜索
     */
    @GetMapping(path = "search")
    public JSONObject selectByKeyWords(@RequestParam("keyword") String keyword,
                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "8") int limit){
        JSONObject jsonObject = new JSONObject();
        List<Good> goods = goodService.selectByKeyWords(keyword, page, limit);
        //查询发布者信息
        for (Good good :
                goods) {
            User user = userService.selectUserByOpenID(good.getOpenid());
            good.setUser(user);
        }
        jsonObject.put("count",goodService.countByKeyWords(keyword));
        jsonObject.put("goods",goods);
        jsonObject.put("msg","data access success");
        return jsonObject;
    }

    /**
     * 物品认领，一旦被人认领，不再显示在主界面，通过学号认领
     * @param sessionKey 认领者
     * @param gid 认领的物品的唯一id
     * @return 返回认领状态
     */
    @PostMapping(path = "found")
    public JSONObject foundByStuNum(@RequestParam("sessionKey")String sessionKey,@RequestParam("gid")int gid){
        JSONObject jsonObject = new JSONObject();
        String openid = Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]);
        User user = userService.selectUserByOpenID(openid);
        String stuNum = null;
        if (user==null){
            jsonObject.put("code",401);
            jsonObject.put("msg","请先授权登录");
            logger.info("返回信息:{}",jsonObject);
            return jsonObject;
        }
        stuNum = user.getStuNum();
        if (stuNum==null || "".equalsIgnoreCase(stuNum)){
            jsonObject.put("code",-1);
            jsonObject.put("msg","请先实名绑定你的学号");
            logger.info("返回信息:{}",jsonObject);
            return jsonObject;
        }
        int rs = 0;
        Good good1 = goodService.selectBGoodyPk(gid);
        if ("no".equalsIgnoreCase(good1.getGoodStatus())){
            Good good = new Good();
            good.setId(gid);
            good.setGoodStatus(stuNum);
            rs = goodService.updateGoodByPk(good);
        }
        if (rs>0){
            jsonObject.put("code",rs);
            jsonObject.put("msg", "操作成功");
        }else if (rs==0){
            jsonObject.put("code",rs);
            jsonObject.put("msg", "操作失败");
        }else {
            jsonObject.put("code", 1024);
            jsonObject.put("msg","对不起，该物品已被人认领");
        }
        return jsonObject;
    }

    /**
     * 已认领
     * @param sessionKey 用户身份
     * @return 已经认领的近10条数据
     */
    @GetMapping(path = "hasFound")
    public JSONObject goodHasFound(@RequestParam("sessionKey")String sessionKey){
        JSONObject jsonObject = new JSONObject();
        String openid = Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]);
        User user = userService.selectUserByOpenID(openid);
        String stuNum = user.getStuNum();
        if (stuNum==null){
            jsonObject.put("code",-1);
            jsonObject.put("msg","请先实名绑定你的学号");
            logger.info("返回信息:{}",jsonObject);
            return jsonObject;
        }
        List<Good> goods = goodService.selectByGoodStatus(stuNum, 1, 10);
        goods.forEach(good -> good.setUser(userService.selectUserByOpenID(good.getOpenid())));
        jsonObject.put("goods",goods);
        jsonObject.put("msg","只显示近10条数据");
        jsonObject.put("code",goods.size());
        logger.info("返回信息:{}",jsonObject);
        return jsonObject;
    }
}
