package org.example

class SkipEqual(
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
        s.apply {
            add(temp1.first)
            add(temp1.second)
            add(temp2.first)
        }
        require(temp2.second == 0u) { "SkipEqual Instruction must end with 0." }
    }

    override fun performOperation() {
        require(s[0] == 8u) { "SkipEqual Instruction must start with 8." }
        if (cpu.registers[s[1].toInt()] == cpu.registers[s[2].toInt()]) {
            incrementCounter()
        }
    }
}