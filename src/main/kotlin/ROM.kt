package org.example

import java.nio.file.Files
import java.nio.file.Paths

class ROM : Memory() {
    override fun write(position: Int, value: UByte) {
        throw UnsupportedOperationException("Writing to ROM not supported.")
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun loadROM() {
        println("Type in the path to the ROM file:")
        val path = readln()
        val path2 = Paths.get(path)

        // Read all bytes from the file into a ByteArray
        val bytes = Files.readAllBytes(path2)

        // Ensure we do not exceed the bounds of the data array
        val endPosition = minOf(data.size, bytes.size)

        // Copy bytes into the data array
        for (position in bytes.indices) {
            if (position < endPosition) {
                data[position] = bytes[position].toUByte()
            } else {
                break
            }
        }
    }
}