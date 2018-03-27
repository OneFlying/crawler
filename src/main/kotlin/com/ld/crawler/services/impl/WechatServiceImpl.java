package com.ld.crawler.services.impl;

import com.Id.crawler.constant.WechatConstant;
import com.ld.crawler.services.ProfileService;
import com.ld.crawler.services.WechatService;
import com.ld.crawler.utils.JSONUtil;
import com.ld.crawler.utils.ThreadServiceUtil;
import com.ld.crawler.utils.WechatAPIHelpher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WechatServiceImpl implements WechatService {

    @Resource
    private ProfileService profileService;

    @Override
    public void pushTextMessageToAllUser(String content) {
        Map<String, Object> msgMap = new HashMap<>();
        List<String> openIds = profileService.getAllOpenId();
        if (!CollectionUtils.isEmpty(openIds)) {
            Map<String, Object> contentMap = new HashMap<>();
            contentMap.put("content", content);

            msgMap.put("touser", openIds);
            msgMap.put("msgtype", WechatConstant.MSG_TYPE_TEXT);
            msgMap.put("text", contentMap);

            // 异步推送消息给各个用户
            ThreadServiceUtil.getInstance()
                    .getAsynServiceThread()
                    .execute(() -> {
                WechatAPIHelpher.sendMessageToAllByOpenId(JSONUtil.mapToJsonStr(msgMap));
            });
        }

    }
}
