package com.gongsir.wxapp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.AdminApiInterceptor;
import com.gongsir.wxapp.model.Notice;
import com.gongsir.wxapp.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gongsir
 * @date 2020/2/15 13:20
 * 编码不要畏惧变化，要拥抱变化
 */
@Api(tags = "小程序公告相关管理接口")
@RestController
@RequestMapping(value = "/admin/notice")
public class NoticeAdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeAdminController.class);

    @Resource
    private NoticeService noticeService;

    @GetMapping(path = "notices")
    @ApiOperation(value = "获取所有小程序公告信息",notes = "如果不传递参数，则默认加载所有，传递参数则按条件匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "公告状态：ok（显示）、no（不显示）、空参数（显示所有）"),
            @ApiImplicitParam(name = "page",value = "当前页码:1"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量:10、20、50等（默认10）"),
    })
    public JSONObject getAllNotices(@RequestParam(value = "status",required = false) String status,
                                   @RequestParam(value = "limit",defaultValue = "10") int limit,
                                   @RequestParam(value = "page",defaultValue = "1") int page){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        List<Notice> notices = new ArrayList<>();
        long count;
        notices  = noticeService.getAllNotices(status,page,limit);
        count = noticeService.countAllNotices(status);
        data.put("notices",notices);
        data.put("count",count);
        result.put("code",100);
        result.put("message","success");
        result.put("data",data);
        return result;
    }

    @DeleteMapping(path = "del")
    @ApiOperation(value = "根据用户id删除notice信息", notes = "批量删除时，多个id使用英文逗号隔开")
    @ApiImplicitParam(name = "ids",value = "id组成的字符串，多个id用逗号隔开")
    public JSONObject deleteNoticesByIds(String ids){
        JSONObject result = new JSONObject();
        List<Integer> list = new ArrayList<>();
        if (null != ids){
            String[] idArr = ids.split(",");
            for (String id:idArr){
                list.add(Integer.valueOf(id));
            }
            int rs;
            if (list.size()==1){
                rs = noticeService.deleteNoticeById(list.get(0));
            }else {
                rs = noticeService.deleteNoticesByIds(list);
            }
            if (rs>0){
                result.put("code",100);
                result.put("message","删除成功");
            }
            return result;
        }
        result.put("code",101);
        result.put("message","请传入正确的id集合");
        return result;
    }

    @PutMapping(path = "update")
    @ApiOperation(value = "根据id修改小程序notice信息，id为必须字段")
    public JSONObject updateNoticeById(Notice notice, HttpServletRequest request){
        Map user = (Map) request.getAttribute(AdminApiInterceptor.USER_INFO_KEY);
        String username = user.get("username").toString();
        notice.setAdmId(username);
        notice.setUpdateTime(new Date());
        JSONObject result = new JSONObject();
        int rs = noticeService.updateNoticeById(notice);
        if (rs>0){
            result.put("code",100);
            result.put("message","信息修改成功");
        }
        return result;
    }

    @PostMapping(path = "add")
    @ApiOperation(value = "新增公告")
    public JSONObject addNotice(Notice notice, HttpServletRequest request){
        JSONObject result = new JSONObject();
        Map admin = (Map) request.getAttribute(AdminApiInterceptor.USER_INFO_KEY);
        String admId = admin.get("username").toString();
        //设置更信人
        notice.setAdmId(admId);
        //设置更新时间
        notice.setUpdateTime(new Date());
        int rs = noticeService.saveNotice(notice);
        if (rs>0){
            result.put("code",100);
            result.put("message","新增公告成功");
        }
        return result;
    }
}
