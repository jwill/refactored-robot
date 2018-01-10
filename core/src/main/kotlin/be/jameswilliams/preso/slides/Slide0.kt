package be.jameswilliams.preso.slides

import be.jameswilliams.preso.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen

class Slide0() : KtxScreen, Slide {
    var table = Table()
    var verticalGroup = VerticalGroup()
    val batch = SpriteBatch()

    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val title = headerLabel("Demystifying ConstraintLayout")
        val name = headerLabel("James Williams")
        val twitterId = headerLabel("@ecspike")
        val twitterIcon = iconLabel("\uf099")

        twitterIcon.setFontScale(0.5f)
        title.centerLabel()
        name.centerLabel()
        name.y -= name.height
        twitterId.centerLabel()
        twitterId.y -= (twitterId.height * 2)

        with(stage) {
            addActor(title)
            addActor(name)
            addActor(twitterIcon)
            addActor(twitterId)
        }

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        stage.act()
        stage.draw()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide1::class.java)
    }

    override fun backPressed() {
        // NO OP
    }
}