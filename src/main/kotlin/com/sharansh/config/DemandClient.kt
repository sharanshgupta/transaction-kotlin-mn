package com.sharansh.config

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaPartitionKey
import io.micronaut.configuration.kafka.annotation.Topic


@KafkaClient(transactionalId = "transaction-demand")
interface DemandClient {

    @Topic("demand_topic")
    fun sendDemand(@KafkaPartitionKey key: String?, message: String?)
}