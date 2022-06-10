package com.example.simple

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
public class SimpleController {
    @GetMapping("/helloworld")
    fun helloWorld(): String {
        return "Changed Text"
    }
}