package com.example.simple.services

import com.example.simple.InvalidUpdateException
import com.example.simple.ItemNotFoundException
import com.example.simple.models.SimpleModel
import com.example.simple.repository.SimpleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SimpleService (val db: SimpleRepository){
    fun add(body: SimpleModel): SimpleModel {
        return db.save(body)
    }

    fun remove(id: String) {
        /*
            Getting obj id by .findById – returns optional.
            Chaining thru > passing logic from finding Id, dealing with it not being found, removing if found.
         */
        db.findById(UUID.fromString(id)).orElseThrow{ ItemNotFoundException(id) }.let {
            db.delete(it)
        }
    }

    fun update(body: SimpleModel) {
        val id = body.id ?: throw InvalidUpdateException()

        return db.findById(id).orElseThrow{ ItemNotFoundException(id.toString()) }.let {
            db.save(body)
        }
    }

    fun getAll(): List<SimpleModel> {
        return db.findAll().map { it }
    }
}
