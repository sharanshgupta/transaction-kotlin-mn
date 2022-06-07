package com.sharansh.repository

import com.sharansh.domain.Demand
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface DemandRepository: CrudRepository<Demand, Long> {

    override fun findAll(): MutableIterable<Demand> {
        TODO("Not yet implemented")
    }

    fun findByCategoryAndStatus(category: String, status: String): MutableIterable<Demand> {
        TODO(reason = "Custom")
    }

//    @Query("SELECT d.id, d.tcin, d.store, d.category, d.status FROM demand d")
    @Query(value = "SELECT * FROM demand where category = :category AND status = :status ", nativeQuery = true)
    fun getDemandsGroupByTcin(category: String, status: String): MutableIterable<Demand> {
        TODO(reason = "Custom")
    }
}