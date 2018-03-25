package com.ld.crawler.domain.pojo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 项目信息表
 * @author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/25 12:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prjInfo")
public class PrjInfo {
    /**
     * 标题
     */
    private String title;
    /**
     * 发布时间
     */
    private String fabutime;
    /**
     * 发布金额
     */
    private String money;
    /**
     * 简介
     */
    private String content;
    /**
     * 项目地址
     */
    private String url;
}
