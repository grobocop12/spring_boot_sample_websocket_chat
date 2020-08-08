package com.grobocop.chat.utils

import org.springframework.stereotype.Component
import java.util.*

@Component
class UrlGenerator {

    fun generateUrl() : String {
        return UUID.randomUUID().toString()
    }
}