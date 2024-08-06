package org.example

class ConvertToBaseTen(
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
        require(secondByte == 0.toUByte()) { "ConvertToBaseTen Instruction has to end with 00." }
    }

    override fun performOperation() {
        require(s[0] == 13u) { "Convert To Base Ten Instruction has to start with 13u." }
        var tempInt = cpu.registers[s[1].toInt()].toInt()
        val address = cpu.address.toInt()

        for (i in 0..2) {
            memory[cpu.memory.toInt()].write(address + 2 - i, (tempInt % 10).toUByte())
            tempInt /= 10
        }
    }
}