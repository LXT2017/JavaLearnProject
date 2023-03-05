package com.example.security.controller;

import com.example.security.pojo.CheckCode;
import com.example.security.pojo.Constants;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author: PhilChan
 * @date: 2022/5/19 上午 10:13
 * Description:
 */
@RestController
@CrossOrigin
public class ValidateController {

    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    // 验证码图片边框宽度
    private int WIDTH = 120;
    // 验证码图片边框高度
    private int HEIGHT = 45;
    // 验证码有效时间 60s
    private int expireIn = 60;

    // 普通验证码
    private int length = 4; // 验证码位数

    @GetMapping("/captcha/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应报头信息
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 设置响应的MIME类型
        response.setContentType("image/jpeg");

        //画板
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics g = image.getGraphics();
        //字体
        Font font = new Font("微软雅黑", Font.BOLD,35);
        //设置字体
        g.setFont(font);

        //引入背景图片
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //随机数
        Random random = new Random();
        //要随机的字符串
        String template = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder s = new StringBuilder();
        char tempNum;
        for (int i = 0; i < length; i++){
            //获取随机出的字符
            int tempIndex = random.nextInt(template.length()-1);
            tempNum = template.charAt(tempIndex);
            //拼成字符串
            s.append(tempNum);
            //设置颜色
            Color color = new Color(20+random.nextInt(110),20+random.nextInt(110),random.nextInt(110));
            g.setColor(color);
            //字母写入图片
            g.drawString(String.valueOf(tempNum),25 * i + 12, 32);
        }

        // 放入session缓存，默认60s过期
        CheckCode checkCode = new CheckCode(s.toString().toLowerCase(),expireIn);
        HttpSession se = request.getSession();
        se.setAttribute(Constants.KAPTCHA_SESSION_KEY, checkCode);


        //获取流发送给前台
        ServletOutputStream ots = response.getOutputStream();
        ImageIO.write(image,"JPEG",ots);
    }


}