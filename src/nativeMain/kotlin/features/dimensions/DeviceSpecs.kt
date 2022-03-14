package features.dimensions

import context.ScreenDimensions

class DeviceSpec(
    val label: String,
    val subheading: String,
    val code: String? = null,
    val dimensions: ScreenDimensions
)

val SamsungGalaxy_S21G_5G = DeviceSpec(
    label = "Samsung Galaxy S21 5G",
    subheading = "Fame: 79%   Popularity: 45%",
    code = "SM-G991B",
    dimensions = ScreenDimensions(1080, 2400, 421)
)
val SamsungGalaxy_S10 = DeviceSpec(
    label = "Samsung Galaxy S10",
    subheading = "Fame: 80%   Popularity: 44%",
    code = "SM-G973F",
    dimensions = ScreenDimensions(1440, 3040, 550)
)

val SamsungGalaxy_S6 = DeviceSpec(
    label = "Galaxy S6",
    subheading = "Fame: 81%   Popularity: 35%",
    code = "SM-G920x",
    dimensions = ScreenDimensions(1440, 2560, 577)
)

val Pixel_4a = DeviceSpec(
    label = "Pixel 4a",
    subheading = "",
    dimensions = ScreenDimensions(1080, 2340, 443)
)

val PalmPhone = DeviceSpec(
    label = "Palm Phone",
    subheading = "Its tiny!",
    dimensions = ScreenDimensions(720, 1280, 445)
)

val allDeviceSpecs
    get() = listOf(
        SamsungGalaxy_S21G_5G,
        SamsungGalaxy_S10,
        SamsungGalaxy_S6,
        PalmPhone,
        Pixel_4a,
    )