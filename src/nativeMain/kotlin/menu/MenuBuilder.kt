package menu

import io.blue
import io.red

/**
 * Creates a basic menu
 */
class MenuBuilder {
    private var optionCounter: Int = 0
    private val config = MenuConfig()

    fun withOption(label: String, menuAction: () -> Unit) {
        val key = getOptionCounterIncremented()
        config.items[key] = MenuOption(label, key, {
            menuAction()
            KeepLooping
        })
    }

    fun withBackOption(key: String = "b", label: String = blue("Back")) {
        if (config.items[key] == null && config.items["e"] == null) {
            config.items[key] = MenuOption(label, key, {
                ExitCurrentMenu
            })
        }
    }

    fun withExitOption(key: String = "e", label: String = red("Exit")) {
        if (config.items[key] == null) {
            config.items[key] = MenuOption(label, key, {
                ExitAll
            })
        }
    }

    fun build(): Menu = BasicMenu(config)

    private fun getOptionCounterIncremented(): String {
        optionCounter++
        return optionCounter.toString()
    }

    fun withHeading(function: () -> String) {
        config.headingFunction = function
    }

    fun withSeparator() {
        val key = (optionCounter + 0.5).toString()
        config.items[key] = Separator
    }

    fun withSubheading(heading: String) {
        val key = (optionCounter + 0.7).toString()
        config.items[key] = Subheading(heading)
    }
}

fun buildMenu(block: MenuBuilder.() -> Unit): Menu {
    return MenuBuilder()
        .apply(block)
        .apply {
            withBackOption()
        }
        .build()
}

fun runMenu(block: MenuBuilder.() -> Unit) {
    buildMenu(block).run()
}

