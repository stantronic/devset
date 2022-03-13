package io

import menu.newLine

fun output(string: String, color: String = AnsiColours.white) {
    print(color + string + AnsiColours.reset)
}

fun outputBlankLine() = print(newLine)