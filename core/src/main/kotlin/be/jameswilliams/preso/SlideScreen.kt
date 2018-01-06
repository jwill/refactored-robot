package be.jameswilliams.preso

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Value
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import com.rafaskoberg.gdx.typinglabel.TypingLabel
import ktx.actors.plus
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.app.use

class DefaultTheme:Theme() {
    override lateinit var headerFont: BitmapFont
    override lateinit var bodyFont: BitmapFont
    override val backgroundColor:Color = Color().set(0.0f, 0.0f, 1.0f, 1.0f)

    init {
        headerFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), 48)
        bodyFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), 96)
    }
}


class SlideScreen(override var theme:Theme) : KtxScreen, InputAdapter(), Slide {
    override var template: Any
        get() = TODO("not implemented")
        set(value) {}

    override fun nextPressed() {
        // draw bullets else advance
        bullets.showNext()
    }

    override fun backPressed() {
        // hide bullets else previous slide
        bullets.goBack()
    }

    val stage = Stage(ScreenViewport())

    var table = Table()
    var verticalGroup = VerticalGroup()
    var slideIndex = 0
    val batch = SpriteBatch()
    val bullets:Bullets

    init {
        Gdx.input.inputProcessor = this

        bullets = Bullets(verticalGroup,listOf<String>(
                "One",
                "Two",
                "Three",
                "Four"
        ), theme)

        setSlideContent()
    }


    override fun setSlideContent() {
        with (table) {
            debugTable()
            debugAll()
            // Align table to top of view
            top()
            setFillParent(true)
        }


        val titleLabel = Label("Title",Label.LabelStyle(theme.headerFont, Color.WHITE))
        table.add(titleLabel).expandX()
        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true
        table.row()
        table.add(verticalGroup).left().padLeft(16f)

        // Add table to stage
        stage.addActor(table)
    }

    fun Label.centerLabel() {
        val x = (Gdx.graphics.width - width) / 2
        val y = (Gdx.graphics.height - height) / 2
        setPosition(x,y)
    }

    override fun render(delta: Float) {
        val x = Color.LIGHT_GRAY
        clearScreen(x.r, x.g, x.b, x.a)
        val bg = theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)
        stage.act()
        stage.draw()
        batch.use {
            val label = Label("Hello [GREEN]Kotlin![]", Label.LabelStyle(theme.bodyFont, Color.WHITE) )

            label.centerLabel()
            stage.addActor(label)
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.RIGHT)
            nextPressed()
            //slideIndex++
        else if (keycode == Input.Keys.LEFT)
            backPressed()
            slideIndex--
        return true
    }
}