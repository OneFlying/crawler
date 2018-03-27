package com.ld.crawler.web;

import com.Id.crawler.constant.WechatConstant;
import com.ld.crawler.dto.WechatMessage;
import com.ld.crawler.utils.WechatUtil;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestController {
    @RequestMapping("/test")
    public String index(HttpResponse response){
        System.out.print("哈哈哈哈");
        return "TEST";
    }
}
