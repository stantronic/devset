package shell

import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.fgets
import platform.posix.pclose
import platform.posix.popen

fun execute(
    command: String,
    redirectStderr: Boolean = true
): String {
    val commandToExecute = if (redirectStderr) "$command 2>&1" else command
    val fp = popen(commandToExecute, "r") ?: error("Failed to run command: $command")

    val response = buildString {
        val buffer = ByteArray(4096)
        while (true) {
            val input = fgets(buffer.refTo(0), buffer.size, fp) ?: break
            append(input.toKString())
        }
    }

    val status = pclose(fp)
    if (status != 0) {
        error("Command `$command` failed with status $status${if (redirectStderr) ": $response" else ""}")
    }

    return response
}