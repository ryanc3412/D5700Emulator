package org.example

class RAM : Memory() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun write(position: Int, value: UByte) {
        this.data[position] = value
    }

    override fun loadROM() {}

}