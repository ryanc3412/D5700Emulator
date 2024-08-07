package org.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class SubTest {
    @Test
    fun testSubtraction() {
        val processor = CPU()
        processor.registers[0] = 3u
        processor.registers[1] = 2u
        assertEquals(3u, processor.registers[0])
        val memoryUnits = arrayOf(RAM(), ROM())
        val screen = Display()
        val subtractor = Sub(processor, memoryUnits, screen)
        subtractor.execute("20", "10")
        assertEquals(1u, processor.registers[0])
    }

    @Test
    fun testSubtractionWithNegatives() {
        val processor = CPU()
        processor.registers[0] = 2u
        processor.registers[1] = 3u
        assertEquals(2u, processor.registers[0])
        val memoryUnits = arrayOf(RAM(), ROM())
        val screen = Display()
        val subtractor = Sub(processor, memoryUnits, screen)
        subtractor.execute("20", "10")
        assertEquals(255.toUByte(), processor.registers[0])
    }

    @Test
    fun testInvalidRegister() {
        val processor = CPU()
        processor.registers[0] = 2u
        processor.registers[1] = 3u
        assertEquals(2u, processor.registers[0])
        val memoryUnits = arrayOf(RAM(), ROM())
        val screen = Display()
        val subtractor = Sub(processor, memoryUnits, screen)
        val action: () -> Unit = { subtractor.execute("29", "10") }
        assertFailsWith<ArrayIndexOutOfBoundsException> { action() }
    }
}