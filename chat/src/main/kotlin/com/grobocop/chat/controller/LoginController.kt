package com.grobocop.chat.controller

import com.grobocop.chat.security.UserService
import com.grobocop.chat.security.data.dto.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class LoginController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/login")
    fun login(): String = "login"


    @GetMapping("/register")
    fun register(model: Model): String {
        model.addAttribute(
                "userDTO",
                UserDTO("",
                        "",
                        "")
        )
        return "register"
    }

    @PostMapping("/register")
    fun postUser(@Valid @ModelAttribute userDTO: UserDTO,
                 model: Model): String {
        try {
            service.saveMember(userDTO)
        } catch(e: DataIntegrityViolationException){
            model.addAttribute("error","Email or username already in use.")
            return "register"
        }
        model.addAttribute("successfulRegister", true)
        return "redirect:/login"
    }
}