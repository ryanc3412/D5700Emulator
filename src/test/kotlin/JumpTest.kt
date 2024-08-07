package org.example

import kotlin.test.Test
import kotlin.test.assertEquals

class JumpTest {
    val processor = CPU()
    val memoryUnits = arrayOf(RAM(), ROM())
    val screen = Display()
    val jumpInstruction = Jump(processor, memoryUnits, screen)
    val addInstruction = Add(processor, memoryUnits, screen)

    @Test
    fun testBasicJumpOperation() {
        assertEquals(0u, processor.counter)
        addInstruction.execute("10", "10")
        assertEquals(2u, processor.counter)
        jumpInstruction.execute("51", "f2")
        assertEquals(498u, processor.counter)
    }
}