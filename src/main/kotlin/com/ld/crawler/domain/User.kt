package com.ld.crawler.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.mongodb.core.mapping.Document
import reactor.util.annotation.Nullable
import java.util.*

/**
 * 用户
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
data class User constructor(
        private var name: String,
        private var pwd: String,
        private var mobile: String,
        private var createdAt: Date,
        @Nullable private var modifiedAt: Date
)