package be.jameswilliams.preso

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

object Debugger {
    var x = 0f
    var y = 0f
}

// Helper functions to draw things in the Attributes view re-creation
object AttributeBuilder {
    var defaultConstraints = arrayOf<ConstraintType>(
            ConstraintType.WRAP_CONTENT,
            ConstraintType.WRAP_CONTENT,
            ConstraintType.WRAP_CONTENT,
            ConstraintType.CONSTRAINT)

    var shapeRenderer: ShapeRenderer = ShapeRenderer()

    enum class ConstraintType {
        WRAP_CONTENT,
        EXACT_SIZE,
        CONSTRAINT
    }

    enum class Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    // constraints  - list of constraint types for left, right, top, bottom

    fun drawAttributeView(location: Vector2, constraints: Array<ConstraintType> = defaultConstraints) {
        val wrapContentDimens = Vector2(80f, 40f)

        val sideLength = 600f
        drawOuterBox(Vector2(0f+location.x, 0f+location.y), sideLength, 10f)
        // draw left component

        when (constraints[0]) {
            ConstraintType.WRAP_CONTENT -> {
                drawWrapContent(Vector2(90f+location.x, 260f+location.y), dimens = wrapContentDimens, offset = 60f, direction = Direction.LEFT)
            }
            ConstraintType.EXACT_SIZE -> {
                drawPipe(Vector2(50f+location.x, 260f+location.y), Vector2(150f, 75f), 5f)
            }
            ConstraintType.CONSTRAINT -> {
                drawSquigglyPipe(Vector2(50f+location.x, 260f+location.y), Vector2(175f, 75f), 5f)
            }
        }
        // right
        when (constraints[1]) {
            ConstraintType.WRAP_CONTENT -> {
                // had to adjust up the y position
                drawWrapContent(Vector2(390f+location.x, 335f+location.y), dimens = wrapContentDimens, offset = 60f, direction = Direction.RIGHT)
            }
            ConstraintType.EXACT_SIZE -> {
                drawPipe(Vector2(400f+location.x, 260f+location.y), Vector2(150f, 75f), 5f)
            }
            ConstraintType.CONSTRAINT -> {
                drawSquigglyPipe(Vector2(375f+location.x, 260f+location.y), Vector2(175f, 75f), 5f)
            }
        }
        // top
        when (constraints[2]) {
            ConstraintType.WRAP_CONTENT -> {
                drawWrapContent(Vector2(260f+location.x, 390f+location.y), dimens = wrapContentDimens, offset = 60f, direction = Direction.TOP)
            }
            ConstraintType.EXACT_SIZE -> {
                drawPipe(Vector2(340f+location.x, 400f+location.y), Vector2(150f, 75f), 5f, rotation = 90f)
            }
            ConstraintType.CONSTRAINT -> {
                drawSquigglyPipe(Vector2(340f+location.x, 400f+location.y), Vector2(150f, 75f), 5f, rotation = 90f)
            }
        }
        // bottom
        when (constraints[3]) {
            ConstraintType.WRAP_CONTENT -> {
                drawWrapContent(Vector2(340f, 90f), dimens = wrapContentDimens, offset = 60f, direction = Direction.BOTTOM)
            }
            ConstraintType.EXACT_SIZE -> {
                drawPipe(Vector2(340f, 50f), Vector2(150f, 75f), 5f, rotation = 90f)

            }
            ConstraintType.CONSTRAINT -> {
                drawSquigglyPipe(Vector2(340f+location.x, 50f+location.y), Vector2(150f, 75f), 5f, rotation = 90f)
            }
        }
    }

    fun drawWrapContent(location: Vector2, dimens: Vector2, offset: Float, direction: Direction = Direction.TOP, color: Color = Color.WHITE) {
        var location2 = Vector2()
        var location3 = Vector2()
        var rotationAngle = 0f

        when (direction) {
            Direction.TOP -> {
                location2 = Vector2(location.x, location.y + offset)
                location3 = Vector2(location.x, location2.y + offset)
            }
            Direction.BOTTOM -> {
                location2 = Vector2(location.x, location.y + offset)
                location3 = Vector2(location.x, location2.y + offset)
                rotationAngle = 180f
            }
            Direction.LEFT -> {
                location2 = Vector2(location.x + offset, location.y)
                location3 = Vector2(location2.x + offset, location2.y)
                rotationAngle = 90f
            }
            Direction.RIGHT -> {
                location2 = Vector2(location.x + offset, location.y)
                location3 = Vector2(location2.x + offset, location2.y)
                rotationAngle = 270f
            }
        }

        drawCaret(location, dimens, rotation = rotationAngle, color = color)
        drawCaret(location2, dimens, rotation = rotationAngle, color = color)
        drawCaret(location3, dimens, rotation = rotationAngle, color = color)
    }

    fun drawDottedLine(location: Vector2, width: Float, gapSize: Float, lineWidth: Float = 5f, rotation: Float = 0f, color: Color = Color.WHITE) {
        var currentX = 0f
        val interval = gapSize * 3
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(color)

            translate(location.x, location.y, 0f)
            rotate(0f, 0f, 1f, rotation)

            while (currentX < width) {
                rectLine(currentX, 0f, currentX + interval, 0f, lineWidth)
                currentX += interval + gapSize
            }

            identity()
            end()
        }
    }

    fun drawSquigglyPipe(location: Vector2, dimens: Vector2, lineWidth: Float = 5f,
                         rotation: Float = 0f, color: Color = Color.WHITE) {
        val tenPercentX = dimens.x / 10f
        val halfY = dimens.y / 2f
        val quarterPercentY = dimens.y / 4f
        val thirdLineWidth = lineWidth / 3f

        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(color)
            translate(location.x, location.y, 0f)
            rotate(0f, 0f, 1f, rotation)
            // draw end caps
            rectLine(0f, 0f, 0f, dimens.y, lineWidth)
            rectLine(dimens.x, 0f, dimens.x, dimens.y, lineWidth)

            // draw start of center bar
            rectLine(0f, halfY, tenPercentX, halfY, thirdLineWidth)

            // draw squiggles
            var currentX = tenPercentX

            var interval = dimens.y / 5f // 20% high about the center

            while (currentX < tenPercentX * 9f) {
                // draw line up
                rectLine(currentX, halfY, currentX, halfY + quarterPercentY, thirdLineWidth)
                rectLine(currentX, halfY + quarterPercentY, currentX + interval, halfY - quarterPercentY, thirdLineWidth)
                rectLine(currentX + interval, halfY - quarterPercentY, currentX + interval, halfY, thirdLineWidth)
                currentX += interval
            }

            // draw end of center bar
            rectLine(9 * tenPercentX, halfY, dimens.x, halfY, thirdLineWidth)

            identity()
            end()
        }
    }

    fun drawPipe(location: Vector2, dimens: Vector2, lineWidth: Float = 5f, rotation:Float = 0f, color: Color = Color.WHITE) {
        val halfY = dimens.y / 2f
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(color)
            translate(location.x, location.y, 0f)
            rotate(0f, 0f, 1f, rotation)
            // draw end caps
            rectLine(0f, 0f, 0f, dimens.y, 8f)
            rectLine(dimens.x, 0f, dimens.x, dimens.y, 8f)

            // draw center bar
            rectLine(0f, halfY, dimens.x, halfY, lineWidth)

            identity()
            end()
        }
    }

    fun drawCaret(location: Vector2, dimens: Vector2, lineWidth: Float = 10f, rotation: Float = 0f, color: Color = Color.WHITE) {
        val halfX = dimens.x / 2f
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            // reset to identity matrix
            identity()
            setColor(color)
            translate(location.x, location.y, 0f)
            rotate(0f, 0f, 1f, rotation)

            rectLine(0f, dimens.y, halfX, 0f, lineWidth)
            // small adjustment to make ends overlap
            rectLine(halfX - (0.18f * halfX), 0f, dimens.x, dimens.y, lineWidth)
            identity()
            end()
        }
    }

    fun drawOuterBox(location: Vector2, sideLength: Float, lineWidth: Float, color: Color = Color.WHITE, debug:Boolean = false) {
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            // reset to identity matrix
            identity()
            setColor(color)
            translate(location.x, location.y, 0f)
            // draw from 0,0 to 0,h
            rectLine(0f, 0f, 0f, sideLength, lineWidth)
            // draw from 0,h to w,h
            rectLine(0f, sideLength, sideLength, sideLength, lineWidth)
            // draw from w,h to w,0
            rectLine(sideLength, sideLength, sideLength, 0f, lineWidth)
            // draw from w,0 to 0,0
            rectLine(sideLength, 0f, 0f, 0f, lineWidth)

            if (debug) {

                for (i in 50 until sideLength.toInt() step 50) {
                    var v = i.toFloat()
                    // vertical lines
                    setColor(Color.LIME)
                    rectLine(v, 0f, v, sideLength, 1f)
                    // horizontal lines
                    setColor(Color.RED)
                    rectLine(0f, v, sideLength, v, 1f)
                }
            }

            identity()
            end()
        }
    }

    fun drawConstraintHandle(location: Vector2, radius:Float = 25f, lineWidth: Float = 8f, color: Color = Color.BLUE, color2: Color = Color.WHITE) {
        with(shapeRenderer) {
            begin(ShapeRenderer.ShapeType.Filled)
            identity()
            translate(location.x, location.y, 0f)
            // draw circle
            setColor(color)
            circle(radius,radius,radius, 32)

            // draw crossbar
            setColor(color2)
            rectLine(radius, 0f, radius, 2*radius, lineWidth)
            rectLine(0f, radius, 2*radius, radius, lineWidth)
            identity()
            end()
        }
    }
}