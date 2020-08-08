package com.grobocop.chat.data.service

import com.grobocop.chat.controller.ChatroomService
import com.grobocop.chat.data.entity.Chatroom
import com.grobocop.chat.data.entity.dto.ChatroomDTO
import com.grobocop.chat.data.entity.dto.ChatroomMessageDTO
import com.grobocop.chat.data.repository.ChatroomRepository
import com.grobocop.chat.utils.UrlGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChatroomServiceImpl : ChatroomService {

    @Autowired
    lateinit var urlGenerator: UrlGenerator

    @Autowired
    lateinit var chatroomRepository: ChatroomRepository

    override fun getListOfChatrooms(): Iterable<ChatroomDTO> = chatroomRepository.findAll().map { it -> ChatroomDTO(it) }

    override fun createChatroom(chatroomDTO: ChatroomDTO): ChatroomDTO {
        val chatroom = Chatroom(
                name = chatroomDTO.name,
                url = urlGenerator.generateUrl()
        )
        return ChatroomDTO(chatroomRepository.save(chatroom))
    }

    override fun getChatroomByUrl(url: String ): ChatroomDTO? {
        chatroomRepository.findByUrl(url)?.let { it ->
            return ChatroomDTO(
                    name= it.name,
                    url = it.url,
                    owner = it.owner,
                    mesages = it.messages.map {message ->
                        ChatroomMessageDTO(
                                message.author.name,
                                message.text,
                                message.created
                        )
                    }
            )
        }
        return null
    }
}