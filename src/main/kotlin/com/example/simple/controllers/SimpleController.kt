package com.example.simple.controllers

import com.example.simple.models.SimpleModel
import com.example.simple.services.SimpleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
public class SimpleController(val service: SimpleService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    fun saveMessage(@RequestBody body: SimpleModel) = service.add(body)

    @GetMapping("/all")
    fun getAll(): List<SimpleModel> = service.getAll()

    @DeleteMapping("/delete/{id}")
    fun remove(@PathVariable id: String) = service.remove(id)

    @PostMapping("/update")
    fun updateMessage(@RequestBody body: SimpleModel) = service.update(body)
}
