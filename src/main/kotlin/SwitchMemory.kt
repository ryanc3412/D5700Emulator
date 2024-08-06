package org.example

class SwitchMemory(
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
        val temp2 = decomposeByte(secondByte)
        s.add(temp1.first)
        require(temp1.second == 0u && temp2.first == 0u && temp2.second == 0u) { "Switch Memory Instruction must end in 000." }
    }

    override fun performOperation() {
        require(s[0] == 7u) { "Switch Memory Instruction must start with 7." }
        cpu.memory = if (cpu.memory == 1u) 0u else 1u
    }
}