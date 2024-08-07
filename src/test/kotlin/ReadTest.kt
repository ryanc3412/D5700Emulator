package org.example

import kotlin.test.Test
import kotlin.test.assertEquals


class ReadTest {
    val processor = CPU()
    val memoryComponents = arrayOf(RAM(), ROM())
    val screen = Display()
    val reader = Read(processor, memoryComponents, screen)

    @Test
    fun testReadOperation() {
        processor.address = 30u
        processor.memory = 0u
        memoryComponents[0].write(processor.address.toInt(), 10u)
        reader.execute("37", "00")
        assertEquals(10.toUByte(), processor.registers[7])
    }

    @Test
    fun testReadFromROM() {
        processor.address = 30u
        processor.memory = 1u
        memoryComponents[0].write(processor.address.toInt(), 10u)
        reader.execute("37", "00")
        assertEquals(0.toUByte(), processor.registers[7])
    }
}