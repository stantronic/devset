package context

import features.fontscale.FontScale
import io.AnsiColours.red
import io.clearConsole
import io.output
import io.wait
import menu.newLine
import shell.execute

class Adb(var executable: String) {

    fun getFontSize(state: State) {

        val size = execute("$executable shell settings get system font_scale")
        state.currentFontSize = FontScale.fromPointSize(size.toFloat().toString())
    }

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

            return Adb(path)
        }
    }
}

class ScreenDimensions(val width: Int, val height: Int, val density: Int){
    override fun toString(): String = "${width}x${height} d:${density}"
}

