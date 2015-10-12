package patterns.decorator

/**
 * Created by Alexander Chebykin
 */

public abstract class Window {
    abstract fun draw()
}

// Extension of a simple Window without any scrollbars
class SimpleWindow : Window() {
    override fun draw() {
        print("Window")
    }
}

// abstract decorator class - note that it implements Window
abstract class WindowDecorator(protected var windowToBeDecorated: Window // the Window being decorated
) : Window() {
    override fun draw() {
        windowToBeDecorated.draw() //Delegation
    }

}
// The first concrete decorator which adds vertical scrollbar functionality
class VerticalScrollBarDecorator(windowToBeDecorated: Window) : WindowDecorator(windowToBeDecorated) {

    override fun draw() {
        super.draw()
        drawVerticalScrollBar()
    }

    private fun drawVerticalScrollBar() {
        print(", with vertical scrolling")
    }

}

// The second concrete decorator which adds horizontal scrollbar functionality
class HorizontalScrollBarDecorator(windowToBeDecorated: Window) : WindowDecorator(windowToBeDecorated) {

    override fun draw() {
        super.draw()
        drawHorizontalScrollBar()
    }

    private fun drawHorizontalScrollBar() {
        print(", with horizontal scrolling")
    }

}
public fun main(args : Array<String>) {
    val simpleWindow = SimpleWindow()
    val decoratedWindow = HorizontalScrollBarDecorator(VerticalScrollBarDecorator(simpleWindow))
    simpleWindow.draw()
    println()
    decoratedWindow.draw()
}