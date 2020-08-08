package com.grobocop.chat.controller

import com.grobocop.chat.data.entity.dto.ChatroomMessageDTO
import com.grobocop.chat.security.data.User
import com.grobocop.chat.websocket.MessageFromServer
import com.grobocop.chat.websocket.MessageToServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import java.security.Principal
import java.util.*

@Controller
class MessageController {

    @Autowired
    private lateinit var messageService: MessageService

    @Autowired
    private lateinit var messagingTemplate: SimpMessageSendingOperations

    @MessageMapping("/secured/{roomUrl}")
    @SendTo("/chat/{roomUrl}")
    fun response(@Payload message: MessageToServer,
                 user: Principal,
                 @DestinationVariable roomUrl: String
    ): MessageFromServer {
        val userDetails = ((user as Authentication).principal as User)
        val username = userDetails.username
        val messageDTO = ChatroomMessageDTO(username, message.content, Date())
        messageService.saveMessage(messageDTO, roomUrl, userDetails)
        return MessageFromServer(message.content, username)
    }
}