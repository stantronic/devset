package io

fun getIntFromUser(question: String): Int {
    var value = 0
    while (value == 0) {
        val answer = question("$question  ")
        try {
            value = answer.toInt()
        } catch (e: Throwable) {
            output("That was not an integer", AnsiColours.red)
            wait(millis = 500)
            clearLine()
        }
    }
    return value
}