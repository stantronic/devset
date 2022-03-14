package context

import features.fontscale.FontScale
import shell.execute

class State(val adb: Adb) {
    var user = ""
    var currentFontSize = FontScale.DEFAULT
    var currentDimensions = ScreenDimensions(0, 0, 0)

    companion object {
        fun load(): State {
            val currentUser = execute("whoami").trim()
            val adb = Adb.load(currentUser)
            return State(adb).apply {
                user = currentUser
                adb.getFontSize(this)
                adb.getScreenDimensions(this)
            }
        }
    }
}