package com.grobocop.chat.security.data.dto

import com.grobocop.chat.security.data.User
import java.util.*

data class UserDetailsDTO(
        val id: String,
        var email: String,
        var name: String,
        var roles: String,
        var accountNonExpired: Boolean,
        var accountNonLocked: Boolean,
        var credentialNonExpired: Boolean,
        var created: Date,
        var modified: Date
) {
    constructor(user: User) : this(
            user.id,
            user.email,
            user.name,
            user.roles,
            user.accountNonExpired,
            user.accountNonLocked,
            user.credentialsNonExpired,
            user.created,
            user.modified
    )
}