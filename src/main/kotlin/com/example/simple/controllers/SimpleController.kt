package com.example.simple.controllers

import com.example.simple.models.SimpleModel
import com.example.simple.services.SimpleService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping("/add")
    fun saveMessage(@RequestBody body: SimpleModel) : SimpleModel = service.add(body)

    @GetMapping("/all")
    fun getAll(): List<SimpleModel> = service.getAll()

    @DeleteMapping("/delete/{id}")
    fun remove(@PathVariable id: String) = service.remove(id)

}
