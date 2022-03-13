package menu

class MenuConfig {
    var headingFunction: (() -> String)? = null
    val items = mutableMapOf<String, MenuItem>()
}