package com.example.simple.controllers

import com.example.simple.models.SimpleModel
import com.example.simple.repository.SimpleRepository
import com.example.simple.services.SimpleService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@WebAppConfiguration
@RunWith(SpringRunner::class)
@WebMvcTest(SimpleController::class)
class SimpleControllerTest {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @MockBean
    private lateinit var simpleService: SimpleService

    @MockBean
    private lateinit var simpleRepository: SimpleRepository

    companion object {

        val id: UUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
        val text = "Unit test - text"
        val body = SimpleModel(id, text)
        val objectMapper = ObjectMapper()
    }

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `Successfully added JSON`() {
        val requestBuilder = MockMvcRequestBuilders.post("/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(body))

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isCreated)

        Mockito.verify(simpleService, Mockito.times(1)).add(
            body = body
        )
    }
    @Test
    fun `Successfully removed by id`() {
        TODO()
    }
    @Test
    fun `Successfully retrieved all JSON entries`() {
        val requestBuilder = MockMvcRequestBuilders.get("/all")

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

        Mockito.verify(simpleService, Mockito.times(1)).getAll()
    }
}