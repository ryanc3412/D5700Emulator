package org.example

class Write(
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

        require(secondByte == 0.toUByte()) { "Write Instruction second byte must be 00." }
    }

    override fun performOperation() {
        require(s[0] == 4u) { "Write instruction must start with 4." }
        memory[cpu.memory.toInt()].write(cpu.address.toInt(), cpu.registers[s[1].toInt()])
    }
}