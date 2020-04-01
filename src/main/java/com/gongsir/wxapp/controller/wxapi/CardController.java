package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Card;
import com.gongsir.wxapp.model.Listen;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.service.CardService;
import com.gongsir.wxapp.service.ListenService;
import com.gongsir.wxapp.service.UserService;
import com.gongsir.wxapp.utils.Base64Util;
import com.gongsir.wxapp.utils.OCRUtil;
import com.gongsir.wxapp.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 龚涛
 * @date 2019/10/28 10:31
 * 编码不要畏惧变化，要拥抱变化
 */
@RestController
@RequestMapping("/wxApi/card")
public class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Resource
    CardService cardService;
    @Resource
    ListenService listenService;
    @Resource
    UserService userService;

    /**
     * 证件识别接口
     * @param file 图片二进制文件
     * @param type 证件类型
     * @return 证件信息集
     * @throws Exception 异常处理
     */
    @PostMapping(path = "ocr")
    public JSONObject ocr(@RequestParam("file") MultipartFile file,
                          @RequestParam("type") String type) throws Exception {
        logger.info("ocr识别开始,识别类型:"+type);
        return OCRUtil.ocr(file,type);
    }

    /**
     * 添加证件信息
     * @param card 证件信息对象封装
     * @return 添加状态
     */
    @PostMapping(path = "add")
    public JSONObject add(@RequestParam("sessionKey")String sessionKey,
                          Card card){
        logger.info("添加证件丢失信息:{}",card);
        //解析出openID,然后加密存进数据库
        card.setOpenid(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]));
        card.setCardTime(new Date());
        //设置状态为未找到
        card.setCardStatus("no");
        card.setRelation(card.getRelation().replaceFirst("place","指定地点领取").replaceFirst("tel","联系电话").replaceFirst("qq","联系QQ"));
        int rs = cardService.saveCard(card);
        JSONObject jsonObject = new JSONObject();
        boolean flag = false;
        //向数据库插入数据后,遍历listen表,是否有同学提交监听信息匹配
        if (rs>0){
            //按照证件号,证件类型,证件状态匹配,no标识还未找到的
            List<Listen> listens = listenService.selectByLisNum(card.getCardNum(),card.getCardType(),"no");
            //java8利用tree set对openID去重
            listens = listens.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(Listen::getOpenid))), ArrayList::new));
            if (!listens.isEmpty()){
                for (Listen lis :
                        listens) {
                    if ("wx".equals(userService.selectUserByOpenID(lis.getOpenid()).getUserApp())){
                        flag = UserUtil.wxMessagePush(lis.getOpenid(),card);
                    }else {
                        flag = UserUtil.qqMessagePush(lis.getOpenid(),card,lis.getFormId());
                    }
                }
            }
            //消息是否推送成功
            if (flag){
                jsonObject.put("code",rs);
                jsonObject.put("msg","证件信息发布成功,已通知失主联系领取");
                jsonObject.put("data",cardService.selectByPk(rs));
            }else {
                jsonObject.put("code",rs);
                jsonObject.put("msg","证件信息发布成功");
                jsonObject.put("data",cardService.selectByPk(rs));
            }
            logger.info("返回信息:{}",jsonObject);
            return jsonObject;
        }else {
            jsonObject.put("code",1024);
            jsonObject.put("msg","error,证件信息发布失败");
            jsonObject.put("data",card);
        }
        logger.info("返回信息:{}",jsonObject);
        return jsonObject;
    }

    /**
     * 查询用户自己已经发布的所有证件信息
     * @param sessionKey 身份认证标识
     * @param page 页码
     * @param limit 每页显示数量
     * @return goods集合
     */
    @GetMapping(path = "my")
    public JSONObject myCard(@RequestParam("sessionKey") String sessionKey,
                            @RequestParam("page") int page,
                            @RequestParam("limit") int limit){
        JSONObject jsonObject = new JSONObject();
        String openid = Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]);
        return getJsonObject(page,limit,openid,jsonObject);
    }

    /**
     * 查询用户已经发布的所有证件信息
     * @param openid 身份认证标识
     * @param page 页码
     * @param limit 每页显示数量
     * @return goods集合
     */
    @GetMapping(path = "his")
    public JSONObject hisCard(@RequestParam("openid") String openid,
                            @RequestParam("page") int page,
                            @RequestParam("limit") int limit){
        JSONObject jsonObject = new JSONObject();
        return getJsonObject(page,limit,openid,jsonObject);
    }

    private JSONObject getJsonObject(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("openid") String openid, JSONObject jsonObject) {
        List<Card> cards = cardService.selectByOpenId(openid, page, limit);
        User user = userService.selectUserByOpenID(openid);
        cards.forEach(card -> card.setUser(user));
        jsonObject.put("count",cardService.countByOpenId(openid));
        jsonObject.put("msg","data access success");
        jsonObject.put("cards",cards);
        return jsonObject;
    }

    /**
     * 证件认领，一旦被人认领，不再显示在主界面，通过学号认领
     * @param sessionKey 认领人
     * @param cid 证件id
     * @return 认领状态
     */
    @PostMapping(path = "found")
    public JSONObject foundCardByStuNum(@RequestParam("sessionKey")String sessionKey,@RequestParam("cid")int cid){
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
        Card card = cardService.selectByPk(cid);
        if ("no".equalsIgnoreCase(card.getCardStatus())){
            card.setCardStatus(stuNum);
            rs = cardService.updateByPk(card);
            Listen listen = new Listen();
            listen.setLisNum(card.getCardNum());
            listen.setLisStatus(stuNum);
            rs += listenService.updateListenByCardNum(listen);
        }
        if (rs>0){
            jsonObject.put("code",rs);
            jsonObject.put("msg", "认领成功");
        }else {
            jsonObject.put("code", 1024);
            jsonObject.put("msg","对不起，该物品已被人认领");
        }
        return jsonObject;
    }

    /**
     * 用户删除自己发布的证件信息
     * @param id 发布的证件信息id
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
        if (Objects.equals(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]), cardService.selectByPk(Integer.valueOf(id)).getOpenid())){
            int rs = cardService.deleteByPrimaryKey(Integer.valueOf(id));
            jsonObject.put("code",rs);
            jsonObject.put("msg","删除成功");
            jsonObject.put("goods",cardService.selectByOpenId(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]),page,limit));
            return jsonObject;
        }else {
            jsonObject.put("code",1024);
            jsonObject.put("msg","抱歉,您不具备权限删除该数据,如有疑问,可咨询管理员");
            return jsonObject;
        }
    }

    /**
     * 通过id查询信息
     * @param id 证件唯一id
     * @return 证件信息
     */
    @PostMapping("find")
    public JSONObject getCardById(@RequestParam("id") int id){
        logger.info("通过id查找证件信息");
        JSONObject jsonObject = new JSONObject();
        Card card = cardService.selectByPk(id);
        if (card!=null){
            jsonObject.put("code","ok");
            jsonObject.put("msg","查询成功");
            jsonObject.put("card",card);
        }else {
            jsonObject.put("code","fail");
            jsonObject.put("msg","查询失败");
        }
        return jsonObject;
    }

    /**
     * 已认领的证件
     * @param sessionKey 用户身份
     * @return 近10条数据
     */
    @GetMapping(path = "hasFound")
    public JSONObject cardHasFound(@RequestParam("sessionKey") String sessionKey){
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
        List<Card> cards = cardService.hasFound(stuNum);
        cards.forEach(card -> card.setUser(userService.selectUserByOpenID(card.getOpenid())));
        jsonObject.put("cards",cards);
        jsonObject.put("msg","只显示近10条数据");
        jsonObject.put("code",cards.size());
        logger.info("返回信息:{}",jsonObject);
        return jsonObject;
    }
}
