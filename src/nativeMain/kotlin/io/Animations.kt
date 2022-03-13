package io

class Spinicon(

    val frames: List<String>,
    val interval: Int = 80,
)


val Animations = mapOf(

    "dots" to Spinicon(
        listOf(
            "⠋",
            "⠙",
            "⠹",
            "⠸",
            "⠼",
            "⠴",
            "⠦",
            "⠧",
            "⠇",
            "⠏",
        )
    )
)

