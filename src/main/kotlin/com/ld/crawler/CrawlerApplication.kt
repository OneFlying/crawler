package com.ld.crawler

import com.spring4all.mongodb.EnableMongoPlus
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(exclude = [
    DataSourceAutoConfiguration::class
])
@MapperScan("com.ld.crawler")
@EnableMongoPlus
@EnableTransactionManagement
class CrawlerApplication

    fun main(args: Array<String>) {
        runApplication<CrawlerApplication>(*args)
    }