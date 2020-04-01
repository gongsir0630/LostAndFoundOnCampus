package com.gongsir.wxapp.controller.admin;

import com.gongsir.wxapp.utils.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author gongsir
 * @date 2020/2/24 15:32
 * 编码不要畏惧变化，要拥抱变化
 */
@RestController
@RequestMapping("/admin/wx")
public class WxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);

    private static final String TOKEN = "LssILoveYou";

    @GetMapping(path = "")
    public void get(HttpServletRequest request, HttpServletResponse response){

        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        LOGGER.info("the signature from wx server:{}",signature);
        String[] array = new String[] { TOKEN, timestamp, nonce };
        StringBuilder sb = new StringBuilder();
        // 字符串排序
        Arrays.sort(array);
        for (String s : array) {
            sb.append(s);
        }
        String str = sb.toString();
        String code = SHA1.getSha1(str);
        LOGGER.info("the signature from my server:{}",code);

        try (PrintWriter out = response.getWriter()) {
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (code.equals(signature)) {
                out.print(echostr);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
