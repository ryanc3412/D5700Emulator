package org.example

class CPU {
    val registers = Array<UByte>(8) { 0u }
    var counter : UShort = 0u
    var timer : UByte = 0u
    var address : UShort = 0u
    var memory : UInt = 0u
}