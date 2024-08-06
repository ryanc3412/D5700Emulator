package org.example

class SetA(
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
        val temp2 = temp1.second shl 8
        s.apply {
            add(temp1.first)
            add(temp2 or secondByte.toUInt())
        }
    }

    override fun performOperation() {
        require(s[0] == 10u) { "Jump Instruction must start with A." }
        cpu.address = s[1].toUShort()
    }
}