package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Card;
import com.gongsir.wxapp.model.Listen;
import com.gongsir.wxapp.service.CardService;
import com.gongsir.wxapp.service.ListenService;
import com.gongsir.wxapp.utils.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 龚涛
 * @date 2019/11/3 13:43
 * 编码不要畏惧变化，要拥抱变化
 */
@RestController
@RequestMapping("/wxApi/listen")
public class ListenController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenController.class);

    @Resource
    private ListenService listenService;
    @Resource
    private CardService cardService;

    @PostMapping(path = "listen")
    public JSONObject listenCard(Listen listen,
                                 @RequestParam("sessionKey")String sessionKey){
        JSONObject jsonObject = new JSONObject();
        //先去数据库查找是否存在我需要的证件信息
        List<Card> cards = cardService.selectByNumAndStatus(listen.getLisNum(), "no");
        List<Listen> listens = listenService.selectByOpenId(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]), listen.getLisType(), "no");
        //不存在我需要的证件信息,添加或者更新监听
        if (cards.isEmpty()){
            int rs = 0;
            //之前提交过formID,更新formId
            if (!listens.isEmpty()){
                for (Listen listen1 : listens) {
                    if (Objects.equals(listen.getLisNum(),listen1.getLisNum())){
                        listen1.setFormId(listen.getFormId());
                        int i = listenService.updateListenFormIdByPk(listen1);
                        if (i>0) {
                            rs++;
                        }
                    }
                }
            }else {
                //第一次提交监听
                listen.setLisTime(new Date());
                listen.setOpenid(Base64Util.encodeData(Base64Util.decode2Array(sessionKey)[0]));
                rs = listenService.addListen(listen);
            }
            if (rs>0){
                jsonObject.put("code",rs);
                jsonObject.put("msg","证件信息监听中,请留意微信小程序通知");
                return jsonObject;
            }else {
                jsonObject.put("code",1024);
                jsonObject.put("msg","未知错误error,请联系管理员微信:GT980630");
                return jsonObject;
            }
        }
        //存在证件信息,直接返回
        jsonObject.put("code","ok");
        jsonObject.put("msg","已为你找到相关的证件信息");
        jsonObject.put("cards",cards);
        return jsonObject;
    }
}
