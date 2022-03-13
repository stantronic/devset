class State(val adb: Adb) {
    var currentFontSize = FontScale.DEFAULT
    var currentDeviceWidth: Int = 0

    companion object {
        fun load(adb: Adb): State = State(adb).apply {
            adb.getFontSize(this)
            adb.getDeviceWidth(this)
        }
    }
}