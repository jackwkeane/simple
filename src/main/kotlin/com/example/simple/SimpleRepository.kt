package com.example.simple

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SimpleRepository : CrudRepository<String, String> {

}
