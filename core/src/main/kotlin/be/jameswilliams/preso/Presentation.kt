package be.jameswilliams.preso

import be.jameswilliams.preso.slides.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.PixmapIO
import com.badlogic.gdx.utils.BufferUtils
import com.badlogic.gdx.utils.ScreenUtils
import ktx.app.KtxGame
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import java.util.*
import com.badlogic.gdx.InputMultiplexer


object Presentation : KtxGame<KtxScreen>(), KtxInputAdapter {
    lateinit var theme:ScreencastTheme
    var i = 0
    var startTime:Long = 0
    var endTime:Long = 0

    // We want both our base application and individual stages to be able to respond to input
    //
    val multiplexer = InputMultiplexer()

    override fun create() {
        println(Gdx.graphics.density)

        // This class is our core input processor but other objects might also subscribe to
        // input triggers as well
        multiplexer.addProcessor(this)
        Gdx.input.inputProcessor = multiplexer

        theme = ScreencastTheme()
        startTime = Date().time


        addScreen(Slide0())
        addScreen(Slide1())
        addScreen(Slide2())
        addScreen(Slide3())

        addScreen(Slide5())
        addScreen(Slide6())
        addScreen(Slide7())
        addScreen(Slide8())

        addScreen(Slide10())
        addScreen(Slide11())
        addScreen(Slide12())
        addScreen(Slide13())
        addScreen(Slide14())

        addScreen(Slide15())
        addScreen(Slide16())
        addScreen(Slide17())
        addScreen(Slide18())
        addScreen(Slide19())

        addScreen(Slide20())
        addScreen(Slide21())
        addScreen(Slide22())
        addScreen(Slide23())
        addScreen(Slide24())

        addScreen(Slide25())
        addScreen(Slide26())
        addScreen(Slide27())
        addScreen(Slide28())
        addScreen(Slide29())

        addScreen(Slide30())
        addScreen(Slide31())
        addScreen(Slide32())
        addScreen(Slide33())
        addScreen(Slide34())

        addScreen(Slide35())
        addScreen(Slide36())
        addScreen(Slide37())
        addScreen(Slide38())

        addScreen(Slide40())
        addScreen(Slide41())
        addScreen(Slide43())

        addScreen(Slide45())
        addScreen(Slide46())
        addScreen(Slide47())
        addScreen(Slide48())
        addScreen(Slide49())

        addScreen(Slide50())
        addScreen(Slide51())
        addScreen(Slide52())
        addScreen(Slide53())
        addScreen(Slide54())

        addScreen(Slide55())
        addScreen(Slide56())
        addScreen(Slide57())
        addScreen(Slide59())

        addScreen(Slide60())
        addScreen(Slide61())
        addScreen(Slide62())
        addScreen(EndSlide())

        addScreen(Slide18A())
        addScreen(Slide10A())


        addScreen(Slide13A())
        addScreen(Slide13B())
        addScreen(Slide13C())
        addScreen(Slide22A())
        addScreen(Slide22B())
        addScreen(Slide22C())
        addScreen(Slide54A())
        addScreen(Slide40A())

        addScreen(Slide47A())
        addScreen(Slide53A())

        addScreen(SlideTest())

        setScreen<Slide41>()
    }

    override fun dispose() {
        super.dispose()
    }

    override fun keyDown(keycode: Int): Boolean {
        var slide = currentScreen as Slide
        when (keycode) {
            Input.Keys.RIGHT, 93 -> slide.nextPressed()
            Input.Keys.LEFT, 92 -> slide.backPressed()
            Input.Keys.SPACE -> saveScreenShot(++i)
            Input.Keys.Q -> System.exit(0)
        }

       return true
    }

    fun saveScreenShot(index:Int) {
        val pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.backBufferWidth, Gdx.graphics.backBufferHeight, true)

        val pixmap = Pixmap(Gdx.graphics.backBufferWidth, Gdx.graphics.backBufferHeight, Pixmap.Format.RGB8888)
        BufferUtils.copy(pixels, 0, pixmap.pixels, pixels.size)
        PixmapIO.writePNG(Gdx.files.external("Downloads/screens/output$index.png"), pixmap)
        pixmap.dispose()
    }
}