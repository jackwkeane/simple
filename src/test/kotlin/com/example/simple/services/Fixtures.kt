package com.example.simple.services

import com.example.simple.models.SimpleModel
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

object Fixtures {
    /*
        function w/ default params – defaults by creating valid version of getting SimpleModel object
     */
    fun getSimpleModel(id: UUID? = UUID.randomUUID(), text: String = "Hello from my test!") = SimpleModel(id, text)
}