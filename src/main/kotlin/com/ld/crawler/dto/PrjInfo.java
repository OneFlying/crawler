package com.ld.crawler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description 项目信息表
 * @Author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/25 12:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "prjInfo")
public class PrjInfo {
    private String title;//标题
    private String fabutime;//发布时间
    private String money;//发布金额
    private String content;//简介
    private String url;//项目地址
}
