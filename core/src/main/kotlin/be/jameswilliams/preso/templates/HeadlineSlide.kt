package be.jameswilliams.preso.templates
import be.jameswilliams.preso.*
import be.jameswilliams.preso.slides.Slide2
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen
import kotlin.reflect.KClass

class Headline1(): HeadlineSlide("My Headline") {
    override fun nextPressed() {
        Presentation.setScreen(Headline2::class.java)
    }

    override fun backPressed() {
        Presentation.setScreen(Slide2::class.java)
    }
}

class Headline2(): HeadlineSlide("My Headline 2") {
    override fun nextPressed() {

    }

    override fun backPressed() {
        Presentation.setScreen(Headline1::class.java)
    }
}

open class HeadlineSlide(val title:String, val previous:Class<out KtxScreen>? = null, val next:Class<out KtxScreen>? = null) : KtxScreen, Slide {

    val batch = SpriteBatch()
    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val label = headerLabel(title)
        label.centerLabel()
        stage.addActor(label)
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