package menu

import io.*
import platform.posix.exit

interface Menu {
    fun run(): MenuOutcome
}

/**
 * A menu that executes functions in response to entering
 * numbers / characters
 */
class BasicMenu(val config: MenuConfig, val receiveInput: InputStrategy = KeyPress) : Menu {

    private val items = config.items.toMap()

    private fun presentMenu() {
        clearConsole()
        output(
            config.headingFunction?.invoke().orEmpty(),
            AnsiColours.green
        )
        outputBlankLine()
        items.values.forEach {
            presentMenuItem(it)
        }
    }

    private fun presentMenuItem(it: MenuItem) {
        when (it) {
            is MenuOption -> {
                output("${it.key}. ", AnsiColours.blue)
                output("${it.label}$newLine", AnsiColours.yellow)
            }
            Separator -> output(sectionLine + newLine)
            is Subheading -> output(it.text + newLine)
        }
    }

    override fun run(): MenuOutcome {
        var outcome: MenuOutcome? = null
        var looping = true
        while (looping) {
            presentMenu()
            val input = receiveInput()
            when (val option = items[input]) {
                is MenuOption -> {
                    val actionOutcome = option.action()
                    if (actionOutcome !is KeepLooping) {
                        outcome = actionOutcome
                        looping = false
                    }
                }
                else -> {
                    if (outcome == null) {
                        clearLine()
                        output("That was not an option", AnsiColours.red)
                        wait(millis = 500)
                    }
                }
            }
        }
        cleanup()
        if (outcome is ExitAll) {
            output(green("Okay, bye!$newLine"))
            exit(0)
        }
        return outcome!!
    }

    private fun cleanup() {

    }
}