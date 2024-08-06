package org.example

class Read(
    cpu: CPU,
    memory: Array<Memory>,
    display: Display
) : InstructionTemplate(
    cpu,
    memory,
    display
) {

    override fun splitBytes() {
        val temp1 = decomposeByte(firstByte)
        s.apply {
            add(temp1.first)
            add(temp1.second)
        }

        require(secondByte == 0.toUByte()) { "Read Instruction second byte must be 00." }
    }

    override fun performOperation() {
        require(s[0] == 3u) { "Read instruction must start with 3." }
        cpu.registers[s[1].toInt()] = memory[cpu.memory.toInt()].read(cpu.address.toInt())
    }
}