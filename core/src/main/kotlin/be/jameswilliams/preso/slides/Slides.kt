package be.jameswilliams.preso.slides

import be.jameswilliams.preso.*
import be.jameswilliams.preso.templates.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.Scaling.fit
import com.badlogic.gdx.utils.Scaling.stretchY
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rafaskoberg.gdx.typinglabel.TypingConfig
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.app.use
import ktx.collections.gdxArrayOf
import ktx.scene2d.slider
import ktx.scene2d.table

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
        val texture = Texture(Gdx.files.internal("images/james-emoji-circular.png"))
        val image = Image(texture)
        val name = bodyLabel("James Williams")
        val twitterId = bodyLabel("@ecspike")
        val twitterIcon = iconLabel("\uf099")

        println(Presentation.theme.blueHeader.toString())

        twitterIcon.setFontScale(0.5f)
        title.centerLabel()
        name.centerLabel()
        name.y -= name.height * 2
        twitterId.centerLabel()
        twitterId.y -= (twitterId.height * 3)

        image.setSize(150f, 150f)
        image.x = name.x - 200f
        image.y = name.y - 80f

        with(stage) {
            addActor(image)
            addActor(title)
            addActor(name)
            //addActor(twitterIcon)
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
    lateinit var label: Label

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        udacityLogo = Texture(Gdx.files.internal("images/udacity-240-white.jpg"))
        label = headerLabel("I work at Udacity. I'm Curriculum Lead for Android.")
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
            it.draw(udacityLogo, x, (Gdx.graphics.height / 2 - (2 * label.height + 50)))
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

class Slide2 : HeadlineSlide("The Android View Rendering Cycle", Slide1::class.java, Slide3::class.java)

class Slide3 : BulletsSlide("The Android View Rendering Cycle", listOf("Measure Pass", "Layout Pass", "Drawing The Things", "")) {
    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide5::class.java)
        }
    }

    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide2::class.java)
        }
    }
}


class Slide5 : HeadlineSlide("Measuring A View", Slide3::class.java, Slide6::class.java)

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
            Presentation.setScreen(Slide10::class.java)
        }
    }
}


class Slide10 : HeadlineSlide("Double Taxation", Slide8::class.java, Slide10A::class.java)

val slide10A_definition = "Android has to do multiple measure passes to" +
        " determine the final positions of a View"

class Slide10A : DefinitionSlide("What is double taxation?",
        slide10A_definition, Slide10::class.java, Slide11::class.java)

class Slide11 : HeadlineSlide("Why is this Important?", Slide10A::class.java, Slide12::class.java)

class Slide12 : HeadlineSlide("16ms*", Slide11::class.java, Slide13::class.java)

class Slide13 : HeadlineSlide("What layouts are prone to double taxation?", Slide12::class.java, Slide13A::class.java)

//Vertical LinearLayout
class Slide13A : KtxScreen, Slide {
    val renderer = ShapeRenderer()
    val batch = SpriteBatch()
    var stage: Stage
    val texture = Texture(Gdx.files.internal("images/device.png"))

    val startingWidth = 150f
    val endingWidth = 400f
    var currentWidth = startingWidth
    val boxHeight = 150f

    val windowWidth = Gdx.graphics.width
    val windowHeight = Gdx.graphics.height

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        val xTranslate = windowWidth / 2f - 200

        batch.use {
            it.draw(texture, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        }

        currentWidth += (delta * 100f)
        if (currentWidth >= endingWidth) {
            currentWidth = startingWidth
        }



        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(Color.LIGHT_GRAY)
            translate(xTranslate, windowHeight / 2f, 0f)
            rect(0f, 0f, currentWidth, boxHeight)
            end()
        }

        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(Color.LIGHT_GRAY)
            translate(xTranslate, windowHeight / 2f - boxHeight - 16f, 0f)
            rect(0f, 0f, currentWidth, boxHeight)
            end()
        }

        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(Color.RED)
            translate(xTranslate, windowHeight / 2f - boxHeight * 2 - 16f * 2, 0f)
            rect(0f, 0f, endingWidth, boxHeight)
            end()
        }


        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        renderer.dispose()
        batch.dispose()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide13B::class.java)
    }

    override fun backPressed() {
        Presentation.setScreen(Slide13::class.java)
    }
}

class Slide13B : KtxScreen, Slide {
    val renderer = ShapeRenderer()
    val batch = SpriteBatch()
    var stage: Stage
    val texture = Texture(Gdx.files.internal("images/device.png"))

    val staticWidth = 100f
    val startingWidth = 50f
    val endingWidth = 200f
    var currentWidth = startingWidth
    val boxHeight = 150f

    val windowWidth = Gdx.graphics.width
    val windowHeight = Gdx.graphics.height
    val xTranslate = windowWidth / 2f - 225

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        batch.use {
            it.draw(texture, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        }

        currentWidth += (delta * 50f)
        if (currentWidth >= endingWidth) {
            currentWidth = startingWidth
        }

        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(Color.LIGHT_GRAY)
            translate(xTranslate, windowHeight / 2f, 0f)
            rect(0f, 0f, staticWidth, boxHeight)
            end()
        }

        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(1f, 0f, 0f, 1f)
            translate(xTranslate + 16 + staticWidth, windowHeight / 2f, 0f)
            rect(0f, 0f, currentWidth, boxHeight)
            end()
        }

        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(Color.LIGHT_GRAY)
            translate(xTranslate + 16 + staticWidth + currentWidth + 16, windowHeight / 2f, 0f)
            rect(0f, 0f, staticWidth, boxHeight)
            end()
        }




        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        renderer.dispose()
        batch.dispose()
    }

    override fun nextPressed() {
        Presentation.setScreen(Slide13C::class.java)
    }

    override fun backPressed() {
        Presentation.setScreen(Slide13A::class.java)
    }
}

class Slide13C : HeadlineSlide("RelativeLayout", Slide13B::class.java, Slide14::class.java)

// TODO Insert Relative and Linear combined example.

class Slide14 : HeadlineSlide("GridLayout", Slide13C::class.java, Slide15::class.java)

class Slide15 : HeadlineSlide("What is ConstraintLayout?", Slide14::class.java, Slide16::class.java)
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

val definition = "A restriction or limitation on the properties of a View that the layout attempts to respect"

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
        fit, Slide21::class.java, Slide22A::class.java)

// Constraint labels
class Slide22A() : KtxScreen, Slide {
    override fun backPressed() {
        if (showBaselineRectangle) {
            showBaselineRectangle = false
            labelBaseline.isVisible = false
        } else if (showBottomRectangle) {
            showBottomRectangle = false
            labelBottom.isVisible = false
        } else if (showTopRectangle) {
            showTopRectangle = false
            labelTop.isVisible = false
        } else {
            // Advance to previous slide
            Presentation.setScreen(Slide22::class.java)
        }
    }

    override fun nextPressed() {
        if (showTopRectangle == false) {
            showTopRectangle = true
            labelTop.isVisible = true
        } else if (showBottomRectangle == false) {
            showBottomRectangle = true
            labelBottom.isVisible = true
        } else if (showBaselineRectangle == false) {
            showBaselineRectangle = true
            labelBaseline.isVisible = true
        } else {
            // Advance to next slide
            Presentation.setScreen(Slide22B::class.java)
        }
    }

    val purpleHex = Presentation.theme.purpleDetail.toHex()

    var stage: Stage
    var renderer = ShapeRenderer()
    val label = headerLabel("[BLUE]A[]")
    val labelTop = bodyLabel("[${purpleHex}]top[]")
    val labelBottom = bodyLabel("[${purpleHex}]bottom[]")
    val labelBaseline = bodyLabel("[${purpleHex}]baseline[]")
    var showTopRectangle = false
    var showBottomRectangle = false
    var showBaselineRectangle = false

    val offset = 300f
    val halfWindowWidth = Gdx.graphics.width / 2f - offset
    val halfWindowHeight = Gdx.graphics.height / 2f
    val boxHeight = 250f
    val boxWidth = 500f


    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val titleLabel = headerLabel("Relative Positioning Constraints")

        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true

        titleLabel.centerX()
        titleLabel.y = Gdx.graphics.height - titleLabel.height - 16f

        // Position A label
        label.centerLabel()
        label.x = halfWindowWidth
        label.x += boxWidth / 2
        label.y += boxHeight / 2

        labelBottom.x = halfWindowWidth - labelBottom.width
        labelBottom.y = halfWindowHeight - labelBottom.height / 2

        labelTop.x = halfWindowWidth - labelTop.width - 40f
        labelTop.y = halfWindowHeight - labelTop.height / 2 + 240f

        labelBaseline.x = halfWindowWidth + labelBaseline.width + 250f
        labelBaseline.y = halfWindowHeight - labelBaseline.height / 2 + 81f

        //labelBottom.setFontScale(0.9f)
        //labelTop.setFontScale(0.9f)
        //labelBaseline.setFontScale(0.9f)

        labelBaseline.isVisible = false
        labelBottom.isVisible = false
        labelTop.isVisible = false

        with(stage) {
            addActor(titleLabel)
            addActor(label)
            // add labels and hide them
            addActor(labelBaseline)
            addActor(labelBottom)
            addActor(labelTop)
        }

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        // Draw the initial box
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(0f, 1f, 0f, 1f)
            translate(halfWindowWidth, halfWindowHeight, 0f)
            rect(0f, 0f, boxWidth, boxHeight)
            end()
        }


        if (showBottomRectangle) {
            // Draw bottom rectangle
            with(renderer) {
                begin(ShapeRenderer.ShapeType.Filled)
                identity()
                setColor(1f, 0f, 0f, 0.5f)
                translate(halfWindowWidth, halfWindowHeight, 0f)
                rect(0f, 0f, boxWidth, boxHeight / 8.3333f)
                end()
            }
        }

        if (showTopRectangle) {
            // Draw top rectangle
            with(renderer) {
                begin(ShapeRenderer.ShapeType.Filled)
                identity()
                setColor(1f, 0f, 0f, 0.5f)
                translate(halfWindowWidth, halfWindowHeight + 220f, 0f)
                rect(0f, 0f, 500f, 30f)
                end()
            }
        }

        if (showBaselineRectangle) {
            // Draw baseline
            with(renderer) {
                begin(ShapeRenderer.ShapeType.Filled)
                identity()
                setColor(1f, 0f, 0f, 0.5f)
                translate(halfWindowWidth, halfWindowHeight + 61f, 0f)
                rect(0f, 0f, boxWidth, boxHeight / 8.3333f)
                end()
            }
        }

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        stage.dispose()
        renderer.dispose()
    }
}

class Slide22B() : KtxScreen, Slide {
    override fun backPressed() {
        if (showRightRectangle == true) {
            showRightRectangle = false
            labelRight.isVisible = false
            labelEnd.isVisible = false
        } else if (showLeftRectangle == true) {
            showLeftRectangle = false
            labelLeft.isVisible = false
            labelStart.isVisible = false
        } else {
            // Advance to previous slide
            Presentation.setScreen(Slide22A::class.java)
        }
    }

    override fun nextPressed() {
        if (showLeftRectangle == false) {
            showLeftRectangle = true
            labelLeft.isVisible = true
            labelStart.isVisible = true
        } else if (showRightRectangle == false) {
            showRightRectangle = true
            labelRight.isVisible = true
            labelEnd.isVisible = true
        } else {
            // Advance to next slide
            Presentation.setScreen(Slide22C::class.java)
        }
    }
    val purpleHex = Presentation.theme.purpleDetail.toHex()
    var stage: Stage
    var renderer = ShapeRenderer()
    val label = headerLabel("[BLUE]A[]")
    val labelLeft = bodyLabel("[${purpleHex}]left[]")
    val labelStart = bodyLabel("[${purpleHex}]start[]")
    val labelRight = bodyLabel("[${purpleHex}]right[]")
    val labelEnd = bodyLabel("[${purpleHex}]end[]")
    var showLeftRectangle = false
    var showRightRectangle = false

    val offset = 300f
    val halfWindowWidth = Gdx.graphics.width / 2f - offset
    val halfWindowHeight = Gdx.graphics.height / 2f
    val boxHeight = 250f
    val boxWidth = 500f

    init {
        stage = Stage(ScreenViewport())
        setSlideContent()
    }


    override fun setSlideContent() {
        val titleLabel = headerLabel("Relative Positioning Constraints")

        TypingConfig.FORCE_COLOR_MARKUP_BY_DEFAULT = true

        titleLabel.centerX()
        titleLabel.y = Gdx.graphics.height - titleLabel.height - 16f

        // Position A label
        label.centerLabel()
        label.x = halfWindowWidth
        label.x += boxWidth / 2
        label.y += boxHeight / 2

        labelLeft.centerLabel()
        labelLeft.x = halfWindowWidth - labelLeft.width
        labelLeft.y = halfWindowHeight - labelLeft.height / 2

        labelStart.centerLabel()
        labelStart.x = halfWindowWidth - labelStart.width
        labelStart.y = halfWindowHeight - labelStart.height / 2 - 90f

        labelRight.centerLabel()
        labelRight.x = halfWindowWidth - labelRight.width + boxWidth + 180f
        labelRight.y = halfWindowHeight - labelRight.height / 2

        labelEnd.centerLabel()
        labelEnd.x = halfWindowWidth - labelEnd.width + boxWidth + 180f
        labelEnd.y = halfWindowHeight - labelEnd.height / 2 - 90f

        labelLeft.setFontScale(0.9f)
        labelRight.setFontScale(0.9f)
        labelStart.setFontScale(0.9f)
        labelEnd.setFontScale(0.9f)

        labelLeft.isVisible = false
        labelStart.isVisible = false
        labelEnd.isVisible = false
        labelRight.isVisible = false

        with(stage) {
            addActor(titleLabel)
            addActor(label)
            // add labels and hide them
            addActor(labelLeft)
            addActor(labelStart)
            addActor(labelEnd)
            addActor(labelRight)
        }

    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)

        // Draw the initial box
        with(renderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(0f, 1f, 0f, 1f)
            translate(halfWindowWidth, halfWindowHeight, 0f)
            rect(0f, 0f, boxWidth, boxHeight)
            end()
        }


        if (showLeftRectangle) {
            // Draw bottom rectangle
            with(renderer) {
                begin(ShapeRenderer.ShapeType.Filled)
                identity()
                setColor(1f, 0f, 0f, 0.5f)
                translate(halfWindowWidth, halfWindowHeight, 0f)
                rect(0f, 0f, boxWidth / (8.3333f * 2), boxHeight)
                end()
            }
        }

        if (showRightRectangle) {
            // Draw top rectangle
            with(renderer) {
                begin(ShapeRenderer.ShapeType.Filled)
                identity()
                setColor(1f, 0f, 0f, 0.5f)
                translate(halfWindowWidth + 470f, halfWindowHeight, 0f)
                rect(0f, 0f, boxWidth / (8.3333f * 2), boxHeight)
                end()
            }
        }

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        stage.dispose()
        renderer.dispose()
    }
}

class Slide22C() : BulletsSlide("Relative Positioning Constraints",
        listOf<String>(
                "layout_constraint[GREEN]SourceConstraint[]_to[RED]TargetConstraint[]Of",
                "layout_constraintStart_toEndOf",
                "layout_constraintRight_toRightOf",
                ""
        )) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide22B::class.java)
        }
    }

    override fun nextPressed() {
        var result = super.bullets.showNext()
        if (result == false) {
            Presentation.setScreen(Slide23::class.java)
        }
    }
}

// Removing a constraint
class Slide23 : HeadlineSlide("Clearing Constraints", Slide22C::class.java, Slide24::class.java)

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


class Slide34 : ConstraintSlide(AttributeBuilder.defaultConstraints, "wrap_content", "wrap_content",
        Slide33::class.java, Slide35::class.java) {
    override fun render(delta: Float) {
        super.render(delta)

        val rectX = uiSizeX * 0.4f
        val rectWidth = uiSizeX * 0.2f
        val rectHeight = uiSizeY * 0.05f
        val handleOffset = uiSizeX * 0.02f

        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            setColor(Color.PINK)
            // 400, 200
            rect(rectX, halfY, rectWidth, rectHeight)
            end()
        }
        // Constraint Handles
        AttributeBuilder.drawConstraintHandle(
                Vector2(rectX - handleOffset, halfY+handleOffset), radius = handleOffset, color2 = Color.BLUE)
        AttributeBuilder.drawConstraintHandle(
                Vector2(rectX+rectWidth-handleOffset, halfY + handleOffset), radius = handleOffset, color2 = Color.BLUE)

        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.8f, halfY + 3*handleOffset), radius = handleOffset, color2 = Color.BLUE)

        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.8f, halfY - handleOffset), radius = handleOffset, color2 = Color.BLUE)

    }
}

val constraints = arrayOf(AttributeBuilder.ConstraintType.WRAP_CONTENT,
        AttributeBuilder.ConstraintType.WRAP_CONTENT,
        AttributeBuilder.ConstraintType.EXACT_SIZE,
        AttributeBuilder.ConstraintType.EXACT_SIZE
)

class Slide35 : ConstraintSlide(constraints, "wrap_content", "19dp",
        Slide34::class.java, Slide36::class.java) {
    override fun render(delta: Float) {
        super.render(delta)

        val rectX = uiSizeX * 0.4f
        val rectWidth = uiSizeX * 0.2f
        val rectHeight = uiSizeY * 0.05f
        val handleOffset = uiSizeX * 0.02f

        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            setColor(Color.PINK)
            rect(rectX, halfY, rectWidth, rectHeight)
            end()
        }
        // Constraint Handles
//        AttributeBuilder.drawConstraintHandle(Vector2(380f, halfY + 20f), radius = 20f, color2 = Color.BLUE)
//        AttributeBuilder.drawConstraintHandle(Vector2(580f, halfY + 20f), radius = 20f, color2 = Color.BLUE)
//
//        AttributeBuilder.drawConstraintHandle(Vector2(480f, halfY + 60f), radius = 20f, color2 = Color.BLUE)
//        AttributeBuilder.drawConstraintHandle(Vector2(480f, halfY - 20f), radius = 20f, color2 = Color.BLUE)

        AttributeBuilder.drawConstraintHandle(
                Vector2(rectX - handleOffset, halfY+handleOffset), radius = handleOffset, color2 = Color.BLUE)
        AttributeBuilder.drawConstraintHandle(
                Vector2(rectX+rectWidth-handleOffset, halfY + handleOffset), radius = handleOffset, color2 = Color.BLUE)

        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.8f, halfY + 3*handleOffset), radius = handleOffset, color2 = Color.BLUE)

        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.8f, halfY - handleOffset), radius = handleOffset, color2 = Color.BLUE)
    }
}

class Slide36 : ConstraintSlide(constraints, "wrap_content", "19dp",
        Slide35::class.java, Slide37::class.java) {
    override fun render(delta: Float) {
        super.render(delta)

        val rectX = uiSizeX * 0.4f
        val rectWidth = uiSizeX * 0.2f
        val rectHeight = uiSizeY * 0.05f
        val handleOffset = uiSizeX * 0.02f

        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            setColor(Color.PINK)
            rect(rectX, halfY, rectWidth, rectHeight)
            end()
        }


        // Constraint Handles
        // middle top
        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.8f, halfY + 3*handleOffset), radius = handleOffset, color2 = Color.BLUE)
        // middle bottom
        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.8f, halfY - handleOffset), radius = handleOffset, color2 = Color.BLUE)

        // left
        AttributeBuilder.drawSquigglyPipe(
                Vector2(5f, halfY + handleOffset),
                Vector2(rectX - 5f, 2 * handleOffset), color = Color.YELLOW)

        //right
        AttributeBuilder.drawSquigglyPipe(
                Vector2(rectX + rectWidth, halfY + handleOffset),
                Vector2(rectX, 2 * handleOffset), color = Color.YELLOW)
    }
}

val slide37_constraints = arrayOf(
        AttributeBuilder.ConstraintType.CONSTRAINT,
        AttributeBuilder.ConstraintType.CONSTRAINT,
        AttributeBuilder.ConstraintType.EXACT_SIZE,
        AttributeBuilder.ConstraintType.EXACT_SIZE
)

class Slide37 : ConstraintSlide(slide37_constraints, "match_constraint", "19dp ",
        Slide36::class.java, Slide38::class.java) {
    override fun render(delta: Float) {
        super.render(delta)

        val rectX = uiSizeX *0.06f
        val rectWidth = uiSizeX * 0.87f
        val rectHeight = uiSizeY * 0.05f
        val handleOffset = uiSizeX * 0.02f
        val squigglyWidth = uiSizeX * 0.05f


        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            setColor(Color.PINK)
            rect(rectX, halfY, rectWidth, rectHeight)
            end()
        }

        // Constraint Handles
        // middle top
        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.5f, halfY + 3*handleOffset), radius = handleOffset, color2 = Color.BLUE)
        // middle bottom
        AttributeBuilder.drawConstraintHandle(
                Vector2((rectX+rectWidth)*0.5f, halfY - handleOffset), radius = handleOffset, color2 = Color.BLUE)

        // left
        AttributeBuilder.drawSquigglyPipe(
                Vector2(5f, halfY + handleOffset), Vector2(squigglyWidth, 2 * handleOffset), color = Color.YELLOW)
        // right
        AttributeBuilder.drawSquigglyPipe(
                Vector2((rectX+ rectWidth), halfY + handleOffset), Vector2(squigglyWidth*1.35f, 2 * handleOffset), color = Color.YELLOW)
    }
}


class Slide38 : HeadlineSlide("A word about MATCH_PARENT", Slide37::class.java, Slide40::class.java)


class Slide40 : HeadlineSlide("Chains", Slide38::class.java, Slide40A::class.java)

class Slide40A : DefinitionSlide("Chains",
        "Views linked together with bidirectional positional constraints. Can replace LinearLayouts in many cases.", Slide40::class.java, Slide41::class.java)

class Slide41 : ConstraintSlide(constraints, "wrap_content", "19dp",
        Slide40A::class.java, Slide43::class.java) {
    override fun render(delta: Float) {
        super.render(delta)

        val rectX = 105f
        val rectWidth = uiSizeX * 0.2f
        val rectHeight = uiSizeY * 0.05f
        val handleOffset = uiSizeX * 0.02f
        val squigglyWidth = uiSizeX * 0.05f

        //val rectWidth = 150f
        //val rectHeight = 80f

        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            setColor(Color.PINK)
            rect(rectX, halfY, rectWidth, rectHeight)

            rect(rectX+rectWidth+230f+20f, halfY, rectWidth, rectHeight)
            end()
        }


        ChainBuilder.makeChainLink(230f, 35f, Vector2(rectX+rectWidth, halfY+10f),color2 = uiBackgroundColor)

        // Constraint Handles
        //AttributeBuilder.drawConstraintHandle(Vector2(480f, halfY + 60f), radius = 20f, color2 = Color.BLUE)
        //AttributeBuilder.drawConstraintHandle(Vector2(480f, halfY - 20f), radius = 20f, color2 = Color.BLUE)


        AttributeBuilder.drawSquigglyPipe(Vector2(5f, halfY ), Vector2(100f, 4 * handleOffset), color = Color.YELLOW)
        AttributeBuilder.drawSquigglyPipe(Vector2(665f, halfY), Vector2(100f, 4 * handleOffset), color = Color.YELLOW)
    }
}


class Slide43 : BackgroundImageSlide(Gdx.files.internal("images/chain-types.png"),
        fit, Slide41::class.java, Slide45::class.java)


class Slide45 : HeadlineSlide("Virtual Helper Objects", Slide43::class.java, Slide46::class.java)

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

class Slide47 : HeadlineSlide("Guidelines", Slide46::class.java, Slide47A::class.java)

val slide47A_definition = "Allow multiple widgets to be aligned from a single virtual object"

class Slide47A : DefinitionSlide("Guidelines", slide47A_definition, Slide47::class.java, Slide48::class.java)

class Slide48 : BackgroundImageSlide(Gdx.files.internal("images/guidelines-blueprint.png"),
        fit, Slide47A::class.java, Slide49::class.java)

val codeString = Gdx.files.internal("code/guidelines.txt.out").readString()

class Slide49 : CodeSlide("Guidelines", codeString, Slide48::class.java, Slide50::class.java)

class Slide50 : HeadlineSlide("Barriers", Slide49::class.java, Slide51::class.java)

val slide51_definition = "Allows widgets to be aligned based on the one with the largest value"

class Slide51 : DefinitionSlide("Barriers", slide51_definition, Slide50::class.java, Slide52::class.java)


val slide52_code = Gdx.files.internal("code/barrier-code.txt.out").readString()

class Slide52 : CodeSlide("Barriers", slide52_code, Slide51::class.java, Slide53::class.java)

class Slide53 : HeadlineSlide("Groups", Slide52::class.java, Slide53A::class.java)

val slide53A_definition = "Control the visibility of a set of widgets."

class Slide53A : DefinitionSlide("Groups", slide53A_definition, Slide53::class.java, Slide54::class.java)

class Slide54 : BackgroundImageSlide(Gdx.files.internal("images/groups-example.png"), Scaling.fillY,
        Slide53A::class.java, Slide54A::class.java)

val slide54_code = Gdx.files.internal("code/groups-code.txt.out").readString()

class Slide54A : CodeSlide("Groups", slide54_code, Slide54::class.java, Slide55::class.java)


val slide55_code = Gdx.files.internal("code/groups-detail.txt.out").readString()

class Slide55 : CodeSlide("Groups", slide55_code, Slide54::class.java, Slide56::class.java)

class Slide56 : HeadlineSlide("Placeholders", Slide55::class.java, Slide57::class.java)
class Slide57 : HeadlineSlide("setContentId(...)", Slide56::class.java, Slide59::class.java)

class Slide59 : HeadlineSlide("Circular Positioning", Slide57::class.java, Slide60::class.java)

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
        shapeRenderer.translate(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f, 0f)
        shapeRenderer.circle(0f, 0f, 50f)
        shapeRenderer.end()

        // Draw earth
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.identity()
        degrees += (delta * 30f)

        if (degrees > 360f)
            degrees = 0f

        shapeRenderer.translate(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f, 0f)
        shapeRenderer.rotate(0f, 0f, 1f, degrees)

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
        Presentation.setScreen(Slide61::class.java)
    }


}

class Slide61 : CodeSlide("Circular Positioning", Gdx.files.internal("code/circular-code.txt.out").readString(), Slide60::class.java, Slide62::class.java)

class Slide62 : HeadlineSlide("Udacity is hiring!",Slide61::class.java, EndSlide::class.java)

class EndSlide : HeadlineSlide("Any Questions?", Slide62::class.java)

class SlideTest : KtxScreen, Slide {
    override fun setSlideContent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(delta: Float) {
        val bg = Presentation.theme.backgroundColor
        clearScreen(bg.r, bg.g, bg.b, bg.a)
    }
}