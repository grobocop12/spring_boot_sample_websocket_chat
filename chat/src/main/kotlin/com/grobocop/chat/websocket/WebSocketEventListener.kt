package com.grobocop.chat.websocket

import com.grobocop.chat.security.data.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener {

    val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    @Autowired
    lateinit var messagingTemplate: SimpMessageSendingOperations

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent) {
        logger.info("New connection")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        headerAccessor.messageHeaders["simpUser"]?.let {
            if (it is UsernamePasswordAuthenticationToken) {
                val user = it.principal
                if (user is User) {
                    logger.info("${user.username} disconnects!")
                    println("${user.username} disconnects!")
                }
            }
        }
    }
}