package com.ld.crawler.domain

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository


@Repository("userMapper")
class UserMapperImpl : UserMapper{
    override fun findByName(name: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun insertUser(user: User): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}