package com.grobocop.chat.data.entity

import com.grobocop.chat.security.data.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Chatroom(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @NotBlank
        var name: String = "",
        var url: String = "",
        @OneToMany(
                cascade = [(CascadeType.ALL)],
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "chatroom_id")
        var messages: List<ChatroomMessage> = emptyList(),
        @ManyToOne(
                cascade = [(CascadeType.DETACH)],
                fetch = FetchType.LAZY
        )
        @JoinColumn(name = "user_id")
        var owner: User? = null,
        @CreationTimestamp
        var created: Date = Date(),
        @UpdateTimestamp
        var modified: Date = Date()
)