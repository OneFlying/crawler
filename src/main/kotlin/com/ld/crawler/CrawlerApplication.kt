package com.ld.crawler

import com.spring4all.mongodb.EnableMongoPlus
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [
    DataSourceAutoConfiguration::class
])
@EnableMongoPlus
@EnableScheduling
class CrawlerApplication

    fun main(args: Array<String>) {
        runApplication<CrawlerApplication>(*args)
    }