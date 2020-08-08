package com.grobocop.chat.security.data

import java.util.*
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(value = "ADMIN")
class Admin(
        id: String,
        email: String,
        pwd: String,
        name: String,
        roles: String,
        enabled: Boolean,
        accountNonExpired: Boolean,
        accountNonLocked: Boolean,
        credentialsNonExpired: Boolean,
        created: Date,
        modified: Date
) : User(id,
        email,
        pwd,
        name,
        roles,
        enabled,
        accountNonExpired,
        accountNonLocked,
        credentialsNonExpired,
        created,
        modified) {
    constructor() : this("",
            "", "",
            "",
            "",
            true,
            true,
            true,
            true,
            Date(),
            Date())
}