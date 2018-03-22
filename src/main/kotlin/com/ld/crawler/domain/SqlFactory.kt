package com.ld.crawler.domain

import org.apache.ibatis.jdbc.SQL

/**
 * SqlFactory
 * @author Paytham (<a href="mailto:caiyuhao2015@gmail.com">caiyuhao2015@gmail.com</a>)
 * 2018/03/22 15:01
 */
class SqlFactory

    fun insertUser(para: Map<String, Any?>): String {
        println(para["bean"])
        val sql = SQL()
        sql.INSERT_INTO("user")
        for ((k, v) in para) {
            //
        }
        return sql.toString()
    }
