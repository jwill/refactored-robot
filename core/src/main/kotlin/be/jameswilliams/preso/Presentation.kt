package be.jameswilliams.preso

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.app.KtxGame
import ktx.app.use
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import ktx.app.KtxInputAdapter

class Presentation : KtxGame<KtxScreen>(), KtxInputAdapter {
    companion object {
        lateinit var theme:Theme
    }
    override fun create() {
        Gdx.input.inputProcessor = this

        theme = DefaultTheme()
        addScreen(SlideScreen(this, theme))
        addScreen(SlideScreen2(this, theme))
        setScreen<SlideScreen>()
    }

    override fun keyDown(keycode: Int): Boolean {
       return (currentScreen as InputAdapter).keyDown(keycode)
    }
}