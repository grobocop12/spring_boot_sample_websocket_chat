package com.grobocop.chat.security

import com.grobocop.chat.security.data.Admin
import com.grobocop.chat.security.data.Member
import com.grobocop.chat.security.data.User
import com.grobocop.chat.security.data.dto.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService : UserDetailsService {

    @Autowired
    private lateinit var repository: UserRepository

    val encoder = BCryptPasswordEncoder(11)

    override fun loadUserByUsername(username: String): User = repository.findOneByName(username)
            ?: throw RuntimeException("User with name: $username does not exist")

    fun saveMember(user: UserDTO): User {
        val member = Member()
        member.email = user.email
        member.name = user.name
        member.pwd = encoder.encode(user.password)
        member.roles = "MEMBER"
        return repository.save(member)
    }

    fun saveAdmin(user: UserDTO): User {
        val admin = Admin()
        admin.email = user.email
        admin.name = user.name
        admin.pwd = encoder.encode(user.password)
        admin.roles = "ADMIN,MEMBER"
        return repository.save(admin)
    }

    fun updateUser(toSave: User): User? {
        val user = repository.findOneByEmail(toSave.email)
        user?.let {
            if (toSave.pwd.isNotEmpty()) {
                it.pwd = encoder.encode(toSave.pwd)
            }
            it.name = toSave.name
            it.accountNonExpired = toSave.accountNonExpired
            it.accountNonLocked = toSave.accountNonLocked
            it.credentialsNonExpired = toSave.credentialsNonExpired
            it.modified = Date()
            return repository.save(it)
        }
        return null
    }

    fun deleteUser(id: String) = repository.deleteById(id)

}