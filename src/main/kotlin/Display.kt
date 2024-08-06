package org.example

class Display {
    private val displayArray = Array(8) { Array<String?>(8) { null } }

    fun changeDisplay(value: String, row: Int, column: Int) {
        displayArray[row][column] = value
        this.sendToConsole()
    }

    fun checkDisplay(row: Int, column: Int) : String? {
        return displayArray[row][column]
    }

    fun sendToConsole(){
        for (row in displayArray) {
            row.forEach {
                if (it != null) {
                    print(it)
                } else {
                    print(Char(0))
                }
            }
            println("")
        }
        println("\n")
    }
}