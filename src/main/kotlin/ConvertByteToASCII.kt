package org.example

class ConvertByteToASCII(
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
        require(temp2.second == 0u) { "Convert Byte To ASCII Instruction" }
    }

    override fun performOperation() {
        require(s[0] == 14u) { "Convert Byte To ASCII Instruction" }
        val registerIndex = s[1].toInt()
        val targetRegisterIndex = s[2].toInt()
        val charValue = cpu.registers[registerIndex].toInt().digitToChar()
        val asciiValue = charValue.code.toUByte()

        if (asciiValue in 0u..127u) {
            cpu.registers[targetRegisterIndex] = asciiValue
        } else {
            error("The value in register $registerIndex is not valid for ASCII conversion")
        }
    }
}