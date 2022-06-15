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
        val returnSimpleModel = db.save(body)

        simpleProducer.sendMessage(returnSimpleModel, returnSimpleModel.id!!)

        return returnSimpleModel
    }

    fun remove(id: String) {
        val idFromString = UUID.fromString(id)

        db.findById(idFromString).orElseThrow{ ItemNotFoundException(id) }.let {
            db.delete(it)
        }

        simpleProducer.sendMessage(null, idFromString)
    }

    fun update(body: SimpleModel): SimpleModel {
        val id = body.id ?: throw InvalidUpdateException()

        val updatedModel = db.findById(id).orElseThrow{ ItemNotFoundException(id.toString()) }.let {
             db.save(body)
        }

        simpleProducer.sendMessage(updatedModel, updatedModel.id!!)

        return updatedModel
    }

    fun getAll(): List<SimpleModel> {
        return db.findAll().map { it }
    }
}
