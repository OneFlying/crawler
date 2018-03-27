package com.ld.crawler

import com.ld.crawler.domain.PrjInfoRepository
import com.ld.crawler.domain.pojo.User
import com.ld.crawler.domain.UserRepository
import com.ld.crawler.spider.CstoUtils
import com.ld.crawler.spider.EpwkUtils
import com.ld.crawler.spider.SxsoftUtils
import com.ld.crawler.utils.SpringBeanUtil
import lombok.extern.slf4j.Slf4j
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Component
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@Slf4j
@RunWith(SpringRunner::class)
@SpringBootTest
@Component
@Import(SpringBeanUtil::class)
class CrawlerApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var prjInfoRepository : PrjInfoRepository
    @Value("\${env}")
    var epwk: String? = null

    @Test
    fun testValue() {
        println(epwk)
    }

	// @Test
	fun contextLoads() {
        userRepository.insert(User("paytham", "123456", "18755133595", Date(), Date()))
        println(userRepository.findByName("paytham"))
	}

    @Test
    fun crawlerCSTO(){
        val list = CstoUtils.crawPrjinfo()
        if(list!=null){
            try{
                prjInfoRepository.insert(list)
            }catch (e: DuplicateKeyException){
                println(e.message)
            }
        }
    }

    @Test
    fun crawlerSXSOFT(){
        val list = SxsoftUtils.crawPrjinfo()
        if(list!=null){
            try{
                prjInfoRepository.insert(list)
            }catch (e: DuplicateKeyException){
                println(e.message)
            }
        }
    }

    @Test
    fun crawlerEpwk(){
        val epwkUtils = EpwkUtils()
        epwkUtils.crawPrjinfo()
        /*if(list!=null){
            try{
                prjInfoRepository.insert(list)
            }catch (e: DuplicateKeyException){
                println(e.message)
            }
        }*/
        Thread.sleep(500000);
    }
}
