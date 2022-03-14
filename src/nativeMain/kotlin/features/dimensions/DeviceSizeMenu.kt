package features.dimensions

import context.State
import io.blue
import io.white
import menu.MenuBuilder
import menu.runMenu

fun deviceSizeMenu(state: State) = runMenu {
    withHeading {
        val dimensions = state.adb.getScreenDimensions(state)
        """|----- Device Dimensions -----
           |Currently: $dimensions
        """.trimMargin()
    }
    allDeviceSpecs.forEach {
        withDeviceSpec(it, state)
    }

    withSeparator()

    withOption("Custom dimensions") {
        selectCustomDimensions(state)
    }
    withOption("Reset") {
        state.adb.resetSize(state)
    }
}

private fun MenuBuilder.withDeviceSpec(spec: DeviceSpec, state: State) {
    withOption(
        """|${spec.label} ${
            spec.code?.let { white("($it)") }.orEmpty()
        } ${blue(spec.dimensions.toString())}
        """.trimMargin()
    ) {
        state.adb.setScreenDimensions(state, spec.dimensions)
    }
}

