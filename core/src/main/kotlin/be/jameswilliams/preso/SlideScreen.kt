package be.jameswilliams.preso

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
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
import ktx.log.logger

class DefaultTheme:Theme() {
    override lateinit var headerFont: BitmapFont
    override lateinit var bodyFont: BitmapFont
    override lateinit var codeFont: BitmapFont

    var iconFont: BitmapFont
    override val backgroundColor:Color = Color().set(0.0f, 0.0f, 1.0f, 1.0f)

    init {
        headerFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), 48)
        bodyFont = createStyle(Gdx.files.internal("fonts/Noto_Serif/NotoSerif-Regular.ttf"), 24)
        codeFont = createStyle(Gdx.files.internal("fonts/Inconsolata/Inconsolata-Regular.ttf"), 40)

        iconFont = createStyle(Gdx.files.internal("fonts/fontawesome-webfont.ttf"), 128, "\uf09e\uf03e")
    }
}


class SlideScreen(val root:Presentation, override var theme:Theme) : KtxScreen, InputAdapter(), Slide {
    override var template: Any
        get() = TODO("not implemented")
        set(value) {}

    override fun nextPressed() {
        println("here")
        // draw bullets else advance
        var result = bullets.showNext()
        if (result == false) {
            root.setScreen(SlideScreen2::class.java)
        }
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
        //Gdx.input.inputProcessor = this

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

        val label = Label("Hello [GREEN]Kotlin![]", Label.LabelStyle(theme.bodyFont, Color.WHITE) )

        label.centerLabel()
        val dt = theme as DefaultTheme
        val label2 = Label("\uf03e", Label.LabelStyle(dt.iconFont, Color.GRAY))
        stage.addActor(label)
        stage.addActor(label2)
    }

    fun Label.centerLabel() {
        val x = (Gdx.graphics.width - width) / 2
        val y = (Gdx.graphics.height - height) / 2
        setPosition(x,y)
    }

    override fun render(delta: Float) {
        val bg = theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        batch.use {
            // draw starting at location 0,0
            // stretch to specified width and height
            //val t = Texture(Gdx.files.internal("images/f.jpg"))
            //it.draw(t, 0f, 0f, 640f, 800f)
        }

        stage.act()
        stage.draw()

    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }

    override fun keyDown(keycode: Int): Boolean {
        // Clicker 92 - Left, 93 - Right

        if (keycode == Input.Keys.RIGHT || keycode == 93) {
            println("right")
            nextPressed()
            //slideIndex++
        } else if (keycode == Input.Keys.LEFT || keycode == 92) {
            backPressed()
            slideIndex--
        }
        return true
    }
}