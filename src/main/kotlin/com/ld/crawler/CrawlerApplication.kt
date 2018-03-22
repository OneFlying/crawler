package com.ld.crawler

import com.spring4all.mongodb.EnableMongoPlus
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
    DataSourceAutoConfiguration::class
])
@EnableMongoPlus
class CrawlerApplication

    fun main(args: Array<String>) {
        runApplication<CrawlerApplication>(*args)
    }