package com.grobocop.chat.security.data.dto

import java.util.*

data class UserDetailsDTO(
        val id: String,
        var email: String,
        var name: String,
        var roles: String,
        var accountNonExpired: Boolean,
        var accountNonLocket: Boolean,
        var credentialNonExpired: Boolean,
        var created: Date,
        var modified: Date
)