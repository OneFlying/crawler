package com.ld.crawler.services.impl

import com.ld.crawler.domain.User
import com.ld.crawler.domain.UserMapper
import com.ld.crawler.services.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 16:29
 */
@Service
class UserServiceImpl: IUserService {

    @Autowired
    lateinit var userMapper: UserMapper

    override fun findByName(name: String): User {
        return userMapper.findByName(name)
    }

    override fun insertUser(user: User): Int {
        return userMapper.insertUser(user)
    }
}
