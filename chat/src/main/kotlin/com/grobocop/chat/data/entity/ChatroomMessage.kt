package com.grobocop.chat.data.entity

import com.grobocop.chat.security.data.User
import net.bytebuddy.dynamic.loading.InjectionClassLoader
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
data class ChatroomMessage(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY)
        var author: User = User(),
        @ManyToOne(fetch = FetchType.LAZY)
        var chatroom: Chatroom,
        var text: String = "",
        @CreationTimestamp
        var created: Date = Date(),
        @UpdateTimestamp
        var modified: Date = Date()
)