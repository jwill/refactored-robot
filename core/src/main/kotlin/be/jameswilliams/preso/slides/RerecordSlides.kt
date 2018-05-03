package be.jameswilliams.preso.slides

import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.Slide
import be.jameswilliams.preso.centerX
import be.jameswilliams.preso.headerLabel
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.actors.plus
import ktx.app.KtxScreen
import ktx.app.clearScreen

open class AndroidViewSlide : KtxScreen, Slide {
    var showMeasure = false
    var showLayout = false

    val renderer = ShapeRenderer()
    val batch = SpriteBatch()
    val ruler = Texture(Gdx.files.internal("images/ruler.png"))
    val layout = Texture(Gdx.files.internal("images/layout-graphic.png"))
    val layoutSprite = Sprite(layout)
    val rulerSprite = Sprite(ruler)

    var stage: Stage

    val tenthY = Gdx.graphics.height / 10f
    val tenthX = Gdx.graphics.width / 10f
    val quarterX = Gdx.graphics.width / 4f


    lateinit var rulerLabel : Label
    lateinit var layoutLabel : Label

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()

    }

    override fun setSlideContent() {
        val titleLabel = headerLabel("Android View Rendering Cycle")

        titleLabel.centerX()
        titleLabel.y = Gdx.graphics.height - titleLabel.height - 16f
        stage + titleLabel

        rulerLabel = headerLabel("Measure")
        layoutLabel = headerLabel("Layout")



        rulerLabel.setPosition(quarterX/2f + tenthX/2, 8 * tenthY)
        stage + rulerLabel

        rulerSprite.setPosition(quarterX/2f, 5.5f * tenthY)
        rulerSprite.rotation = 45f


        layoutLabel.setPosition(2*quarterX + tenthX* 2f, 8 * tenthY)
        stage + layoutLabel



        layoutSprite.setPosition(2*quarterX + tenthX, 3 * tenthY )

        layoutLabel.isVisible = false
        rulerLabel.isVisible = false

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(1f,1f,1f, 1f)
        //clearScreen(bg.r, bg.g, bg.b, bg.a)

        rulerLabel.isVisible = showMeasure
        layoutLabel.isVisible = showLayout

        with(batch) {
            begin()
            if (showMeasure) rulerSprite.draw(batch)
            if (showLayout) layoutSprite.draw(batch)

            end()
        }

        // draw bounding rectangles
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Line)
            setColor(Color.BLACK)
            val r = rulerSprite.boundingRectangle
            rect(r.x, r.y, r.width, r.height)
            val l = layoutSprite.boundingRectangle
            rect(l.x, l.y, l.width, l.height)

            end()
        }

        stage.act()
        stage.draw()
    }

    override fun backPressed() {
        if (showLayout)
            showLayout = false
        else if (showMeasure)
            showMeasure = false
        else super.backPressed()
    }

    override fun nextPressed() {
        if (!showMeasure)
            showMeasure = true
        else if (!showLayout)
            showLayout = true
        else super.nextPressed()

    }



    override fun dispose() {
        super.dispose()
        batch.dispose()
        stage.dispose()
    }
}

// Layout Dimmed
class SlideTest : AndroidViewSlide() {
    override fun setSlideContent() {
        super.setSlideContent()

        showMeasure = true
        showLayout = true
        layoutLabel.setText("")
        layoutSprite.setColor(1f,1f,1f, 0.10f)
    }
}

// Measure Dimmed
class SlideTest2 : AndroidViewSlide() {
    override fun setSlideContent() {
        super.setSlideContent()

        showMeasure = true
        showLayout = true
        rulerLabel.setText("")
        rulerSprite.setColor(1f,1f,1f, 0.10f)
    }
}