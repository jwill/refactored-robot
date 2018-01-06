package be.jameswilliams.preso

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import com.badlogic.gdx.utils.Align
import com.rafaskoberg.gdx.typinglabel.TypingLabel

class Bullets(val table: VerticalGroup, bullets: List<String>, theme:Theme, fontScale:Float = 1f) {
    // Create TypingLabels from text
    private val texts:List<TypingLabel>
    init {
        texts = bullets.map {
            val label = TypingLabel(it, Label.LabelStyle(theme.bodyFont, Color.WHITE))
            label.setFontScale(fontScale)
            label
        }

    }

    fun showNextBulletCallback(index:Int) {
        for (i in 0..showUpToIndex) {
            val it = texts[i]
            if (i < showUpToIndex) {
                table.addActor(it)
            }
        }
    }

    var showUpToIndex: Int = -1
    fun showNext(): Boolean {
        if (showUpToIndex < texts.size - 1) {
            showUpToIndex ++
            showNextBulletCallback(showUpToIndex)
            return true
        } else
            return false
    }
    fun goBack():Boolean {
        if (showUpToIndex > -1) {
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
