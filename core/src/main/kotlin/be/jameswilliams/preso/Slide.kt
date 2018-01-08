package be.jameswilliams.preso

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin


interface Slide {
    var template:Any
    var theme:Theme

    fun setSlideContent()
    fun nextPressed()
    fun backPressed()
}

abstract class Theme:Skin() {
    abstract var headerFont:BitmapFont
    abstract var bodyFont:BitmapFont
    abstract var codeFont:BitmapFont
    abstract val backgroundColor: Color

    companion object {
        fun createStyle(filepath: FileHandle, size:Int, characters:String? = null) : BitmapFont {
            var font:BitmapFont
            val generator = FreeTypeFontGenerator(filepath)
            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
            parameter.size = size
            if (characters != null) {
                parameter.characters = characters
            }
            font = generator.generateFont(parameter);
            font.data.markupEnabled = true
            generator.dispose(); // don't forget to dispose to avoid memory leaks!

            return font
        }
    }
}