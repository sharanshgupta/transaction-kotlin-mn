package com.sharansh.config

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.KafkaException
import org.apache.kafka.common.protocol.types.Field.Str
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Singleton
class Producer {
    lateinit var kafkaProducer: KafkaProducer<String, String>

    //    @Singleton
    @PostConstruct
    fun postConstruct() {
        val props = Properties()
        props["bootstrap.servers"] = "localhost:9092"
//        props["acks"] = "all"
//        props["retries"] = 1
//        props["batch.size"] = 16384
//        props["linger.ms"] = 1
//        props["buffer.memory"] = 33554432
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
//        props["transaction.state.log.min.isr"] = 1
//        props["transaction.state.log.replication.factor"] = 2
        props["transactional.id"] = "my-transactional-id"
//        props["enable.idempotence"] = "true"

        kafkaProducer = KafkaProducer<String, String>(props)
    }

    @PreDestroy
    fun preDestroy() {
        kafkaProducer.close()
    }

    fun sendMessage(message: String) {
        try {
            var flag = false
            kafkaProducer.initTransactions()
            kafkaProducer.beginTransaction()
            kafkaProducer.send(ProducerRecord("demand_topic", "key-1", message))
            if(flag) {
                throw KafkaException("Kafka Exception")
            }
            kafkaProducer.commitTransaction()
        } catch (e: KafkaException) {
            // For all other exceptions, just abort the transaction and try again.
//            kafkaProducer.abortTransaction()
            println(e.message)
        }
    }
}