package com.grobocop.chat.data.service

import com.grobocop.chat.controller.MessageService
import com.grobocop.chat.data.entity.ChatroomMessage
import com.grobocop.chat.data.entity.dto.ChatroomMessageDTO
import com.grobocop.chat.data.repository.ChatroomRepository
import com.grobocop.chat.data.repository.MessageRepository
import com.grobocop.chat.security.data.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageServiceImpl : MessageService {
    @Autowired
    lateinit var repository: MessageRepository

    @Autowired
    lateinit var chatroomRepository: ChatroomRepository

    override fun saveMessage(messageDTO: ChatroomMessageDTO, chatroomUrl: String, author: User): ChatroomMessageDTO {
        chatroomRepository.findByUrl(chatroomUrl)?.let { chatroom ->
            val toSave = ChatroomMessage(
                    0,
                    author,
                    chatroom,
                    messageDTO.text,
                    Date(),
                    Date()
            )
            val message = repository.save(toSave)
            return ChatroomMessageDTO(
                    message.author.name,
                    message.text,
                    message.created
            )
        }
        throw RuntimeException()
    }
}