package be.jameswilliams.preso

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.app.use
import java.util.regex.Pattern

/**
 * Created by James Williams on 1/7/2018.
 */

// Convert BBCode to libGDX Color Markup Language

class SlideScreen2(val root:Presentation, override var theme:Theme) : KtxScreen, InputAdapter(), Slide {
    fun convertBB2CML(x:String): String {
        var temp = x
        val pattern = Regex("\\[color=#......\\]")
        val pattern2 = Regex("\\[/color\\]")

        return temp.replace("color=","").replace("/color", "")
        //temp = pattern.replace(temp, { v:MatchResult -> v.value.replace("color=","") })
        //temp = pattern2.replace(temp, "[]")

    }


    override var template: Any
        get() = TODO("not implemented")
        set(value) {}

    override fun nextPressed() {
        // draw bullets else advance
        bullets.showNext()
    }

    override fun backPressed() {
        // hide bullets else previous slide
        root.setScreen(SlideScreen::class.java)
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
        var snippet = """
[color=#66aaff]package[/color] com[color=#ff00cc].[/color]udacity[color=#ff00cc].[/color]stockhawk[color=#ff00cc].[/color]sync
        """


        val titleLabel = Label("Title", Label.LabelStyle(theme.headerFont, Color.WHITE))
        table.add(titleLabel).expandX()
        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true
        table.row()
        table.add(verticalGroup).left().padLeft(16f)

        // Add table to stage
        stage.addActor(table)

        var t:String = convertBB2CML(snippet)

        val label = Label(t, Label.LabelStyle(theme.codeFont, Color.WHITE) )

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
        System.out.println(keycode)
        // Clicker 92 - Left, 93 - Right

        if (keycode == Input.Keys.RIGHT || keycode == 93)
            nextPressed()
        //slideIndex++
        else if (keycode == Input.Keys.LEFT || keycode == 92)
            backPressed()
        return true
    }
}