package org.example

class FactoryForInstructions(
    private val cpu: CPU,
    private val memory: Array<Memory>,
    private val display: Display
) {
    private val instructions: Map<String, InstructionTemplate> = mapOf(
        "0" to Store(this.cpu, this.memory, this.display),
        "1" to Add(this.cpu, this.memory, this.display),
        "2" to Sub(this.cpu, this.memory, this.display),
        "3" to Read(this.cpu, this.memory, this.display),
        "4" to Write(this.cpu, this.memory, this.display),
        "5" to Jump(this.cpu, this.memory, this.display),
        "6" to ReadKeyboard(this.cpu, this.memory, this.display),
        "7" to SwitchMemory(this.cpu, this.memory, this.display),
        "8" to SkipEqual(this.cpu, this.memory, this.display),
        "9" to SkipNotEqual(this.cpu, this.memory, this.display),
        "A" to SetA(this.cpu, this.memory, this.display),
        "B" to SetT(this.cpu, this.memory, this.display),
        "C" to ReadT(this.cpu, this.memory, this.display),
        "D" to ConvertToBaseTen(this.cpu, this.memory, this.display),
        "E" to ConvertByteToASCII(this.cpu, this.memory, this.display),
        "F" to Draw(this.cpu, this.memory, this.display)
    )

    fun execute(b1: UByte, b2: UByte) {
        val n1 = convertUByteToHex(b1)
        val n2 = convertUByteToHex(b2)
        val instructionValue = n1.chunked(1)[0]

        val instruction = instructions[instructionValue]
            ?: throw IllegalArgumentException("Error: Invalid Instruction")

        instruction.execute(n1, n2)
    }

    private fun convertUByteToHex(byte: UByte): String {
        return String.format("%02X", byte.toByte())
    }
}