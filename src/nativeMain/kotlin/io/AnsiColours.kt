package io

/**
 * ANSI escape codes for using colours in terminal
 */
object AnsiColours {
    const val reset = "\u001B[0m"
    const val black = "\u001B[30m"
    const val red = "\u001B[31m"
    const val green = "\u001B[32m"
    const val yellow = "\u001B[33m"
    const val blue = "\u001B[34m"
    const val purple = "\u001B[35m"
    const val cyan = "\u001B[36m"
    const val white = "\u001B[37m"
}

fun green(string: String) = "${AnsiColours.green}$string${AnsiColours.reset}"
fun red(string: String) = "${AnsiColours.red}$string${AnsiColours.reset}"
fun white(string: String) = "${AnsiColours.white}$string${AnsiColours.reset}"
fun blue(string: String) = "${AnsiColours.blue}$string${AnsiColours.reset}"