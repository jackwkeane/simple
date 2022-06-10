package com.example.simple

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
public class SimpleController(val service: SimpleService) {
    @GetMapping("/test")
    fun test(): String {
        return "This is a test."
    }

    @GetMapping("/pathvariable/{variable}")
    fun pathVariable(@PathVariable variable: String): String {
        return "Your path variable: $variable"
    }

    @GetMapping("/sayhello/{name}")
    fun greeting(@PathVariable name: String): String = service.greetingService(name)

}