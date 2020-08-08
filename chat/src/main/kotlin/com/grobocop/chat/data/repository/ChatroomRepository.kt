package com.grobocop.chat.data.repository

import com.grobocop.chat.data.entity.Chatroom
import org.springframework.data.repository.CrudRepository

interface ChatroomRepository: CrudRepository<Chatroom, Long> {
    fun findByUrl(url: String) : Chatroom?
}