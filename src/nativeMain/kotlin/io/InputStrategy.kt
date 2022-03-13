package io

import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.fgets
import platform.posix.pclose
import platform.posix.popen

const val defaultEnterTextPrompt = "\nPlease enter your choice  > "
const val defaultKeyPressPrompt = "\nPlease press a key  > "

interface InputStrategy {
    operator fun invoke(promptText: String? = null): String
}

object KeyPress : InputStrategy {
    override fun invoke(promptText: String?): String {
        print(promptText ?: defaultKeyPressPrompt)
        return getKeyPress()
    }
}

object EnterText : InputStrategy {
    override fun invoke(promptText: String?): String {
        print(promptText ?: defaultEnterTextPrompt)
        return readLine().orEmpty()
    }
}

fun getKeyPress(): String {
    val file = popen("bash -c 'read -s -n1 c && printf \"%s\" \"\$c\"'", "r")

    if (file == null) {
        output("Error opening pipe!", AnsiColours.red)
        return "-1"
    }

    val byteArray = ByteArray(32).refTo(0)
    val pointer = fgets(byteArray, 32, file)
    pclose(file)

    return pointer?.toKString() ?: "-1"
}