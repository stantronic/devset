package features.dimensions

import context.ScreenDimensions
import context.State
import io.getIntFromUser
import io.outputBlankLine

fun selectCustomDimensions(state: State) {
    outputBlankLine()
    val density = getIntFromUser("Please input a density (integer):")
    val width = getIntFromUser("Please input a width (integer):")
    val height = getIntFromUser("Please input a height (integer):")

    state.adb.setScreenDimensions(state, ScreenDimensions(width, height, density))
}