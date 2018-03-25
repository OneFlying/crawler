package com.ld.crawler

import com.ld.crawler.domain.PrjInfoRepository
import com.ld.crawler.dto.User
import com.ld.crawler.domain.UserRepository
import com.ld.crawler.dto.PrjInfo
import com.ld.crawler.spider.CstoUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class CrawlerApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var prjInfoRepository : PrjInfoRepository

	@Test
	fun contextLoads() {
        userRepository.insert(User("paytham", "123456", "18755133595", Date(), Date()))
        println(userRepository.findByName("paytham"))
	}

    @Test
    fun crawlerCSTO(){
        var list = CstoUtils.crawPrjinfo();
        if(list!=null){
            prjInfoRepository.insert(list)

        }
    }

}
