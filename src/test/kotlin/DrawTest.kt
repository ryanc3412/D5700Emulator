package org.example

import kotlin.test.Test
import kotlin.test.assertEquals

class DrawTest {

    @Test
    fun testAlternateDrawOperation() {
        val processor = CPU()
        val memoryUnits = arrayOf(RAM(), ROM())
        val screen = Display()
        val render = Draw(processor, memoryUnits, screen)

        screen.sendToConsole()
        assertEquals(null, screen.checkDisplay(2, 3))
        processor.registers[0] = 72u
        render.execute("F0", "00")
        screen.sendToConsole()
        assertEquals("H", screen.checkDisplay(0, 0))
    }
}