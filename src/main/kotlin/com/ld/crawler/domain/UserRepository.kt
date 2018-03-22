package com.ld.crawler.domain

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * 用户
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 20:41
 */
interface UserRepository: MongoRepository<User, Long> {
    fun findByName(name: String): User
}