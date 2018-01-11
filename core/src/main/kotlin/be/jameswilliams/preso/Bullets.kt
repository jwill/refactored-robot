package be.jameswilliams.preso

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Align
import com.rafaskoberg.gdx.typinglabel.TypingLabel

class Bullets(val table: VerticalGroup, bullets: List<String>, fontScale:Float = 1f) {
    // Create TypingLabels from text
    private val texts:List<HorizontalGroup>
    var showUpToIndex: Int = -1

    init {
        texts = bullets.map {
            val horizontalGroup = HorizontalGroup()
            // TODO make this a constant
            val bullet = iconLabel("\uf111  ")
            val label = TypingLabel(it, Label.LabelStyle(Presentation.theme.bodyFont, Color.WHITE))
            bullet.setFontScale(fontScale/4)
            label.setFontScale(fontScale)

            //horizontalGroup.addActor(bullet)
            horizontalGroup.addActor(label)
            horizontalGroup.padTop(32f)
            horizontalGroup.padBottom(32f)
            horizontalGroup
        }
        println(texts.size)
    }

    fun showNextBulletCallback(index:Int) {
        for (i in 0..showUpToIndex) {
            val it = texts[i]
            if (i < showUpToIndex) {
                table.addActor(it)
            }
        }
    }

    fun showNext(): Boolean {
        if (showUpToIndex < texts.size - 1) {
            showUpToIndex++
            showNextBulletCallback(showUpToIndex)
            return true
        } else
            return false
    }
    fun goBack():Boolean {
        println(showUpToIndex)
        if (showUpToIndex > 0) {
            if (table.children.size > 0) {
                showUpToIndex--
                val child = table.children.last()
                table.removeActor(child)
            }
            return true
        } else
            return false
    }
}
