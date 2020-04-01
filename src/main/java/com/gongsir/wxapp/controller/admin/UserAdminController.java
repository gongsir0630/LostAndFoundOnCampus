package com.gongsir.wxapp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.model.User;
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
@Api(tags = "小程序用户相关管理接口")
@RestController
@RequestMapping(value = "/admin/user")
public class UserAdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminController.class);

    @Resource
    private UserService userService;

    @GetMapping(path = "users")
    @ApiOperation(value = "获取所有小程序用户信息",notes = "如果不传递参数，则默认加载所有，传递参数则按条件匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuNum",value = "用户学号：201731061426"),
            @ApiImplicitParam(name = "app",value = "小程序类型：QQ（qq）、微信（wx）"),
            @ApiImplicitParam(name = "page",value = "当前页码:1"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量:10、20、50等（默认10）"),
    })
    public JSONObject getAllUsers(@RequestParam(value = "stuNum",defaultValue = "",required = false) String username,
                                   @RequestParam(value = "app",defaultValue = "",required = false) String app,
                                   @RequestParam(value = "limit",defaultValue = "10") int limit,
                                   @RequestParam(value = "page",defaultValue = "1") int page){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("users",userService.getAllUsers(username,app,limit,page));
        data.put("count",userService.countAllUsers(username,app));
        result.put("code",100);
        result.put("message","success");
        result.put("data",data);
        return result;
    }

    @DeleteMapping(path = "del")
    @ApiOperation(value = "根据用户id删除用户信息", notes = "批量删除时，多个id使用英文逗号隔开")
    @ApiImplicitParam(name = "ids",value = "id组成的字符串，多个id用逗号隔开")
    public JSONObject deleteUsersByIds(String ids){
        JSONObject result = new JSONObject();
        List<Integer> list = new ArrayList<>();
        if (null != ids){
            String[] idArr = ids.split(",");
            for (String id:idArr){
                list.add(Integer.valueOf(id));
            }
            int rs;
            if (list.size()==1){
                rs = userService.deleteUserById(list.get(0));
            }else {
                rs = userService.deleteUsersByIds(list);
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
    @ApiOperation(value = "根据id修改小程序用户信息，id为必须字段")
    public JSONObject updateUserById(User user){
        JSONObject result = new JSONObject();
        int rs = userService.updateUserById(user);
        if (rs>0){
            result.put("code",100);
            result.put("message","信息修改成功");
        }
        return result;
    }
}
