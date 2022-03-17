package com.zqm.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @describe:
 * @author:zqm
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(String loginname, String pwd, String code, HttpSession session){
        //获得存储在session中的验证码
        String sessionCheckCode = (String) session.getAttribute("code");
        //判断验证码是否正确
        if (code!=null && sessionCheckCode.equals(code)){
            //登录成功，返回json的提示。
            return "success";
        }else {
            //登陆失败，提示验证码不正确！
            return "false";
        }
    }

    /**
     * 得到登陆验证码
     * @param response
     * @param session
     * @throws
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //HuTool定义图形验证码的长和宽,验证码的位数，干扰线的条数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,10);
        //将验证码放入session
        session.setAttribute("code",lineCaptcha.getCode());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}