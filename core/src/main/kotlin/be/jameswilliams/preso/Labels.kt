package be.jameswilliams.preso

import com.badlogic.gdx.graphics.Color.*
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.rafaskoberg.gdx.typinglabel.TypingLabel

/**
 * Created by James Williams on 1/8/2018.
 */
fun iconLabel(text:String) =  Label(text, Label.LabelStyle(Presentation.theme.iconFont, WHITE))
fun headerLabel(text:String, isTypingLabel: Boolean = false) :Label {
    if (isTypingLabel)
        return TypingLabel(text, Label.LabelStyle(Presentation.theme.headerFont, WHITE))
    else
        return Label(text, Label.LabelStyle(Presentation.theme.headerFont, Presentation.theme.blueHeader))
}
fun codeLabel(text:String) =  Label(text, Label.LabelStyle(Presentation.theme.codeFont, WHITE))
fun bodyLabel(text:String) =  Label(text, Label.LabelStyle(Presentation.theme.bodyFont, WHITE))
fun bodyTypingLabel(text:String) =  TypingLabel(text, Label.LabelStyle(Presentation.theme.bodyFont, WHITE))



