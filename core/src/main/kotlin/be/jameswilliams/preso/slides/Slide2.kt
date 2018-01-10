package be.jameswilliams.preso.slides
import be.jameswilliams.preso.*
import be.jameswilliams.preso.templates.Headline1
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.app.use

class Slide2 : KtxScreen, Slide {
    val batch = SpriteBatch()

    lateinit var udacityLogo:Texture
    lateinit var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        udacityLogo = Texture(Gdx.files.internal("images/udacity-240-white.png"))
        val label = headerLabel(" I'm Curriculum Lead for Android.")
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
            it.draw(udacityLogo,x,0f)
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide3::class.java)
    }

    override fun backPressed() {
        Presentation.setScreen(Slide1::class.java)

    }
}