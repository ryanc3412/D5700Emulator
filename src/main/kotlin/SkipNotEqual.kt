package org.example

class SkipNotEqual(
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
        require(temp2.second == 0u) { "SkipNotEqual Instruction must end in 0." }
    }

    override fun performOperation() {
        require(s[0] == 9u) { "SkipNotEqual Instruction must start with 9." }
        if(cpu.registers[s[1].toInt()] != cpu.registers[s[2].toInt()]) {
            incrementCounter()
        }
    }
}