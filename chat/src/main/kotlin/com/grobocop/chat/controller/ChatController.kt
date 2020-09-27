package com.grobocop.chat.controller

import com.grobocop.chat.data.entity.dto.ChatroomDTO
import com.grobocop.chat.security.data.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid


@Controller
@RequestMapping("/chat")
class ChatController {
    @Autowired
    lateinit var chatroomService: ChatroomService

    @GetMapping("")
    fun chatroom(request: HttpServletRequest,
                 model: Model): String {
        val user = (request.userPrincipal as UsernamePasswordAuthenticationToken).principal as User
        val contains = user.authorities.contains(SimpleGrantedAuthority("ADMIN"))
        model.addAttribute("isAdmin", contains)
        val chatrooms = chatroomService.getListOfChatrooms()
        model.addAttribute("listOfRooms", chatrooms)
        return "listOfRooms"
    }

    @GetMapping("/addChatroom")
    fun addChatroom(model: Model): String {
        model.addAttribute("chatroom", ChatroomDTO())
        return "addChatroom"
    }

    @PostMapping("/addChatroom")
    fun addChatroom(request: HttpServletRequest,
                    @Valid @ModelAttribute chatroomDTO: ChatroomDTO,
                    model: Model): String {
        val user = (request.userPrincipal as UsernamePasswordAuthenticationToken).principal as User
        chatroomDTO.owner = user
        chatroomService.createChatroom(chatroomDTO)
        return "redirect:/chat"
    }

    @GetMapping("/room/{roomUrl}")
    fun room(@PathVariable roomUrl: String,
             model: Model): String {
        chatroomService.getChatroomByUrl(roomUrl)?.let {
            model.addAttribute("roomName", it.name)
            model.addAttribute("messages", it.mesages)
            model.addAttribute("roomUrl", roomUrl)
            return "room"
        }
        return "redirect:/chat"
    }
}