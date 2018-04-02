package com.ld.crawler.services.impl;

import com.Id.crawler.dao.ProfileMapper;
import com.Id.crawler.model.Profile;
import com.Id.crawler.model.ProfileExample;
import com.ld.crawler.services.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Resource
    private ProfileMapper profileMapper;

    @Override
    public void insertProfile(Profile profile) {
        profileMapper.insertSelective(profile);
    }

    @Override
    public List<String> getAllOpenId() {
        ProfileExample profileExample = new ProfileExample();

        List<Profile> profileList = profileMapper.selectByExample(profileExample);
        if (!CollectionUtils.isEmpty(profileList)) {
            return profileList.stream().map(Profile::getOpenId).collect(toList());
        }
        return null;
    }

    @Override
    public void deleteProfileByOpenId(String openId) {
        ProfileExample profileExample = new ProfileExample();
        profileExample.createCriteria().andOpenIdEqualTo(openId);

        profileMapper.deleteByExample(profileExample);
    }
}
