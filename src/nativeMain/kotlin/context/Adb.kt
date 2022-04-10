package context

import features.fontscale.FontScale
import io.AnsiColours.red
import io.clearConsole
import io.output
import io.red
import io.wait
import menu.newLine
import shell.execute

class Adb private constructor(var executable: String) {

    var connected: Boolean = false
        private set

    fun getFontSize(state: State) {

        val size = execute("$executable shell settings get system font_scale")
        state.currentFontSize = FontScale.fromPointSize(size.toFloat().toString())
    }

    private fun init() {
        val devices = adb("devices").removePrefix("List of devices attached")
        val numberOrDevices = devices.split("/n").filter { it.isNotBlank() }.size

        when (numberOrDevices) {
            0 -> {
                output(red("You are not currently connected to any device\n"))
                output("Please start an emulator to use DevSetter, or connect by adb")
                wait(2)
            }
            1 -> {
                connected = true
                output("Connected device: $devices")
            }
            else -> {
                output(red("You are currently connected to more than one device. This is not currently supported by DevSetter"))
            }
        }
    }

    private fun adb(command: String) = execute("$executable $command")

    fun changeFontSize(option: FontScale, state: State) {
        execute("$executable shell settings put system font_scale ${option.pointSize}")
        state.currentFontSize = option
    }

    fun getScreenDimensions(state: State): ScreenDimensions {
        val densityString = execute("$executable shell wm density")
        val sizeString = execute("$executable shell wm size")
        val density = densityString.split(" ").last().trim().toInt()
        val size = sizeString.split(" ").last().trim()
        val (width, height) = size.split("x").let { it.first().toInt() to it[1].toInt() }
        val screenDimensions = ScreenDimensions(width, height, density)

        state.currentDimensions = screenDimensions
        return screenDimensions
    }

    fun setScreenDimensions(state: State, dimensions: ScreenDimensions) {
        execute("$executable shell wm density ${dimensions.density}")
        execute("$executable shell wm size ${dimensions.width}x${dimensions.height}")
        getScreenDimensions(state)
    }

    fun resetSize(state: State) {
        execute("$executable shell wm density reset")
        execute("$executable shell wm size reset")
        getScreenDimensions(state)
    }

    companion object {
        fun load(currentUser: String): Adb {

            val path = "/Users/${currentUser}/Library/Android/sdk/platform-tools/adb"
            try {
                clearConsole()
                output("Current user detected as $currentUser$newLine")
                wait(1)
                execute("ls $path")
                output("Adb located at $path$newLine")
                wait(1)

            } catch (e: Throwable) {

                output("Adb was missing at the usual location!$newLine", red)
            }

            val adb = Adb(path)
            adb.init()
            return adb
        }
    }
}

class ScreenDimensions(val width: Int, val height: Int, val density: Int) {
    override fun toString(): String = "${width}x${height} d:${density}"
}

