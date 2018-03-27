package com.ld.crawler.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程服务类
 */
public class ThreadServiceUtil {

    // 异步服务线程
    private volatile ExecutorService asynService;

    // 防止被new
    private ThreadServiceUtil(){};

    private static class SingletonHolder {
        public static ThreadServiceUtil INSTANCE = new ThreadServiceUtil();
    }

    public static ThreadServiceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 获取异步服务线程
    public ExecutorService getAsynServiceThread() {
        if (asynService == null) {
            synchronized (ThreadServiceUtil.class) {
                if (asynService == null) {
                    asynService = Executors.newCachedThreadPool();
                }
            }
        }
        return asynService;
    }
}
