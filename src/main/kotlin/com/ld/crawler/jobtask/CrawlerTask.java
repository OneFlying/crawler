package com.ld.crawler.jobtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description 抓取任务
 * @Author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/25 18:49
 */
@Slf4j
@EnableScheduling
public class CrawlerTask {
    /**
     * Sxsoft 抓取任务
     */
    @Scheduled(cron = "0/1 * * * * ? ")
    public void crawlerSxsoftTask(){
        log.info("抓取数据");
    }

}
