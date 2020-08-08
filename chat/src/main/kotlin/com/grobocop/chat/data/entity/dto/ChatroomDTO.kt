package com.grobocop.chat.data.entity.dto

import com.grobocop.chat.data.entity.Chatroom
import com.grobocop.chat.data.entity.ChatroomMessage
import com.grobocop.chat.security.data.User

class ChatroomDTO(
        var name: String = "",
        var url: String = "",
        var owner: User? = null,
        var mesages: List<ChatroomMessageDTO> = emptyList()
) {
    constructor(chatroom: Chatroom) : this(chatroom.name, chatroom.url, chatroom.owner)
}