package com.grobocop.chat.controller

import com.grobocop.chat.data.entity.dto.ChatroomMessageDTO
import com.grobocop.chat.security.data.User

interface MessageService {
    fun saveMessage(message: ChatroomMessageDTO, chatroomUrl: String, author: User) : ChatroomMessageDTO

}