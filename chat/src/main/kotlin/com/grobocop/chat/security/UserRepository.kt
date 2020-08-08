package com.grobocop.chat.security

import com.grobocop.chat.security.data.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String>{
    fun findOneByEmail(email: String): User?
    fun findOneByName(username: String): User?
}