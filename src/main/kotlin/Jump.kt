package org.example

class Jump(
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
        var temp2 = temp1.second shl 8
        s.apply {
            add(temp1.first)
            add(temp2 or secondByte.toUInt())
        }
    }

    override fun performOperation() {
        require(s[0] == 5u) { "Jump instruction must start with 5." }
        cpu.counter = s[1].toUShort()
    }

    override fun shouldIncrement(): Boolean {
        return false
    }
}