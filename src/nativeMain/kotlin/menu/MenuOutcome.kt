package menu

sealed class MenuOutcome
object KeepLooping : MenuOutcome()
object ExitCurrentMenu : MenuOutcome()
object ExitAll : MenuOutcome()
class Value(val value: Any?) : MenuOutcome()