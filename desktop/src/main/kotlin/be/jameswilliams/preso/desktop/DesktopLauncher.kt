package be.jameswilliams.preso.desktop

import be.jameswilliams.preso.Presentation
import com.badlogic.gdx.Files
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object DesktopLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication() = LwjglApplication(Presentation(), defaultConfiguration)

    private val defaultConfiguration: LwjglApplicationConfiguration
        get() {
            val configuration = LwjglApplicationConfiguration()
            configuration.title = "Presentation"
            // TODO Later support fullscreen toggle
            configuration.width = 1920
            configuration.height = 1080

            //Set full screem
           /* System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
            configuration.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
            configuration.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
            */

            for (size in intArrayOf(128, 64, 32, 16)) {
                configuration.addIcon("libgdx$size.png", Files.FileType.Internal)
            }
            return configuration
        }

}