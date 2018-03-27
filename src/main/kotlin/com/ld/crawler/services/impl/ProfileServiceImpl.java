package com.ld.crawler.services.impl;

import com.ld.crawler.domain.pojo.Profile;
import com.ld.crawler.services.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public void insertProfile(Profile profile) {

    }

    @Override
    public List<String> getAllOpenId() {
        return null;
    }

    @Override
    public void deleteProfileByOpenId(String openId) {

    }
}
