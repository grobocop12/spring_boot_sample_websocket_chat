package com.grobocop.chat.security.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.sun.istack.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
open class User(
        @Id
        @GeneratedValue(generator = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        var id: String = "",

        @Email
        @NotNull
        @Column(unique = true, nullable = false)
        var email: String = "",

        @NotBlank
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        var pwd: String = "",

        @NotBlank
        @Column(unique = true, nullable = false)
        var name: String = "",

        var roles: String = "",
        var enabled: Boolean = true,
        var accountNonExpired: Boolean = true,
        var accountNonLocked: Boolean = true,
        var credentialsNonExpired: Boolean = true,

        @CreationTimestamp
        open var created: Date = Date(),

        @UpdateTimestamp
        var modified: Date = Date()
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        roles.split(",")
                .forEach {
                    authorities.add(
                            SimpleGrantedAuthority(it.trim())
                    )
                }
        return authorities
    }

    override fun isEnabled(): Boolean = enabled

    override fun getUsername(): String = name

    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired

    override fun getPassword(): String = pwd

    override fun isAccountNonExpired(): Boolean = accountNonExpired

    override fun isAccountNonLocked(): Boolean = accountNonLocked

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