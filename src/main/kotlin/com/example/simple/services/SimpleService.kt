package com.example.simple.services

import com.example.simple.InvalidUpdateException
import com.example.simple.ItemNotFoundException
import com.example.simple.models.SimpleModel
import com.example.simple.repository.SimpleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SimpleService (
    val db: SimpleRepository,
    val simpleProducer: SimpleProducer
){
    fun add(body: SimpleModel): SimpleModel {
        val ret = db.save(body)

        simpleProducer.sendMessage(ret, ret.id!!)

        return ret
    }

    fun remove(id: String) {
        /*
            Getting obj id by .findById – returns optional.
            Chaining thru > passing logic from finding Id, dealing with it not being found, removing if found.
         */
        val idToString = UUID.fromString(id)

        db.findById(idToString).orElseThrow{ ItemNotFoundException(id) }.let {
            db.delete(it)
        }

        simpleProducer.sendMessage(null, idToString)
    }

    fun update(body: SimpleModel): SimpleModel {
        val id = body.id ?: throw InvalidUpdateException()

        val ret = db.findById(id).orElseThrow{ ItemNotFoundException(id.toString()) }.let {
            db.save(body)
        }

        simpleProducer.sendMessage(ret, ret.id!!)

        return ret
    }

    fun getAll(): List<SimpleModel> {
        return db.findAll().map { it }
    }


}
