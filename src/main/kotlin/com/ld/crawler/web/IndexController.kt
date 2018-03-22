package com.ld.crawler.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 测试控制器
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 14:04
 */
@RestController
@RequestMapping("/")
class IndexController {

    @RequestMapping("/")
    fun helloKotlin(): String {
        return "Hello Kotlin"
    }
}
