package com.grobocop.chat.controller

import com.grobocop.chat.security.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/")
    fun home(model: Model, request: HttpServletRequest): String {
        val authorities = SecurityContextHolder.getContext().authentication.authorities
        if (SimpleGrantedAuthority("MEMBER") in authorities) {
            model.addAttribute("isAuthenticated", true)
        }
        if (SimpleGrantedAuthority("ADMIN") in authorities) {
            model.addAttribute("isAdmin", true)
        }
        return "index"
    }
}