package org.example

abstract class Memory {
    @OptIn(ExperimentalUnsignedTypes::class)
    protected val data = UByteArray(4000) { 0u }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun read(position: Int): UByte {
        return this.data[position]
    }

    abstract fun write(position: Int, value: UByte)
    abstract fun loadROM()
}