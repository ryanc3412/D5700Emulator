package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StoreTest {
    @Test
    fun testStoringValue() {
        val processor = CPU()
        assertEquals(0u, processor.registers[0])
        val memoryComponents = arrayOf(RAM(), ROM())
        val monitor = Display()
        val memoryStore = Store(processor, memoryComponents, monitor)
        memoryStore.execute("00", "ff")
        assertEquals(255.toUByte(), processor.registers[0])
    }
}