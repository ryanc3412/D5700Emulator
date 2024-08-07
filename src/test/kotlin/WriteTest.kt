package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WriteTest {
    @Test
    fun testWriteRAM() {
        val cpu = CPU()
        val array = arrayOf(RAM(), ROM())
        val display = Display()
        val write = Write(cpu, array, display)
        cpu.address = 30u
        cpu.memory = 0u
        cpu.registers[7] = 10u
        write.execute("47", "00")
        assertEquals(10.toUByte(), array[cpu.memory.toInt()].read(cpu.address.toInt()))
    }
}