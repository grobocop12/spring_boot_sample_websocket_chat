package com.grobocop.chat.controller

import com.grobocop.chat.data.entity.Chatroom
import com.grobocop.chat.data.entity.dto.ChatroomDTO

interface ChatroomService {
    fun getListOfChatrooms(): Iterable<ChatroomDTO>
    fun createChatroom(chatroomDTO: ChatroomDTO) : ChatroomDTO
    fun getChatroomByUrl(url: String ): ChatroomDTO?
}