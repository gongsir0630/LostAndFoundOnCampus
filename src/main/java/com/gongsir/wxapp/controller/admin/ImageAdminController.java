package com.gongsir.wxapp.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.gongsir.wxapp.configuration.AdminApiInterceptor;
import com.gongsir.wxapp.model.Image;
import com.gongsir.wxapp.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gongsir
 * @date 2020/2/15 13:20
 * 编码不要畏惧变化，要拥抱变化
 */
@Api(tags = "小程序轮播图相关管理接口")
@RestController
@RequestMapping(value = "/admin/image")
public class ImageAdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageAdminController.class);

    @Resource
    private ImageService imageService;

    /**
     * 读取配置文件的图片存储路径
     */
    @Value("${upload.location}")
    private String filePath;

    /**
     * 用此格式的日期对上传的图片进行分类
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @GetMapping(path = "images")
    @ApiOperation(value = "获取所有小程序图片信息",notes = "如果不传递参数，则默认加载所有，传递参数则按条件匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status",value = "图片状态：ok（显示）、no（不显示）、空参数（显示所有）"),
            @ApiImplicitParam(name = "page",value = "当前页码:1"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量:10、20、50等（默认10）"),
    })
    public JSONObject getAllImages(@RequestParam(value = "status",required = false) String status,
                                   @RequestParam(value = "limit",defaultValue = "10") int limit,
                                   @RequestParam(value = "page",defaultValue = "1") int page){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        List<Image> images = new ArrayList<>();
        long count;
        images  = imageService.getAllImages(status,page,limit);
        count = imageService.countAllImages(status);
        data.put("images",images);
        data.put("count",count);
        result.put("code",100);
        result.put("message","success");
        result.put("data",data);
        return result;
    }

    @DeleteMapping(path = "del")
    @ApiOperation(value = "根据用户id删除image信息", notes = "批量删除时，多个id使用英文逗号隔开")
    @ApiImplicitParam(name = "ids",value = "id组成的字符串，多个id用逗号隔开")
    public JSONObject deleteImagesByIds(String ids){
        JSONObject result = new JSONObject();
        List<Integer> list = new ArrayList<>();
        if (null != ids){
            String[] idArr = ids.split(",");
            for (String id:idArr){
                list.add(Integer.valueOf(id));
            }
            int rs;
            if (list.size()==1){
                rs = imageService.deleteImageById(list.get(0));
            }else {
                rs = imageService.deleteImagesByIds(list);
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
    @ApiOperation(value = "根据id修改小程序image信息，id为必须字段")
    public JSONObject updateImageById(Image image,
                                      HttpServletRequest request){
        JSONObject result = new JSONObject();
        Map admin = (Map) request.getAttribute(AdminApiInterceptor.USER_INFO_KEY);
        String admId = admin.get("username").toString();
        //设置更信人
        image.setUsername(admId);
        //设置更新时间
        image.setUpdatetime(new Date());
        int rs = imageService.updateImageById(image);
        if (rs>0){
            result.put("code",100);
            result.put("message","新增图片成功");
        }
        return result;
    }

    @PostMapping(path = "add")
    @ApiOperation(value = "新增图片")
    public JSONObject addImage(Image image,
                               HttpServletRequest request){
        JSONObject result = new JSONObject();
        Map admin = (Map) request.getAttribute(AdminApiInterceptor.USER_INFO_KEY);
        String admId = admin.get("username").toString();
        //设置更信人
        image.setUsername(admId);
        //设置更新时间
        image.setUpdatetime(new Date());
        int rs = imageService.saveImage(image);
        if (rs>0){
            result.put("code",100);
            result.put("message","新增图片成功");
        }
        return result;
    }

    @PostMapping("upload")
    @ApiOperation(value = "上传图片，获得url")
    public JSONObject uploadImage(@RequestParam("file") MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        LOGGER.info("图片上传请求");
        if (file.isEmpty()){
            jsonObject.put("code",101);
            jsonObject.put("message","图片为空,请选择一张图片");
            return jsonObject;
        }
        //根据日期创建文件夹,对文件进行分类
        String format = sdf.format(new Date());
        File folder = new File(filePath+format);
//        File folder = new File(filePath);
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
            LOGGER.info("图片上传成功");
            String url = "/uploadImg/"+format+newName;
            jsonObject.put("code",100);
            jsonObject.put("message","图片上传成功");
            jsonObject.put("url",url);
            return jsonObject;
//            return "/uploadImg/"+newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObject.put("code",102);
        jsonObject.put("message","上传异常,请重试");
        return jsonObject;
    }
}
