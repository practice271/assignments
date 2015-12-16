package patterns.Bridge

interface Drawer {
    fun drawCircle(x:Int, y:Int, radius:Int)
}

class SmallCircleDrawer:Drawer {
    public override fun drawCircle(x:Int, y:Int, radius:Int) {
        println("Small circle center = " + x + "," + y + " radius = " + radius * radiusMultiplier)
    }
    companion object {
        val radiusMultiplier = 0.25
    }
}
class LargeCircleDrawer:Drawer {
    val radiusMultiplier = 10

    public override fun drawCircle(x:Int, y:Int, radius:Int) {
        println("Large circle center = " + x + "," + y + " radius = " + radius * radiusMultiplier)
    }
}

abstract class Shape protected constructor(drawer:Drawer) {
    protected var drawer:Drawer
    init{
        this.drawer = drawer
    }

    abstract fun draw()
    abstract fun enlargeRadius(multiplier:Int)
}

class Circle(var x:Int,var y:Int,var radius:Int, drawer:Drawer):Shape(drawer) {
    public override fun draw() {
        drawer.drawCircle(x, y, radius)
    }
    public override fun enlargeRadius(multiplier:Int) {
        radius *= multiplier
    }
}

fun main(argv:Array<String>){
    val shapes = arrayOf<Shape>(
            Circle(5, 10, 10, LargeCircleDrawer()),
            Circle(20, 30, 100, SmallCircleDrawer())
    )
    for (shape in shapes)
    {
        shape.draw()
    }
    // Output :
    // Large circle center = 5,10 radius = 100
    // Small circle center = 20,30 radius = 25.0
}