package com.example.simple.models

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="SIMPLEMODELS")
data class SimpleModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null, val text: String
) {
    constructor() : this(UUID.randomUUID(), "") {

    }


}




