package com.ld.crawler.domain

import org.apache.ibatis.annotations.*

/**
 * 用户
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 14:54
 */

@Mapper
interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    fun findByName(@Param("name") name: String): User

    @InsertProvider(type = SqlFactory::class, method = "insertUser")
    fun insertUser(@Param("bean") user: User): Int
}