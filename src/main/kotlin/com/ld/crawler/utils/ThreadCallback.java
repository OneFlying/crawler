package com.ld.crawler.utils;

import com.alibaba.fastjson.JSONObject;
import com.ld.crawler.domain.PrjInfoRepository;
import com.ld.crawler.domain.pojo.PrjInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * @Description 线程回调类
 * @Author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/27 15:45
 */
@Slf4j
public class ThreadCallback {

    /**
     * 处理一品威客线程回调数据
     * @param list
     */
    public void epwk(List<PrjInfo> list){
        log.info(JSONObject.toJSONString(list));
        try {
            PrjInfoRepository prjInfoRepository = (PrjInfoRepository) SpringBeanUtil.getBean("prjInfoRepository");
            prjInfoRepository.insert(list);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
