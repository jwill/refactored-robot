package be.jameswilliams.preso

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.app.KtxGame
import ktx.app.use
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter



class Presentation : KtxGame<SlideScreen>() {
    override fun create() {
        val theme:Theme = DefaultTheme()
        addScreen(SlideScreen(theme))
        setScreen<SlideScreen>()
    }
}