package be.jameswilliams.preso

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import be.jameswilliams.preso.TypographicalScale.*


interface Slide {
    fun setSlideContent()
    fun nextPressed() {}
    fun backPressed() {}

    companion object {
        fun convertBB2CML(x:String): String {
            var temp = x
            val pattern = Regex("\\[color=#......\\]")
            val pattern2 = Regex("\\[/color\\]")

            return temp.replace("color=","").replace("/color", "")
            //temp = pattern.replace(temp, { v:MatchResult -> v.value.replace("color=","") })
            //temp = pattern2.replace(temp, "[]")

        }
    }
}

abstract class Theme:Skin() {
    abstract var headerFont:BitmapFont
    abstract var bodyFont:BitmapFont
    abstract var codeFont:BitmapFont
    abstract var iconFont:BitmapFont
    abstract val backgroundColor: Color

    companion object {
        fun createStyle(filepath: FileHandle, scale: TypographicalScale, characters:String? = null, distanceFactor:Float = 0.5f) : BitmapFont {
            var font:BitmapFont
            val generator = FreeTypeFontGenerator(filepath)
            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
            parameter.size = (scale.sp * Gdx.graphics.getDensity() * distanceFactor).toInt()
            if (characters != null) {
                parameter.characters = characters
            }
            font = generator.generateFont(parameter)
            font.data.markupEnabled = true
            generator.dispose() // don't forget to dispose to avoid memory leaks!

            return font
        }
    }
}

fun Color.toHex() : String {
    return "#"+this.toString()
}

class ScreencastTheme:DefaultTheme() {
    val blueHeader = Color.valueOf("#2C6481")
    val pinkHighlight = Color.valueOf("#F06292")
    val ltGrayDetail = Color.valueOf("#90A4AE")
    val purpleDetail = Color.valueOf("#7986CB")

    //override val backgroundColor:Color = ltGrayDetail
    override val backgroundColor:Color = Color.valueOf("#E9F4FF")
}

open class DefaultTheme:Theme() {
    override lateinit var headerFont: BitmapFont
    override lateinit var bodyFont: BitmapFont
    override lateinit var codeFont: BitmapFont
    var codeFont2:BitmapFont

    override lateinit var iconFont: BitmapFont
    override val backgroundColor:Color = Color().set(0.0f, 0.0f, 0.0f, 1.0f)

    init {
        headerFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), DISPLAY4)
        bodyFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), DISPLAY3)
        codeFont = createStyle(Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular.ttf"), DISPLAY3)
        codeFont2 = createStyle(Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular.ttf"), DISPLAY3HALF)

        //headerFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), 96)
        //bodyFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), 64)
        //codeFont = createStyle(Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular.ttf"), 64)
        //codeFont2 = createStyle(Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular.ttf"), 84)

        /* http://www.fontawesomecheatsheet.com/
         * Android          \uf17b
         * Github           \uf09b
         * RSS              \uf09e
         * picture outline  \uf03e
         * twitter          \uf099
         * circle (bullet)  \uf111
         */
        iconFont = createStyle(Gdx.files.internal("fonts/fontawesome-webfont.ttf"), DISPLAY4, "\uf17b\uf09b\uf09e\uf03e\uf099\uf111")
    }
}
object IconFontConstants {
    val ANDROID = "\uf17b"
    val TWITTER = "\uf099"
    val BULLET = "\uf111"
    val GITHUB = "\uf09b"
}

fun Label.centerLabel() {
    val x = (Gdx.graphics.width - width) / 2
    val y = (Gdx.graphics.height - height) / 2
    setPosition(x, y)
}

fun Label.centerX() {
    setPosition((Gdx.graphics.width - width) / 2, this.y)
}