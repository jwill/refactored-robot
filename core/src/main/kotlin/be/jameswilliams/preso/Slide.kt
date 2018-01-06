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
    abstract val backgroundColor: Color

    companion object {
        fun createStyle(filepath: FileHandle, size:Int) : BitmapFont {
            var font:BitmapFont
            val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"))
            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
            parameter.size = size
            font = generator.generateFont(parameter);
            font.data.markupEnabled = true
            generator.dispose(); // don't forget to dispose to avoid memory leaks!

            return font
        }
    }


}