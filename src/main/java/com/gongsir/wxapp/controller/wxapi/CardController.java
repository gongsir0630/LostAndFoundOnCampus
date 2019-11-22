package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Card;
import com.gongsir.wxapp.model.Listen;
import com.gongsir.wxapp.service.CardService;
import com.gongsir.wxapp.service.ListenService;
import com.gongsir.wxapp.service.UserService;
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
    UserService userService;
    @Resource
    ListenService listenService;

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
    public JSONObject add(Card card){
        logger.info("添加证件丢失信息:{}",card);
        card.setCardTime(new Date());
        //设置状态为未找到
        card.setCardStatus("no");
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
                    flag = UserUtil.messagePush(lis.getOpenid(),card,lis.getFormId());
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
}
