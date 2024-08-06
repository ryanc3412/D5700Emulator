package org.example

class ReadT(
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
        require(secondByte == 0.toUByte()) { "ReadT Instruction must end with 00." }
    }

    override fun performOperation() {
        require(s[0] == 12u) { "ReadT Instruction must start with C." }
        cpu.registers[s[1].toInt()] = cpu.timer.toUByte()
    }
}