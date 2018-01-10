package be.jameswilliams.preso

import be.jameswilliams.preso.Presentation.theme
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

class SlideScreen2() : KtxScreen, Slide {


    override fun nextPressed() {
        // draw bullets else advance
        bullets.showNext()
    }

    override fun backPressed() {
        // hide bullets else previous slide
        Presentation.setScreen(SlideScreen::class.java)
    }


    var table = Table()
    var verticalGroup = VerticalGroup()
    var slideIndex = 0
    val batch = SpriteBatch()
    val bullets:Bullets
    var stage: Stage

    init {
        //Gdx.input.inputProcessor = this
        stage = Stage(ScreenViewport())


        bullets = Bullets(verticalGroup,listOf<String>(
                "One",
                "Two",
                "Three",
                "Four"
        ))

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

        var t:String = Slide.convertBB2CML(snippet)

        val label = Label(t, Label.LabelStyle(theme.codeFont, Color.WHITE) )

        label.centerLabel()
        val dt = theme as DefaultTheme
        val label2 = Label("\uf03e", Label.LabelStyle(dt.iconFont, Color.GRAY))
        stage.addActor(label)
        stage.addActor(label2)
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
}