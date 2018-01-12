package be.jameswilliams.preso.slides

import be.jameswilliams.preso.*
import be.jameswilliams.preso.templates.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Scaling.fit
import com.badlogic.gdx.utils.Scaling.stretchY
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.app.use
import ktx.collections.gdxArrayOf
import ktx.collections.gdxListOf

// This is where slides live until they deserve
// their own file
class Slide0() : KtxScreen, Slide {
    var table = Table()
    var verticalGroup = VerticalGroup()
    val batch = SpriteBatch()

    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val title = headerLabel("Demystifying ConstraintLayout")
        val name = headerLabel("James Williams")
        val twitterId = headerLabel("@ecspike")
        val twitterIcon = iconLabel("\uf099")

        twitterIcon.setFontScale(0.5f)
        title.centerLabel()
        name.centerLabel()
        name.y -= name.height
        twitterId.centerLabel()
        twitterId.y -= (twitterId.height * 2)

        with(stage) {
            addActor(title)
            addActor(name)
            addActor(twitterIcon)
            addActor(twitterId)
        }

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        stage.act()
        stage.draw()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide1::class.java)
    }

    override fun backPressed() {
        // NO OP
    }
}

class Slide1 : KtxScreen, Slide {
    var table = Table()
    var verticalGroup = VerticalGroup()
    val batch = SpriteBatch()

    lateinit var udacityLogo: Texture

    var stage: Stage

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        udacityLogo = Texture(Gdx.files.internal("images/udacity-240-white.png"))
        val label = headerLabel("I work at Udacity. I'm Curriculum Lead for Android.")
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
            it.draw(udacityLogo, x, 0f)
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide2::class.java)
    }

    override fun backPressed() {
        Presentation.setScreen(Slide0::class.java)

    }
}

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

class Slide3 : HeadlineSlide("The Android View Rendering Cycle", Slide2::class.java, Slide4::class.java)

class Slide4 : BulletsSlide("The Android View Rendering Cycle", listOf("Measure Pass", "Layout Pass", "Drawing The Things", "")) {
    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide5::class.java)
        }
    }

    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide3::class.java)
        }
    }
}

class Slide5 : HeadlineSlide("Measuring A View", Slide4::class.java, Slide6::class.java)

class Slide6 : HeadlineSlide("onMeasure(widthMeasureSpec, heightMeasureSpec)", Slide5::class.java, Slide7::class.java)

class Slide7 : BulletsSlide("MeasureSpec", listOf("mode", "size", "")) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide6::class.java)
        }
    }

    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide8::class.java)
        }
    }
}

class Slide8 : BulletsSlide("MeasureSpec Modes", listOf("EXACTLY", "AT MOST", "UNSPECIFIED", "")) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide7::class.java)
        }
    }

    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide9::class.java)
        }
    }
}

class Slide9 : HeadlineSlide("onLayout(changed, L, T, R, B)", Slide8::class.java, Slide10::class.java)

class Slide10 : HeadlineSlide("Double Taxation", Slide9::class.java, Slide10A::class.java)

val slide10A_definition = "Android has to do multiple measure passes to\n" +
        " determine the final positions of a View"

class Slide10A : DefinitionSlide("What is double taxation?",
        slide10A_definition, Slide10::class.java, Slide11::class.java)

class Slide11 : HeadlineSlide("Why is this Important?", Slide10A::class.java, Slide12::class.java)

// TODO Perhaps add animation here
class Slide12 : HeadlineSlide("16ms", Slide11::class.java, Slide13::class.java)

class Slide13 : HeadlineSlide("What about Relative Layout?", Slide12::class.java, Slide14::class.java)

class Slide14 : HeadlineSlide("GridLayout", Slide13::class.java, Slide15::class.java)

class Slide15 : HeadlineSlide("ConstraintLayout", Slide14::class.java, Slide16::class.java)
class Slide16 : BulletsSlide("ConstraintLayout",
        listOf("Support library", "Available on API 9+ (Gingerbread)", "")) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide15::class.java)
        }
    }

    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide17::class.java)
        }
    }
}

class Slide17 : HeadlineSlide("What are constraints?", Slide16::class.java, Slide18::class.java)

val definition = "A restriction or limitation on the properties \nof a View that the layout attempts to respect"

class Slide18 : DefinitionSlide("What is a constraint?",
        definition, Slide17::class.java, Slide18A::class.java)

class Slide18A : KtxScreen, Slide {
    override fun backPressed() = Presentation.setScreen(Slide18::class.java)
    override fun nextPressed() = Presentation.setScreen(Slide19::class.java)

    val textures = gdxArrayOf<Texture>(
            Texture("images/device.png"),
            Texture("images/device2.png"),
            Texture("images/device3.png"),
            Texture("images/device4.png"))

    var animation: Animation<Texture>
    var batch = SpriteBatch()
    var currentTime = 0f

    init {
        animation = Animation<Texture>(0.333f, textures, Animation.PlayMode.LOOP)
        setSlideContent()
    }


    override fun setSlideContent() {}

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        currentTime += delta

        val currentFrame = animation.getKeyFrame(currentTime, true)
        batch.use {
            it.draw(currentFrame, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        batch.dispose()
    }
}

class Slide19 : BulletsSlide("The New Layout Editor", listOf("Design View", "Blueprint View", "")) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide18A::class.java)
        }
    }

    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide20::class.java)
        }
    }
}

class Slide20 : BackgroundImageSlide(Gdx.files.internal("images/design-view.png"),
        stretchY, Slide19::class.java, Slide21::class.java)

class Slide21 : BackgroundImageSlide(Gdx.files.internal("images/blueprint-view.png"),
        fit, Slide20::class.java, Slide22::class.java)

class Slide22 : BackgroundImageSlide(Gdx.files.internal("images/attributes-view.png"),
        fit, Slide21::class.java, Slide23::class.java)

// Constraint labels
class Slide22A():KtxScreen, Slide {
    override fun backPressed() {

    }
    override fun nextPressed() {

    }

    var stage: Stage
    var renderer = ShapeRenderer()
    val label = headerLabel("[BLUE]A[]")
    val labelTop = bodyLabel("[YELLOW]top[]")
    val labelBottom = bodyLabel("[YELLOW]bottom[]")
    val labelBaseline = bodyLabel("[YELLOW]baseline[]")
    var showAnnotations = gdxListOf(false, false, false)
    var showTopRectangle = false
    var showBottomRectangle = false
    var showBaselineRectangle = false

    var showUpToIndex: Int = 0

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val titleLabel = headerLabel("Relative Positioning Constraints")

        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true

        titleLabel.centerX()
        titleLabel.y = Gdx.graphics.height - titleLabel.height

        with(stage) {
            addActor(titleLabel)
            addActor(label)
            // add labels and hide them
            addActor(labelBaseline)
            addActor(labelBottom)
            addActor(labelTop)

            labelBottom.setFontScale(0.5f)
            labelTop.setFontScale(0.5f)
            labelBaseline.setFontScale(0.5f)

            //labelBaseline.isVisible = false
            //labelBottom.isVisible = false
            //labelTop.isVisible = false
        }

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        // Draw the things
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(0f, 1f, 0f, 1f)
            translate(Gdx.graphics.width/2f, Gdx.graphics.height/2f, 0f)
            rect(0f,0f, 500f, 250f)
            end()
        }

        // Position A label
        label.centerLabel()
        label.x += 250
        label.y += 125

        // Draw bottom rectangle
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(1f,0f,0f,0.5f)
            translate(Gdx.graphics.width/2f, Gdx.graphics.height/2f, 0f)
            rect(0f,0f, 500f, 30f)
            end()
        }
        labelBottom.x = Gdx.graphics.width/2f - labelBottom.width
        labelBottom.y = Gdx.graphics.height/2f - labelBottom.height/2

        // Draw top rectangle
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(1f,0f,0f,0.5f)
            translate(Gdx.graphics.width/2f, Gdx.graphics.height/2f + 220f, 0f)
            rect(0f,0f, 500f, 30f)
            end()
        }
        labelTop.x = Gdx.graphics.width/2f - labelTop.width - 40f
        labelTop.y = Gdx.graphics.height/2f - labelTop.height/2 +240f

        // Draw baseline
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(1f,0f,0f,0.5f)
            translate(Gdx.graphics.width/2f, Gdx.graphics.height/2f +61f, 0f)
            rect(0f,0f, 500f, 30f)
            end()
        }
        labelBaseline.x = Gdx.graphics.width/2f + labelBaseline.width + 180f
        labelBaseline.y = Gdx.graphics.height/2f - labelBaseline.height/2 +81f


        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        stage.dispose()
    }
}

// Removing a constraint
class Slide23 : HeadlineSlide("Clearing Constraints", Slide22::class.java, Slide24::class.java)

class Slide24 : BackgroundImageSlide(Gdx.files.internal("images/show-initial-constraints.png"),
        fit, Slide23::class.java, Slide25::class.java)

class Slide25 : BackgroundImageSlide(Gdx.files.internal("images/delete-connection.png"),
        fit, Slide24::class.java, Slide26::class.java)

class Slide26 : BackgroundImageSlide(Gdx.files.internal("images/delete-left-constraint.png"),
        fit, Slide25::class.java, Slide27::class.java)

// Adding a constraint
class Slide27 : HeadlineSlide("Adding Constraints", Slide26::class.java, Slide28::class.java)

class Slide28 : BackgroundImageSlide(Gdx.files.internal("images/add-constraint1.png"),
        fit, Slide27::class.java, Slide29::class.java)

class Slide29 : BackgroundImageSlide(Gdx.files.internal("images/add-constraint2.png"),
        fit, Slide28::class.java, Slide30::class.java)

class Slide30 : BackgroundImageSlide(Gdx.files.internal("images/add-constraint3.png"),
        fit, Slide29::class.java, Slide31::class.java)

class Slide31 : BackgroundImageSlide(Gdx.files.internal("images/add-constraint4.png"),
        fit, Slide30::class.java, Slide32::class.java)

class Slide32 : BackgroundImageSlide(Gdx.files.internal("images/add-constraint5.png"),
        fit, Slide31::class.java, Slide33::class.java)

class Slide33 : HeadlineSlide("Understanding The New Attributes View",
        Slide32::class.java, Slide34::class.java)

class Slide34 : BackgroundImageSlide(Gdx.files.internal("images/constraints1.png"),
        fit, Slide33::class.java, Slide35::class.java)

class Slide35 : BackgroundImageSlide(Gdx.files.internal("images/constraints2.png"),
        fit, Slide34::class.java, Slide36::class.java)

class Slide36 : BackgroundImageSlide(Gdx.files.internal("images/constraints3.png"),
        fit, Slide35::class.java, Slide37::class.java)

class Slide37 : BackgroundImageSlide(Gdx.files.internal("images/constraints4.png"),
        fit, Slide36::class.java, Slide38::class.java)

class Slide38 : HeadlineSlide("MATCH_PARENT", Slide37::class.java, Slide39::class.java)

class Slide39 : BackgroundImageSlide(Gdx.files.internal("images/margins.png"),
        fit, Slide38::class.java, Slide40::class.java)

class Slide40 : HeadlineSlide("Chains", Slide39::class.java, Slide41::class.java)

class Slide41 : BackgroundImageSlide(Gdx.files.internal("images/chain-blueprint.png"),
        fit, Slide40::class.java, Slide42::class.java)

class Slide42 : BackgroundImageSlide(Gdx.files.internal("images/cycle-chain.png"),
        fit, Slide41::class.java, Slide43::class.java)

class Slide43 : BackgroundImageSlide(Gdx.files.internal("images/chain-types.png"),
        fit, Slide42::class.java, Slide44::class.java)

class Slide44 : HeadlineSlide("How many Designers in the room?",
        Slide43::class.java, Slide45::class.java)

class Slide45 : HeadlineSlide("And Developers?", Slide44::class.java, Slide46::class.java)

class Slide46 : BulletsSlide("Virtual Helper Objects", listOf("Guidelines", "Barriers", "Groups", "")) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide45::class.java)
        }
    }

    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide47::class.java)
        }
    }
}

class Slide47 : HeadlineSlide("Guidelines", Slide46::class.java, Slide48::class.java)

class Slide48 : BackgroundImageSlide(Gdx.files.internal("images/guidelines-blueprint.png"),
        fit, Slide47::class.java, Slide49::class.java)

val codeString = Gdx.files.internal("code/guidelines.txt.out").readString()

class Slide49 : CodeSlide("Guidelines", codeString, Slide48::class.java, Slide50::class.java)

class Slide50 : HeadlineSlide("Barriers", Slide49::class.java, Slide51::class.java)

val slide51_code = Gdx.files.internal("code/barrier-code.txt.out").readString()

class Slide51 : CodeSlide("Barriers", slide51_code, Slide50::class.java, Slide52::class.java)

class Slide52 : HeadlineSlide("Any Questions?", Slide51::class.java, Slide53::class.java)

class Slide53 : HeadlineSlide("Groups", Slide52::class.java, Slide54::class.java)

val slide54_code = Gdx.files.internal("code/groups-code.txt.out").readString()

class Slide54 : CodeSlide("Groups", slide54_code, Slide53::class.java, Slide55::class.java)

val slide55_code = Gdx.files.internal("code/groups-detail.txt.out").readString()

class Slide55 : CodeSlide("Groups", slide55_code, Slide54::class.java, Slide56::class.java)

class Slide56 : HeadlineSlide("Placeholders", Slide55::class.java, Slide57::class.java)
class Slide57 : HeadlineSlide("Any Questions?", Slide56::class.java, Slide58::class.java)
class Slide58 : HeadlineSlide("Any Questions?", Slide57::class.java, Slide59::class.java)


class Slide59 : HeadlineSlide("Circular Positioning", Slide58::class.java, Slide60::class.java)

class Slide60 : KtxScreen, Slide {

    var stage: Stage
    var shapeRenderer = ShapeRenderer()
    var degrees = 0f


    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val label = headerLabel("Circular Positioning")
        label.centerLabel()
        stage.addActor(label)
    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        shapeRenderer.setAutoShapeType(true)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(Color.ORANGE)
        shapeRenderer.identity()
        // Place circle in center
        shapeRenderer.translate(Gdx.graphics.width/2f, Gdx.graphics.height / 2f, 0f)
        shapeRenderer.circle(0f, 0f, 50f)
        shapeRenderer.end()

        // Draw earth
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.identity()
        degrees += (delta * 30f)

        if (degrees > 360f)
            degrees = 0f

        shapeRenderer.translate(Gdx.graphics.width/2f, Gdx.graphics.height / 2f, 0f)
        shapeRenderer.rotate(0f,0f, 1f, degrees)

        shapeRenderer.setColor(Color.CYAN)
        shapeRenderer.circle(250f, 0f, 10f)
        shapeRenderer.end()

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        shapeRenderer.dispose()
    }

    override fun backPressed() {
        Presentation.setScreen(Slide59::class.java)
    }
    override fun nextPressed() {
        Presentation.setScreen(Slide60::class.java)
    }


}
class Slide61 : CodeSlide("Circular Positioning",Gdx.files.internal("code/circular-code.txt.out").readString(),Slide60::class.java, null)

class SlideXX : HeadlineSlide("Any Questions?")