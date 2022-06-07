package com.sharansh.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "demand")
class Demand {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "tcin")
    lateinit var tcin: String

    @Column(name = "store")
    lateinit var store: String

    @Column(name = "category")
    lateinit var category: String

    @Column(name = "status")
    lateinit var status: String

}