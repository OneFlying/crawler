package com.ld.crawler

import com.ld.crawler.domain.PrjInfoRepository
import com.ld.crawler.domain.pojo.User
import com.ld.crawler.domain.UserRepository
import com.ld.crawler.spider.CstoUtils
import com.ld.crawler.spider.SxsoftUtils
import lombok.extern.slf4j.Slf4j
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DuplicateKeyException
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
@Slf4j
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
            try{
                prjInfoRepository.insert(list);
            }catch (e: DuplicateKeyException){
                println(e.message)
            }

        }
    }

    @Test
    fun crawlerSXSOFT(){
        var list = SxsoftUtils.crawPrjinfo();
        if(list!=null){
            try{
                prjInfoRepository.insert(list);
            }catch (e: DuplicateKeyException){
                println(e.message)
//                log.in
            }


        }
    }

}
