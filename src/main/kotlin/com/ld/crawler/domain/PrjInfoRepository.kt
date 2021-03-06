package com.ld.crawler.domain

import com.ld.crawler.domain.pojo.PrjInfo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * 项目信息数DAO
 * @author dzfking007 (<a href="mailto:dzfking007@163.com">dzfking007@163.com</a>)
 * 2018/3/25 12:33
 */
interface PrjInfoRepository : MongoRepository<PrjInfo, Long>