package be.jameswilliams.preso.templates

import be.jameswilliams.preso.Bullets
import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.Slide
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import ktx.app.KtxScreen
import ktx.app.clearScreen

open class BulletsSlide(val title:String, val bulletItems:List<String>, debug:Boolean = false) : KtxScreen, Slide {
    var table = Table()
    var verticalGroup = VerticalGroup()
    val batch = SpriteBatch()
    val bullets: Bullets
    var stage: Stage

    init {
        //Gdx.input.inputProcessor = this
        stage = Stage(ScreenViewport())
        bullets = Bullets(verticalGroup,bulletItems)

        if (debug) {
            table.debugAll()
        }

        setSlideContent()
    }

    override fun nextPressed() {
        // draw bullet
    }

    override fun backPressed() {
        // hide bullet
    }


    override fun setSlideContent() {
        val titleLabel = Label(title, Label.LabelStyle(Presentation.theme.headerFont, Color.WHITE))
        table.add(titleLabel).expandX().padTop(16f)

        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true
        table.row()
        //table.add(verticalGroup).left().padLeft(16f)
        table.add(verticalGroup).center().padTop(64f)


        table.align(Align.top)
        // Add table to stage
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)
        table.setFillParent(true)


        //table.centerPosition()
        stage.act()
        stage.draw()

    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }
}