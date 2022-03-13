package io

import kotlinx.cinterop.cValue
import platform.posix.nanosleep


const val clearScreen = "\u001B[2J"
const val clearLine = "\u001B[2K"
const val cursorToTop = "\u001B[999A"
const val cursorToStart = "\u001B[999D"

/**
 * Clears the console and puts the cursor at the top of the screen
 *
 */
fun clearConsole() = output(clearScreen + cursorToTop + cursorToStart)

fun clearLine() = output(clearLine + cursorToStart)


fun wait(secs: Long = 0L, millis: Long = 0L) {
    nanosleep(
        cValue {
            tv_sec = secs
            tv_nsec = millis * 1000_000L
        },
        null
    )
}