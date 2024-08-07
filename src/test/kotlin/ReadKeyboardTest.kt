package org.example

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReadKeyboardTest {
    val cpu = CPU()
    val array = arrayOf(RAM(), ROM())
    val display = Display()
    val keyboard = ReadKeyboard(cpu, array, display)

    @Test
    fun testSplit() {
        assertEquals(true,keyboard.hexadecimalChecker("1A"))
        assertEquals(false,keyboard.hexadecimalChecker("5P"))
        assertEquals(true,keyboard.hexadecimalChecker("9D"))
        assertEquals(false,keyboard.hexadecimalChecker("-45"))
        assertEquals(true,keyboard.hexadecimalChecker("FFFF"))
        assertEquals(true,keyboard.hexadecimalChecker("023"))
    }
}