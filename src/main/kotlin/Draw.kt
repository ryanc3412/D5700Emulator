package org.example

class Draw(
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
        require(s[0] == 15u) { "Draw Instruction must start with F." }
        require(s[2].toInt() in 0..7 && s[3].toInt() in 0..7) { "Reminder: Screen size is 8 by 8." }

        val registerIndex = s[1].toInt()
        val registerValue = cpu.registers[registerIndex]

        if (registerValue > 127u) {
            error("The value in register $registerIndex is greater than 7F (127).")
        } else {
            val value = registerValue.toInt().toChar().toString()
            display.changeDisplay(value, s[2].toInt(), s[3].toInt())
        }
    }
}