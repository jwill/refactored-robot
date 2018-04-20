package be.jameswilliams.preso.templates

import be.jameswilliams.preso.AttributeBuilder
import be.jameswilliams.preso.DefaultTheme
import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.Slide
import be.jameswilliams.preso.slides.Slide33
import be.jameswilliams.preso.slides.Slide35
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.actors.plus
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.collections.gdxArrayOf
import ktx.scene2d.KTableWidget
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textField
import ktx.style.label
import ktx.style.skin
import ktx.style.textField

open class ConstraintSlide(val constraints:Array<AttributeBuilder.ConstraintType>, val widthLabel:String, val heightLabel:String, val previous:Class<out KtxScreen>? = null,
                      val next:Class<out KtxScreen>? = null) : KtxScreen, Slide {
    val shapeRenderer = ShapeRenderer()
    val stage = Stage(ScreenViewport())
    val attributeOffset = Gdx.graphics.width * 0.6f
    val constraintOffset = Gdx.graphics.width * 0.7f
    val uiSizeX = Gdx.graphics.width * 0.4f
    val uiSizeY = Gdx.graphics.width * 0.6f
    val uiBackgroundColor = Color.valueOf("#3577e0")
    val halfY = Gdx.graphics.height * 0.5f

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

    init {
        Presentation.multiplexer.addProcessor(stage)
        setSlideContent()
        //table.debugAll()
    }

    override fun setSlideContent() {
        val constraintTable = AttributeBuilder.drawConstraintTable(Vector2(constraintOffset, 400f), widthLabel = widthLabel, heightLabel = heightLabel)

        stage + constraintTable
    }

    override fun render(delta: Float) {
        super.render(delta)
        val bg = Presentation.theme.backgroundColor
        //clearScreen(bg.r, bg.g, bg.b, bg.a)
        clearScreen(1f, 1f, 1f, bg.a)


        AttributeBuilder.drawAttributeView(Vector2(attributeOffset, 700f),constraints)


        // draw UI
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            setColor(uiBackgroundColor)
            rect(0f,0f, uiSizeX, uiSizeY)
            end()
        }

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        super.dispose()
        stage.dispose()
        shapeRenderer.dispose()
        Presentation.multiplexer.removeProcessor(stage)
    }
}