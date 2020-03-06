package com.gongsir.wxapp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.Good;
import com.gongsir.wxapp.model.User;
import com.gongsir.wxapp.service.GoodService;
import com.gongsir.wxapp.service.UserService;
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
@Api(tags = "小程序物品相关管理接口")
@RestController
@RequestMapping(value = "/admin/good")
public class GoodAdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodAdminController.class);

    @Resource
    private GoodService goodService;
    @Resource
    private UserService userService;

    @GetMapping(path = "goods")
    @ApiOperation(value = "获取所有小程序good信息",notes = "如果不传递参数，则默认加载所有，传递参数则按条件匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "查询条件：all（查询全部，默认）、stuNum（按学号查找）、class（按分类查找）、keywords（关键字查找）、openid（用户查找）、status（状态查找:no(未认领)、ok（已认领））"),
            @ApiImplicitParam(name = "value",value = "key分类下需要查找的值"),
            @ApiImplicitParam(name = "page",value = "当前页码:1"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量:10、20、50等（默认10）"),
    })
    public JSONObject getAllGoods(@RequestParam(value = "key",defaultValue = "all") String key,
                                  @RequestParam(value = "value",defaultValue = "",required = false) String value,
                                   @RequestParam(value = "limit",defaultValue = "10") int limit,
                                   @RequestParam(value = "page",defaultValue = "1") int page){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        List<Good> goods = new ArrayList<>();
        long count;
        if ("all".equalsIgnoreCase(key)){
            goods = goodService.selectAllGoods(page,limit,1);
            count = goodService.getAllCount();
        }else if ("stuNum".equalsIgnoreCase(key)){
            goods = goodService.selectByStuNum(value,page,limit);
            count = goodService.countByStuNum(value);
        }else if ("class".equalsIgnoreCase(key)){
            goods = goodService.selectGoodsByClass(value,page,limit);
            count = goodService.countByClass(value);
        }else if ("keywords".equalsIgnoreCase(key)){
            goods = goodService.selectByKeyWords(value,page,limit);
            count = goodService.countByKeyWords(value);
        }else if ("openid".equalsIgnoreCase(key)){
            goods = goodService.selectGoodsByOpenID(value,page,limit);
            count = goodService.countByOpenID(value);
        }else if ("status".equalsIgnoreCase(key)){
            goods = goodService.selectByGoodStatus(value,page,limit);
            count = goodService.countByGoodStatus(value);
        }else {
            goods = goodService.selectAllGoods(page,limit,1);
            count = goodService.getAllCount();
        }
        //查询发布者信息
        for (Good good :
                goods) {
            User user = userService.selectUserByOpenID(good.getOpenid());
            good.setUser(user);
        }
        data.put("goods",goods);
        data.put("count",count);
        result.put("code",100);
        result.put("message","success");
        result.put("data",data);
        return result;
    }

    @DeleteMapping(path = "del")
    @ApiOperation(value = "根据用户id删除good信息", notes = "批量删除时，多个id使用英文逗号隔开")
    @ApiImplicitParam(name = "ids",value = "id组成的字符串，多个id用逗号隔开")
    public JSONObject deleteGoodsByIds(String ids){
        JSONObject result = new JSONObject();
        List<Integer> list = new ArrayList<>();
        if (null != ids){
            String[] idArr = ids.split(",");
            for (String id:idArr){
                list.add(Integer.valueOf(id));
            }
            int rs;
            if (list.size()==1){
                rs = goodService.deleteGoodByPk(list.get(0));
            }else {
                rs = goodService.deleteGoodByIds(list);
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
    @ApiOperation(value = "根据id修改小程序good信息，id为必须字段")
    public JSONObject updateGoodById(Good good){
        JSONObject result = new JSONObject();
        int rs = goodService.updateGoodByPk(good);
        if (rs>0){
            result.put("code",100);
            result.put("message","信息修改成功");
        }
        return result;
    }
}
