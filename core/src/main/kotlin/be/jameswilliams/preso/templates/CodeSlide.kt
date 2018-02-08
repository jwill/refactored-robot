package be.jameswilliams.preso.templates

import be.jameswilliams.preso.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import ktx.actors.plus
import ktx.app.KtxScreen
import ktx.app.clearScreen

open class CodeSlide(val title:String, val sourceCode:String,
                           val previous:Class<out KtxScreen>? = null,
                           val next:Class<out KtxScreen>? = null) : KtxScreen, Slide {
    override fun backPressed() {
        if (previous != null) {
            Presentation.setScreen(previous)
        }
    }
    override fun nextPressed() {
        if (next != null) {
            Presentation.setScreen(next)
        }
    }

    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val titleLabel = headerLabel(title)
        val convertedCode = Slide.convertBB2CML(sourceCode)
        val codeLabel = codeLabel(convertedCode)
        codeLabel.setWrap(true)

        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true

        titleLabel.centerX()
        titleLabel.y = Gdx.graphics.height - titleLabel.height - 16f
        codeLabel.centerLabel()

        stage + titleLabel
        stage + codeLabel
    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        stage.dispose()
    }
}

