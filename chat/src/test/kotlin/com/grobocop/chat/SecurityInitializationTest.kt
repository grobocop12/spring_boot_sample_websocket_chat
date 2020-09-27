package com.grobocop.chat

import com.grobocop.chat.security.UserService
import com.grobocop.chat.security.data.Admin
import com.grobocop.chat.security.data.dto.UserDTO
import org.junit.Assert
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(SpringRunner::class)
@TestMethodOrder(OrderAnnotation::class)
class SecurityInitializationTest {

    @Autowired
    private lateinit var service: UserService

    private val password = "12345"
    private val adminEmail = "admin@email.com"
    private val adminName = "admin"

    private val userPassword = "12345"
    private val userEmail = "userName@email.com"
    private val userName = "userName"


    @Test
    @Order(1)
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

    @Test
    @Order(2)
    fun createAndBanUser() {
        try {
            service.lockUser(userEmail)
            val user = service.loadUserByUsername(userName)
            if (!user.enabled) {
                println("User already banned")
            } else {
                Assert.fail("User not banned")
            }
        } catch (e: RuntimeException) {
            val toSave = UserDTO(
                    userEmail,
                    userPassword,
                    userName
            )
            val saved = service.saveMember(toSave)
            service.lockUser(saved.email)
            println("Created user account")
        }
    }

    @Test
    @Order(3)
    fun getAllUsers() {
        val users = service.getUsers()
        assertTrue(users.isNotEmpty())
    }
}