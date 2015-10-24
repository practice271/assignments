package hw6

import javafx.animation.FadeTransition
import javafx.animation.Timeline
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.*
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.util.Duration
import java.util.*

class UI: Application() {
    private val game = TicTacToe()
    private val root = StackPane()
    private val field = Group()
    private val text = Text()
    private val fade = FadeTransition(Duration.millis(1000.0), text)
    private val pulse = FadeTransition(Duration.millis(1000.0), text)
    private val crosses: MutableList<Shape> = LinkedList()
    private val circles: MutableList<Shape> = LinkedList()
    private val lattice: MutableList<Shape> = LinkedList()
    private val R = Rectangle(0.0, 0.0, 300.0, 300.0)

    override fun start(primaryStage: Stage) {

        field.setOnMouseClicked { e -> clickField(e) }
        root.children.add(field)
        root.children.add(text)
        addLattice()
        setupStyle()

        primaryStage.title = "TicTacToe"
        primaryStage.scene = Scene(root, 300.0, 300.0)
        primaryStage.show()

        update()
    }

    private fun setupStyle() {
        text.font = Font(text.font.family, 70.0)
        text.fill = Color.rgb(100, 170, 200)
        text.stroke = Color.WHITE
        text.strokeWidth = 1.5
        text.isMouseTransparent = true

        fade.fromValue = 1.0
        fade.toValue = 0.0

        pulse.fromValue = 1.0
        pulse.toValue = 0.5
        pulse.cycleCount = Timeline.INDEFINITE
        pulse.isAutoReverse = true
    }

    private fun clickField(e: MouseEvent) {
        val j = (e.x / 100.0).toInt()
        val i = (e.y / 100.0).toInt()
        val res = game.move(i, j)
        if (res)
            when (game[i, j]) {
                Player.CROSS -> addCross(i, j)
                else -> addCircle(i, j)
            }
        update()
    }

    private fun update() {
        updateStyle()
        showWhich()
    }

    private fun addLattice() {
        R.fill = Color.WHITE
        field.children.add(R)

        for (i in 1..2) {
            val La = Line(i * 100.0, 0.0, i * 100.0, 300.0)
            val Lb = Line(0.0, i * 100.0, 300.0, i * 100.0)

            setStyle(La)
            setStyle(Lb)

            lattice.add(La)
            lattice.add(Lb)

            field.children.add(La)
            field.children.add(Lb)
        }
    }

    private fun addCircle(i: Int, j: Int) {
        val x = j * 100.0 + 50.0
        val y = i * 100.0 + 50.0
        val a = 30.0

        val c = Circle(x, y, a)
        setStyle(c)
        circles.add(c)
        field.children.add(c)
    }

    private fun addCross(i: Int, j: Int) {
        val x = j * 100.0 + 20.0
        val y = i * 100.0 + 20.0
        val a = 60.0

        val La = Line(x, y, x + a, y + a)
        val Lb = Line(x, y + a, x + a, y)

        setStyle(La)
        setStyle(Lb)

        crosses.add(La)
        crosses.add(Lb)
        field.children.add(La)
        field.children.add(Lb)
    }

    private fun setStyle(x: Shape) {
        x.fill = Color.TRANSPARENT
        x.stroke = Color.GRAY
        x.strokeWidth = 4.0
        x.strokeLineCap = StrokeLineCap.ROUND
    }

    private fun updateStyle() {
        val baseColor = if (game.run) Color.GRAY
                        else Color.WHITE

        val playerColor = if (game.run) baseColor.darker()
                          else baseColor.brighter()

        val oppositeColor = if (game.run) baseColor.brighter()
                            else baseColor.darker()

        fun colorFor(p: Player) = if (game.next == p) playerColor
                                  else oppositeColor

        val crossColor = colorFor(Player.CROSS)
        val circleColor = colorFor(Player.CIRCLE)

        crosses.forEach { x -> x.stroke = crossColor }
        circles.forEach { x -> x.stroke = circleColor }

        if (!game.run) {
            lattice.forEach { x -> x.stroke = Color.WHITE }
            R.fill = Color.GRAY
        }
    }

    private fun showWhich() {
        fade.stop()
        when {
            game.run -> {
                when (game.next) {
                    Player.CROSS -> text.text = "X MOVE"
                    else -> text.text = "O MOVE"
                }
                fade.play()
            }
            else -> {
                when (game.winner) {
                    Player.CROSS -> text.text = "X WIN"
                    Player.CIRCLE -> text.text = "O WIN"
                    Player.EMPTY -> text.text = "DRAW"
                }
                pulse.play()
            }
        }
    }
}