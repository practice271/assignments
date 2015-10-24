package patterns

public abstract class Window{
    abstract fun draw()
}

class This_Window: Window(){
    override fun draw(){
        println("This is window")
    }
}

abstract class Decorator(var win: Window): Window(){
    override fun draw(){
        win.draw()
    }
}

class BorderDecorator (win: Window, width: Int): Decorator(win){
    private val width = width

    private fun DrawBorder(width: Int){
        println("This is window with border width $width")
    }
    override fun draw(){
        DrawBorder(width)
    }
}

class ShadowDecorator (win:Window): Decorator(win){
    private fun MakeShadow(){
        println("*with shadows*")
    }
    override fun draw(){
        win.draw()
        MakeShadow()
    }
}

class ScrollDecorator (win: Window): Decorator(win){
    private fun MakeScroll(){
        println("*with scroll*")
    }
    override  fun draw(){
        win.draw()
        MakeScroll()
    }
}

/**fun main (args : Array<String>){
    val win = This_Window()
    val decWin = ScrollDecorator(ShadowDecorator(BorderDecorator(win,5)))
    println("///")
    win.draw()
    println("///")
    decWin.draw()
}*/