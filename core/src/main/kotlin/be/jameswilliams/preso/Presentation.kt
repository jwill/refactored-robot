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


object Presentation : KtxGame<KtxScreen>(), KtxInputAdapter {
    lateinit var theme:Theme
    var i = 0
    var startTime:Long = 0
    var endTime:Long = 0

    override fun create() {
        Gdx.input.inputProcessor = this

        theme = DefaultTheme()
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
        addScreen(Slide39())

        addScreen(Slide40())
        addScreen(Slide41())
        addScreen(Slide42())
        addScreen(Slide43())
        addScreen(Slide44())

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

        addScreen(Slide18A())
        addScreen(Slide10A())


        addScreen(Slide13A())
        addScreen(Slide13B())
        addScreen(Slide22A())
        addScreen(Slide22B())
        addScreen(Slide22C())

        endTime = Date().time
        println((endTime- startTime)/1000f)

        setScreen<Slide22A>()
    }

    override fun keyDown(keycode: Int): Boolean {
        var slide = currentScreen as Slide
        if (keycode == Input.Keys.RIGHT || keycode == 93) {
            slide.nextPressed()
        } else if (keycode == Input.Keys.LEFT || keycode == 92) {
            slide.backPressed()
        } else if (keycode == Input.Keys.SPACE) {
            saveScreenShot(i++)
        }

       return true
    }

    fun saveScreenShot(index:Int) {
        val pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.backBufferWidth, Gdx.graphics.backBufferHeight, true)

        val pixmap = Pixmap(Gdx.graphics.backBufferWidth, Gdx.graphics.backBufferHeight, Pixmap.Format.RGBA8888)
        BufferUtils.copy(pixels, 0, pixmap.pixels, pixels.size)
        PixmapIO.writePNG(Gdx.files.external("Downloads/screens/output${index}.png"), pixmap)
        pixmap.dispose()
    }
}