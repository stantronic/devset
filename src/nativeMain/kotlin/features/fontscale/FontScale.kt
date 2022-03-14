package features.fontscale

enum class FontScale(val pointSize: String) {
    SMALL("0.85"),
    DEFAULT("1.0"),
    LARGE("1.15"),
    LARGEST("1.3");

    companion object {
        fun fromPointSize(value: String): FontScale = values()
            .find { it.pointSize == value } ?: error("Could not find font size matching $value")
    }
}