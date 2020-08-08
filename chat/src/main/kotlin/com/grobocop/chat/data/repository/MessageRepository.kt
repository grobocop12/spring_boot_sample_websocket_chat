package com.grobocop.chat.data.repository

import com.grobocop.chat.data.entity.ChatroomMessage
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<ChatroomMessage, Long> {
}