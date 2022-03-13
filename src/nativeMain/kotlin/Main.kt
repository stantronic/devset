import features.fontScaleMenu
import menu.runMenu

fun main() {
    val adb = Adb()
    val state = State.load(adb)
    welcomeMenu(state)
}


fun welcomeMenu(state: State) = runMenu {
    withHeading { "Welcome to DevSet!" }
    withOption("Change font size") {
        fontScaleMenu(state)
    }
    withOption("Change device size") {
        deviceSizeMenu(state)
    }
    withExitOption()
}

fun deviceSizeMenu(state: State) = runMenu {
    withHeading {
        state.adb.getDeviceWidth(state)
        "Device Dimensions"
    }
    withSeparator()
    withOption("Custom dimensions") {

    }
    withOption("Reset"){
        state.adb.resetSize(state)
    }
}

