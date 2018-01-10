package be.jameswilliams.preso.slides

import be.jameswilliams.preso.Presentation
import be.jameswilliams.preso.templates.BackgroundImageSlide
import be.jameswilliams.preso.templates.BulletsSlide
import be.jameswilliams.preso.templates.HeadlineSlide
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.Scaling.*

// This is where slides live until they deserve
// their own file

class Slide3 : HeadlineSlide("The Android View Rendering Cycle", Slide2::class.java, Slide4::class.java)

class Slide4 : BulletsSlide("[RED]The Android View Rendering Cycle[]", listOf("Measure Pass", "Layout Pass", "Drawing The Things", "")) {
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

class Slide6 : HeadlineSlide("onMeasure(widthMeasureSpec,heightMeasureSpec)", Slide5::class.java, Slide7::class.java)

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

class Slide10 : HeadlineSlide("Double Taxation", Slide9::class.java, Slide11::class.java)

class Slide11 : HeadlineSlide("Why is this Important?", Slide10::class.java, Slide12::class.java)

// TODO Perhaps add animation here
class Slide12 : HeadlineSlide("16ms", Slide11::class.java, Slide13::class.java)

class Slide13 : HeadlineSlide("What about Relative Layout?", Slide12::class.java, Slide14::class.java)

class Slide14 : HeadlineSlide("GridLayout", Slide13::class.java, Slide15::class.java)

class Slide15 : HeadlineSlide("ConstraintLayout", Slide14::class.java, Slide16::class.java)

class Slide16 : HeadlineSlide("ConstraintLayout is a support library", Slide15::class.java, Slide17::class.java)

class Slide17 : HeadlineSlide("Supported back to API 9 (Gingerbread)", Slide16::class.java, Slide18::class.java)

// TODO Insert animation from script
class Slide18 : HeadlineSlide("What are constraints?", Slide17::class.java, Slide19::class.java)

class Slide19 : BulletsSlide("The New Layout Editor", listOf("Design View", "Blueprint View", "")) {
    override fun backPressed() {
        var result = super.bullets.goBack()
        if (result == false) {
            Presentation.setScreen(Slide18::class.java)
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
        fit, Slide19::class.java, Slide21::class.java)

class Slide21 : BackgroundImageSlide(Gdx.files.internal("images/blueprint-view.png"),
        fit, Slide20::class.java, Slide22::class.java)

class Slide22 : BackgroundImageSlide(Gdx.files.internal("images/attributes-view.png"),
        fit, Slide21::class.java, Slide23::class.java)

// Removing a constraint
class Slide23 : HeadlineSlide("Clearing Constraints", Slide22::class.java, Slide24::class.java)

// TODO could compress this into an action sequence
class Slide24 : BackgroundImageSlide(Gdx.files.internal("images/show-initial-constraints.png"),
        fit, Slide23::class.java, Slide25::class.java)

class Slide25 : BackgroundImageSlide(Gdx.files.internal("images/delete-connection.png"),
        fit, Slide24::class.java, Slide26::class.java)

class Slide26 : BackgroundImageSlide(Gdx.files.internal("images/delete-left-constraint.png"),
        fit, Slide25::class.java, Slide27::class.java)

// Adding a constraint
class Slide27 : HeadlineSlide("Adding Constraints", Slide26::class.java, Slide28::class.java)

// TODO could compress this into an action sequence
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

// TODO Replace this later?
class Slide47 : HeadlineSlide("Guidelines", Slide46::class.java, Slide48::class.java)
class Slide48 : BackgroundImageSlide(Gdx.files.internal("images/guidelines-blueprint.png"),
        fit, Slide47::class.java, Slide49::class.java)

// TODO Replace with code slide?
class Slide49 : BackgroundImageSlide(Gdx.files.internal("images/guidelines-code.png"),
        fit, Slide48::class.java, Slide50::class.java)

class Slide50 : HeadlineSlide("Barriers", Slide49::class.java, Slide51::class.java)

class Slide51 : HeadlineSlide("Any Questions?", Slide50::class.java, Slide52::class.java)
class Slide52 : HeadlineSlide("Any Questions?", Slide51::class.java, Slide53::class.java)

class Slide53 : HeadlineSlide("Groups", Slide52::class.java, Slide54::class.java)
class Slide54 : HeadlineSlide("Any Questions?", Slide53::class.java, Slide55::class.java)
class Slide55 : HeadlineSlide("Any Questions?", Slide54::class.java, Slide56::class.java)

class Slide56 : HeadlineSlide("Placeholders", Slide55::class.java, Slide57::class.java)
class Slide57 : HeadlineSlide("Any Questions?", Slide56::class.java, Slide58::class.java)
class Slide58 : HeadlineSlide("Any Questions?", Slide57::class.java, Slide59::class.java)


class Slide59 : HeadlineSlide("Circular Positioning", Slide58::class.java, Slide60::class.java)
class Slide60 : HeadlineSlide("Any Questions?")



class SlideXX : HeadlineSlide("Any Questions?")