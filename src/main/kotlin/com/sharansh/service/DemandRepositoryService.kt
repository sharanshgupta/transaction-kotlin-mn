package com.sharansh.service

import com.sharansh.domain.Demand
import com.sharansh.repository.DemandRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DemandRepositoryService {
    @Inject
    lateinit var demandRepository: DemandRepository

//    fun findAll(): MutableIterable<Demand> {
//        return demandRepository.findAll()
//    }

    fun findAll(): Map<String, List<Demand>> {
        val demands = demandRepository.findAll().sortedBy { demand -> demand.tcin }
        return demands.groupBy { demand -> demand.tcin }
    }

    fun findByCategoryAndStatus(category: String): MutableIterable<Demand> {
        return demandRepository.findByCategoryAndStatus(category, "CREATED")
    }

    fun getDemandsUsingQuery(category: String): MutableIterable<Demand> {
        return demandRepository.getDemandsUsingQuery(category, "CREATED")
    }
}