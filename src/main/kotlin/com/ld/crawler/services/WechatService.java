package com.ld.crawler.services;

/**
 * 微信服务类
 */
public interface WechatService {

    /**
     * @param content 推送内容
     */
    void pushTextMessageToAllUser(String content);
}
