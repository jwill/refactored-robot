package be.jameswilliams.preso.templates
import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.Slide
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.actors.plus
import ktx.app.KtxScreen
import ktx.app.clearScreen

open class BackgroundImageSlide(val filehandle: FileHandle, val scale:Scaling,
                                val previous:Class<out KtxScreen>? = null,
                                val next:Class<out KtxScreen>? = null) : KtxScreen, Slide {
    val batch = SpriteBatch()
    var stage: Stage
    val texture = Texture(filehandle)
    val image = Image(texture)

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        image.setScaling(scale)
        var x = (Gdx.graphics.width - image.width) / 2f
        var y = (Gdx.graphics.height - image.height) / 2f
        image.x = x
        image.y = y
        stage + image
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