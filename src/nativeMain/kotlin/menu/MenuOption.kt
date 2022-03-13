package menu

sealed class MenuItem
class MenuOption(
    val label: String,
    val key: String? = null,
    val action: MenuAction,
    val conditionalOn: (() -> Boolean)? = null
) : MenuItem()

object Separator : MenuItem()

class Subheading(val text: String) : MenuItem()