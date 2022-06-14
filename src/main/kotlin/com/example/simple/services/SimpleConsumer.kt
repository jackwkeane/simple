package com.example.simple.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class SimpleConsumer {
    private val logger: Logger = LoggerFactory.getLogger(SimpleProducer::class.java)

    @KafkaListener(topics = ["users"], groupId = "group_id")
    @Throws(IOException::class)

    fun consume(message: String?) {
        logger.info(String.format("#### -> Consumed message -> %s", message))
    }
}