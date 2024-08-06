package org.example

class Add(
    cpu: CPU,
    memory: Array<Memory>,
    display: Display
) : InstructionTemplate(
    cpu,
    memory,
    display
) {

    override fun splitBytes() {
        listOf(firstByte, secondByte).forEach { byte ->
            decomposeByte(byte).let { (high, low) ->
                s.add(high)
                s.add(low)
            }
        }
    }

    override fun performOperation() {
        require(s[0] == 1u) { "Must be 1 for Add Instruction" }
        val nv = cpu.registers[s[1].toInt()] + cpu.registers[s[2].toInt()]
        cpu.registers[s[3].toInt()] = nv.toUByte()
    }
}