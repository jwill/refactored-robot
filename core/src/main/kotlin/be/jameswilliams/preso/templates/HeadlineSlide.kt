package be.jameswilliams.preso.templates

import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.Slide
import be.jameswilliams.preso.centerLabel
import be.jameswilliams.preso.headerLabel
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.actors.plus
import ktx.app.KtxScreen
import ktx.app.clearScreen

open class HeadlineSlide(val title: String,
                         val previous: Class<out KtxScreen>? = null,
                         val next: Class<out KtxScreen>? = null) : KtxScreen, Slide {

    val batch = SpriteBatch()
    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val label = headerLabel(title)
        label.centerLabel()
        stage + label
    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }

    override fun nextPressed() {
        if (next != null) {
            Presentation.setScreen(next)
        }
    }

    override fun backPressed() {
        if (previous != null) {
            Presentation.setScreen(previous)
        }
    }
}