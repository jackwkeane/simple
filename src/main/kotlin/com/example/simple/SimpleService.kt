package com.example.simple

import org.springframework.stereotype.Service
import java.util.*

@Service
class SimpleService (val db: SimpleRepository ){
    fun greetingService(name: String): String {
        return "Hello, $name"
    }

    fun add(body: SimpleModel): SimpleModel {
        return db.save(body)
    }


    fun remove(id: String) {
        /*
            Getting obj id by .findById – returns optional.
            Chaining thru > passing logic from finding Id, dealing with it not being found, removing if found.
         */
        db.findById(UUID.fromString(id)).orElseThrow{ItemNotFoundException(id)}.let {
            db.delete(it)
        }
    }

    fun getAll(): List<SimpleModel> {
        return db.findAll().map { it }
    }
}
