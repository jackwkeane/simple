package com.example.simple.repository

import com.example.simple.models.SimpleModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SimpleRepository : CrudRepository<SimpleModel, UUID> {}
