package org.example

import kotlin.test.Test
import kotlin.test.assertEquals

class SetATest {

    @Test
    fun testSetAddressOperation() {
        val processor = CPU()
        val memoryUnits = arrayOf(RAM(), ROM())
        val screen = Display()
        val setAddress = SetA(processor, memoryUnits, screen)

        assertEquals(0u, processor.address)
        setAddress.execute("A2", "55")
        assertEquals(597u, processor.address)
    }
}