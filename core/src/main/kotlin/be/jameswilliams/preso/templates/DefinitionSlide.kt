package be.jameswilliams.preso.templates

import be.jameswilliams.preso.*
import be.jameswilliams.preso.slides.Slide17
import be.jameswilliams.preso.slides.Slide18A
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import ktx.app.KtxScreen
import ktx.app.clearScreen

open class DefinitionSlide(val title:String, val definition:String,
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
        val definitionLabel = headerLabel(definition)

        definitionLabel.setFontScale(0.75f)

        titleLabel.centerX()
        titleLabel.y = Gdx.graphics.height - titleLabel.height -16f

        val maxLabelWidth = Gdx.graphics.width - 256f
        if (definitionLabel.width > maxLabelWidth) {
            definitionLabel.width = maxLabelWidth
        }
        definitionLabel.centerLabel()


        definitionLabel.setWrap(true)

        stage.addActor(titleLabel)
        stage.addActor(definitionLabel)
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
