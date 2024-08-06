package org.example

class ReadKeyboard(
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
        require(secondByte == 0.toUByte()) { "ReadKeyboard Instruction second byte must be 00." }
    }

    override fun performOperation() {
        require(s[0] == 6u) { "" }
        println("Enter a valid input: ")
        val userInput = readLine()
        if(hexadecimalChecker(userInput)) {
            if (userInput == null) {
                cpu.registers[s[1].toInt()] = 0u
            } else {
                val registerIndex = s[1].toInt()
                val firstChunk = userInput.chunked(2).first().toUByte(16)
                cpu.registers[registerIndex] = firstChunk
            }
        }
    }
}