package features

import context.State
import features.dimensions.deviceSizeMenu
import features.fontscale.fontScaleMenu
import menu.runMenu

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