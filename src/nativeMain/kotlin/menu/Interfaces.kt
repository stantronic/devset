package menu

typealias  MenuAction = () -> MenuOutcome
typealias PresentationStrategy = (Map<String, MenuAction>) -> Unit

const val sectionLine =
    "---------------------------------------------------------\n"
const val newLine = "\n"

