package com.ld.crawler.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * 用户Entity
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
class User constructor(
        @Id private val id: Long
)