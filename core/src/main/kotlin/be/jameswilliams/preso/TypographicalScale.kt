package be.jameswilliams.preso
// Inspired by the typographical scale in Android
// Font sizes determined by sp * density * distanceFactor

enum class TypographicalScale(val sp: Int) {
    DISPLAY4(112),
    DISPLAY3HALF(96),
    DISPLAY3(56),
    DISPLAY2(45),
    DISPLAY1(34),
    HEADLINE(24),
    TITLE(20),
    SUBHEADING(16),
    BODY2(14),
    BODY1(14),
    CAPTION(12)
}