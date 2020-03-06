package com.gongsir.wxapp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Listen;
import com.gongsir.wxapp.service.ListenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gongsir
 * @date 2020/2/15 13:20
 * 编码不要畏惧变化，要拥抱变化
 */
@Api(tags = "小程序证件监听相关管理接口")
@RestController
@RequestMapping(value = "/admin/listen")
public class ListenAdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenAdminController.class);

    @Resource
    private ListenService listenService;

    @GetMapping(path = "cards")
    @ApiOperation(value = "获取所有小程序listen信息",notes = "如果不传递参数，则默认加载所有，传递参数则按条件匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lisNum",value = "证件号码"),
            @ApiImplicitParam(name = "status",value = "认领状态：no（未认领）、ok（已认领）"),
            @ApiImplicitParam(name = "page",value = "当前页码:1"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量:10、20、50等（默认10）"),
    })
    public JSONObject getAllGoods(@RequestParam(value = "lisNum",defaultValue = "") String lisNum,
                                  @RequestParam(value = "status",defaultValue = "",required = false) String status,
                                   @RequestParam(value = "limit",defaultValue = "10") int limit,
                                   @RequestParam(value = "page",defaultValue = "1") int page){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        List<Listen> listens = new ArrayList<>();
        long count;
        listens  = listenService.getAllListens(lisNum,status,page,limit);
        count = listenService.countAllListens(lisNum,status);
        data.put("listens",listens);
        data.put("count",count);
        result.put("code",100);
        result.put("message","success");
        result.put("data",data);
        return result;
    }

    @DeleteMapping(path = "del")
    @ApiOperation(value = "根据用户id删除listen信息", notes = "批量删除时，多个id使用英文逗号隔开")
    @ApiImplicitParam(name = "ids",value = "id组成的字符串，多个id用逗号隔开")
    public JSONObject deleteCardsByIds(String ids){
        JSONObject result = new JSONObject();
        List<Integer> list = new ArrayList<>();
        if (null != ids){
            String[] idArr = ids.split(",");
            for (String id:idArr){
                list.add(Integer.valueOf(id));
            }
            int rs;
            if (list.size()==1){
                rs = listenService.deleteListenById(list.get(0));
            }else {
                rs = listenService.deleteListensByIds(list);
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
    @ApiOperation(value = "根据id修改小程序card监听信息，id为必须字段")
    public JSONObject updateListenById(Listen listen){
        JSONObject result = new JSONObject();
        int rs = listenService.updateListenFormIdByPk(listen);
        if (rs>0){
            result.put("code",100);
            result.put("message","信息修改成功");
        }
        return result;
    }
}
