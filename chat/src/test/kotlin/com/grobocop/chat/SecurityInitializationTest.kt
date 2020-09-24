package com.grobocop.chat

import com.grobocop.chat.security.UserService
import com.grobocop.chat.security.data.Admin
import com.grobocop.chat.security.data.dto.UserDTO
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(SpringRunner::class)
class SecurityInitializationTest {

    @Autowired
    private lateinit var service: UserService

    private val password = "12345"
    private val adminEmail = "admin@email.com"
    private val adminName = "admin"


    @Test
    fun initAdmin() {
        try {
            val admin = service.loadUserByUsername(adminName)
            if (admin is Admin) {
                println("Admin already exists")
            } else {
                Assert.fail("This is not an Admin account")
            }
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                    adminEmail,
                    password,
                    adminName
            )
            val saved = service.saveAdmin(toSave)
            println("Created admin account")
        }
    }
}