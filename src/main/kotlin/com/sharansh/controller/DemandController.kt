package com.sharansh.controller

import com.sharansh.domain.Demand
import com.sharansh.service.DemandRepositoryService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject

@Controller
class DemandController {

    @Inject
    lateinit var demandRepositoryService: DemandRepositoryService

    @Get(value = "/demands")
    @Produces(MediaType.APPLICATION_JSON)
    fun getDemands(): Map<String, List<Demand>> {
        println("Request received for all demands")
        return demandRepositoryService.findAll()
    }

    @Get(value = "/demand/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getDemandsByCategory(@PathVariable(name = "category") category: String): MutableIterable<Demand> {
        println("Request received for demands with category $category")
        return demandRepositoryService.findByCategoryAndStatus(category)
    }

    @Get(value = "/demand/group/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getDemandsUsingQuery(@PathVariable(name = "category") category: String): MutableIterable<Demand> {
        println("Request received for demands with category $category")
        return demandRepositoryService.getDemandsUsingQuery(category)
    }
}