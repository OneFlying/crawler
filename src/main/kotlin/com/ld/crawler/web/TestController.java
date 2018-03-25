package com.ld.crawler.web;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/")
    public String index(HttpResponse response){
        System.out.print("哈哈哈哈");
        return "TEST";
    }
}
