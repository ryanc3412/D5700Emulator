package org.example

import java.util.*

abstract class InstructionTemplate(
    protected val cpu: CPU,
    protected val memory: Array<Memory>,
    protected val display: Display
) {
    protected var firstByte : UByte = 0u
    protected var secondByte : UByte = 0u
    protected var s : MutableList<UInt> = mutableListOf()

    fun hexadecimalChecker(input: String?) : Boolean {
        if (input == null) {
            return true
        }
        val bigInput = input.uppercase(Locale.getDefault())
        bigInput.forEach {
            if ((it < '0' || it > '9')
                && (it < 'A' || it > 'F')) {
                return false
            }
        }
        return true
    }

    fun execute(firstByte: String, secondByte: String) {
        require(hexadecimalChecker(firstByte)) { "Error: Byte 1 must be a hexadecimal string." }
        require(hexadecimalChecker(secondByte)) { "Error: Byte 1 must be a hex string." }
        require(firstByte.length == 2) { "Error: This must a hexadecimal Byte." }
        require(secondByte.length == 2) { "Error: This must a hexadecimal Byte" }

        this.firstByte = firstByte.toUByte(16)
        this.secondByte = secondByte.toUByte(16)
        splitBytes()
        performOperation()
        if (shouldIncrement()) {
            incrementCounter()
        }
        s.clear()
    }

    protected abstract fun splitBytes()
    protected abstract fun performOperation()

    protected fun incrementCounter() {
        cpu.counter = (cpu.counter + 2u).toUShort()
    }

    protected fun decomposeByte(byte: UByte) : Pair<UInt, UInt> {
        val value = byte.toUInt()
        val high = value shr 4
        val low = value and 15u
        return Pair(high, low)
    }

    open fun shouldIncrement() : Boolean {
        return true
    }
}