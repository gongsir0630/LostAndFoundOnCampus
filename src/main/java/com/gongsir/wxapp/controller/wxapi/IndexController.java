package com.gongsir.wxapp.controller.wxapi;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Image;
import com.gongsir.wxapp.model.Notice;
import com.gongsir.wxapp.service.ImageService;
import com.gongsir.wxapp.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/24 12:44
 * 编码不要畏惧变化，要拥抱变化
 */
@Api(tags = "小程序首页公告、轮播图相关接口")
@RestController
@RequestMapping(value = "/wxApi/index")
public class IndexController {
    @Resource
    private NoticeService noticeService;

    @Resource
    private ImageService imageService;

    @GetMapping(path = "notices")
    public JSONObject getNotices(@RequestParam(value = "page",defaultValue = "1") int page,
                                 @RequestParam(value = "limit",defaultValue = "10") int limit){
        JSONObject res = new JSONObject();
        //获取公告内容
        List<Notice> notices = noticeService.getAllNotices("ok", page, limit);
        long count = noticeService.countAllNotices("ok");
        res.put("notices",notices);
        res.put("notice_count",count);
        return res;
    }

    @GetMapping(path = "images")
    public JSONObject getAllImages(@RequestParam(value = "page",defaultValue = "1") int page,
                                 @RequestParam(value = "limit",defaultValue = "10") int limit){
        JSONObject res = new JSONObject();
        List<Image> images = imageService.getAllImages("ok", page, limit);
        long count = imageService.countAllImages("ok");
        res.put("images",images);
        res.put("image_count",count);
        return res;
    }
}
