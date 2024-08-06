package org.example

class Sub(
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
            add(temp2.second)
        }
    }

    override fun performOperation() {
        require(s[0] == 2u) { "Must be 2 for Sub Instruction" }
        val nv = cpu.registers[s[1].toInt()] - cpu.registers[s[2].toInt()]
        cpu.registers[s[3].toInt()] = nv.toUByte()
    }
}