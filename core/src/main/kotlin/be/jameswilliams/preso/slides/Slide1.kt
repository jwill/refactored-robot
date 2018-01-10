package be.jameswilliams.preso.slides

import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.Slide
import be.jameswilliams.preso.centerLabel
import be.jameswilliams.preso.headerLabel
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.app.use

class Slide1 : KtxScreen, Slide {
    var table = Table()
    var verticalGroup = VerticalGroup()
    val batch = SpriteBatch()

    lateinit var udacityLogo: Texture

    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        udacityLogo = Texture(Gdx.files.internal("images/udacity-240-white.png"))
        val label = headerLabel("I work at Udacity. I'm Curriculum Lead for Android.")
        label.centerLabel()
        stage.addActor(label)
    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        stage.act()
        stage.draw()

        batch.use {
            var x = (Gdx.graphics.width - udacityLogo.width) / 2f
            it.draw(udacityLogo, x, 0f)
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide2::class.java)
    }

    override fun backPressed() {
        Presentation.setScreen(Slide0::class.java)

    }
}