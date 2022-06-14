package com.example.simple.templates

import com.example.simple.models.SimpleModel
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.stereotype.Component

@Component
class KafkaTemplates {
    @Bean
    private fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(kafkaProducerFactory())
    }
    @Bean
    private fun kafkaProducerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory<String, String>(mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
            )
        )
    }
}