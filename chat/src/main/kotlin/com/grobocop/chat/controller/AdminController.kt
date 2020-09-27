package com.grobocop.chat.controller

import com.grobocop.chat.security.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("")
    fun adminConsole(model: Model): String {
        val users = userService.getUsers()
        model.addAttribute("listOfUsers", users)
        return "adminConsole"
    }

}