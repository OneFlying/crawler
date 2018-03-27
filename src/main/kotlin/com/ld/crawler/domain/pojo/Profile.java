package com.ld.crawler.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profile")
public class Profile {

    private Long id;

    private String openId;

    private String profileName;

    private String password;

    private String email;

    private String address;

    private String phone;
}
