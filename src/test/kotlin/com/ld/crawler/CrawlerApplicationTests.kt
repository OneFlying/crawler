package com.ld.crawler

import com.ld.crawler.domain.User
import com.ld.crawler.domain.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@WebAppConfiguration
class CrawlerApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository

	@Test
	fun contextLoads() {
        userRepository.insert(User("paytham", "123456", "18755133595", Date(), Date()))
        println(userRepository.findByName("paytham"))
	}

}
