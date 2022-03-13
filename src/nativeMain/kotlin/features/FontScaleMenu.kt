package features

import FontScale
import State
import io.white
import menu.runMenu

/**
 * Change the font between 4 options provided by stock Android
 */
fun fontScaleMenu(state: State) = runMenu {
    withHeading {
        "------- Font Sizes --------\n ${
            white("Currently set to ${state.currentFontSize.name}")
        }"
    }
    withOption("Small") {
        state.adb.changeFontSize(FontScale.SMALL, state)
    }
    withOption("Default") {
        state.adb.changeFontSize(FontScale.DEFAULT, state)
    }
    withOption("Large") {
        state.adb.changeFontSize(FontScale.LARGE, state)
    }
    withOption("Largest") {
        state.adb.changeFontSize(FontScale.LARGEST, state)
    }
}