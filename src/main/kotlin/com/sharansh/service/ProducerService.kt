package com.sharansh.service

import com.sharansh.config.DemandClient
import com.sharansh.config.Producer
import jakarta.inject.Inject
import jakarta.inject.Singleton


@Singleton
class ProducerService {
    @Inject
    lateinit var demandClient: DemandClient

    @Inject
    lateinit var produce: Producer

    fun sendMessage(message: String) {
        produce.sendMessage(message)
    }
}