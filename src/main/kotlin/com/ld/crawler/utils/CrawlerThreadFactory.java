package com.ld.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

/**
 * @Description 线程工厂
 * @Author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/27 13:25
 */
@Slf4j
public class CrawlerThreadFactory implements ThreadFactory{
    /**
     * 计数器
     */
    private int counter;
    /**
     * 线程名称
     */
    private String threadName;

    public CrawlerThreadFactory(String threadName){
        log.info(threadName);
        this.threadName = threadName;
    }
    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(r,threadName+"-"+counter);
        counter++;
        return thread;
    }

}
