package com.ld.crawler.services

import com.ld.crawler.domain.User

/**
 * 用户
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 16:28
 */
interface IUserService {
    fun findByName(name: String): User
    fun insertUser(user: User): Int
}