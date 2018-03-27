package com.ld.crawler

import com.ld.crawler.utils.SpringBeanUtil
import com.spring4all.mongodb.EnableMongoPlus
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [
    DataSourceAutoConfiguration::class
])
@EnableMongoPlus
@EnableScheduling
@Import(SpringBeanUtil::class)
class CrawlerApplication

    fun main(args: Array<String>) {
        runApplication<CrawlerApplication>(*args)
    }