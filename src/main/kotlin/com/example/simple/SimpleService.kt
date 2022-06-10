package com.example.simple

import org.springframework.stereotype.Service

@Service
class SimpleService (val db: SimpleRepository ){
    fun greetingService(name: String): String {
        return "Hello, $name"
    }
}
