import context.State
import features.welcomeMenu

fun main() {
    val state = State.load()
    welcomeMenu(state)
}


