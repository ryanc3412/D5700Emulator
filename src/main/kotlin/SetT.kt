package org.example

class SetT(
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
        val temp3 = temp1.second shl 4
        s.apply {
            add(temp1.first)
            add(temp3 or temp2.first)
        }
        require(temp2.second == 0u) { "SetT Instruction must end with 0." }
    }

    override fun performOperation() {
        require(s[0] == 11u) { "SetT Instruction must start with B." }
        cpu.timer = s[1].toUByte()
    }
}