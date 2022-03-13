class Adb {

    fun getFontSize(state: State) {

        val size = execute("$Executable shell settings get system font_scale")
        state.currentFontSize = FontScale.fromPointSize(size.toFloat().toString())
    }


    fun changeFontSize(option: FontScale, state: State) {
        execute("$Executable shell settings put system font_scale ${option.pointSize}")
        state.currentFontSize = option
    }

    fun getDeviceWidth(state: State) {
        val density = execute("$Executable shell wm density")
        val size = execute("$Executable shell wm size")
        println("Density:\n $density")
        println("Size:\n $size")
    }

    fun resetSize(state: State) {
        execute("$Executable shell wm density reset")
        execute("$Executable shell wm size reset")
        getDeviceWidth(state)
    }

    companion object {

        private const val Executable =
            "/Users/stevenstanton/Library/Android/sdk/platform-tools/adb"
    }
}