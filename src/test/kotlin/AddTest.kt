package org.example

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AddTest {
    @Test
    fun testAdd() {
        val cpu = CPU()
        cpu.registers[0] = 5u
        cpu.registers[1] = 3u
        assertEquals(5u, cpu.registers[0])
        val array = arrayOf(RAM(), ROM())
        val display = Display()
        val add = Add(cpu, array, display)
        add.execute("10", "10")
        assertEquals(8u, cpu.registers[0])
    }

    @Test
    fun testBadRegister() {
        val cpu = CPU()
        cpu.registers[0] = 5u
        cpu.registers[1] = 3u
        assertEquals(5u, cpu.registers[0])
        val view = arrayOf(RAM(), ROM())
        val display = Display()
        val a = Add(cpu, view, display)
        val block: () -> Unit = { a.execute("T0", "10") }
        assertFailsWith<IllegalArgumentException> { block() }
    }
}