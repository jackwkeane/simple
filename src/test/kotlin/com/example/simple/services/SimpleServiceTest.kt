package com.example.simple.services

import com.example.simple.ItemNotFoundException
import com.example.simple.repository.SimpleRepository
import com.example.simple.services.SimpleProducer
import com.example.simple.services.Fixtures.getSimpleModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@EnableConfigurationProperties
class SimpleServiceTest {
    private lateinit var simpleService: SimpleService

    private lateinit var simpleRepository: SimpleRepository

    private lateinit var simpleProducer: SimpleProducer

    @Before
    fun setup() {
        simpleProducer = Mockito.mock(SimpleProducer::class.java)
        simpleRepository = Mockito.mock(SimpleRepository::class.java)
        simpleService = SimpleService(simpleRepository, simpleProducer)
    }

    @Test
    fun `Successfully added SimpleModel to database`() {
        val expected = getSimpleModel()
        val input = getSimpleModel(null) // No UUID > should be converted to expected on line 31

        Mockito.`when`(simpleRepository.save(input)).thenReturn(expected)

        val actual = simpleService.add(input)

        Assert.assertEquals(expected, actual)

    }

    @Test
    fun `Successfully removed SimpleModel from database`(){
        val expected = getSimpleModel()

        Mockito.`when`(simpleRepository.findById(expected.id!!)).thenReturn(Optional.of(expected))

        simpleService.remove(expected.id!!.toString())

        Mockito.verify(simpleRepository, Mockito.times(1)).delete(expected)

        Mockito.verify(simpleProducer, Mockito.times(1)).sendMessage(null, expected.id!!)
    }

    @Test(expected = ItemNotFoundException::class)
    fun `Throw an exception when object not found (remove)`() {
        val expected = getSimpleModel()

        Mockito.`when`(simpleRepository.findById(expected.id!!)).thenReturn(Optional.empty())

        simpleService.remove(expected.id!!.toString())

        Mockito.verify(simpleRepository, Mockito.times(0)).delete(expected)
    }

    @Test
    fun `Successfully update SimpleModel from database`(){
        val expected = getSimpleModel()

        Mockito.`when`(simpleRepository.findById(expected.id!!)).thenReturn(Optional.of(expected))

        simpleService.update(expected)

        Mockito.verify(simpleRepository, Mockito.times(1)).save(expected)
    }

    @Test(expected = ItemNotFoundException::class)
    fun `Throw an exception when object not found (update)`() {
        val expected = getSimpleModel()

        Mockito.`when`(simpleRepository.findById(expected.id!!)).thenReturn(Optional.empty())

        simpleService.update(expected)

        Mockito.verify(simpleRepository, Mockito.times(0)).save(expected)
    }

}