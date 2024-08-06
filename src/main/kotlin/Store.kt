package org.example

class Store(
    cpu: CPU,
    memory: Array<Memory>,
    display: Display
) : InstructionTemplate(
    cpu,
    memory,
    display
) {
    override fun splitBytes() {
        val temp = decomposeByte(firstByte)
        s.apply {
            add(temp.first)
            add(temp.second)
            add(secondByte.toUInt())
        }
    }

    override fun performOperation() {
        require(s[0] == 0u) { "Must be 0 for Store Instruction." }
        cpu.registers[s[1].toInt()] = s[2].toUByte()
    }
}