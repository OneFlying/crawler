package com.ld.crawler.services;

import com.ld.crawler.domain.pojo.Profile;

import java.util.List;

/**
 * 用户服务类
 */
public interface ProfileService {

    void insertProfile(Profile profile);

    /**
     * 获取所有用户openId
     * @return
     */
    List<String> getAllOpenId();

    void deleteProfileByOpenId(String openId);
}
