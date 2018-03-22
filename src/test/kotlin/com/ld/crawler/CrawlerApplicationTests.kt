package com.ld.crawler

import com.ld.crawler.domain.User
import com.ld.crawler.services.IUserService
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
    lateinit var userService: IUserService

	@Test
	fun contextLoads() {
        userService.insertUser(User("paytham", "123456", "18755133595", Date(), Date()))
        println(userService.findByName("paytham"))
	}

}
