package patterns

/**
 * Created by Mikhail on 18.09.2015.
 */

public interface Shape {
    public fun draw()
}

public class Square: Shape {
    override fun draw() {
        println("Inside Square::draw() method.")
    }
}

public class Rectangle: Shape {
    override fun draw() {
        println("Inside Rectangle::draw() method.")
    }
}

public class Circle: Shape {
    override fun draw() {
        println("Inside Circle::draw() method.")
    }
}

public interface Color {
    public fun fill()
}

public class Blue: Color {
    override fun fill() {
        println("Inside Blue::fill() method.")
    }
}

public class Green: Color {
    override fun fill() {
        println("Inside Green::fill() method.")
    }
}

public class Red: Color {
    override fun fill() {
        println("Inside Red::fill() method.")
    }
}

public abstract class AbstractFactory {
    abstract fun getColor(color: String): Color?
    abstract fun getShape(shape: String): Shape?
}

public class ShapeFactory: AbstractFactory() {
    override fun getShape(shapeType: String): Shape? {
        if (shapeType == null) { return null }
        when (shapeType) {
            "CIRCLE"    -> return Circle()
            "RECTANGLE" -> return Rectangle()
            "SQUARE"    -> return Square()
        }
        return null
    }
    override fun getColor(color: String): Color? {
        return null
    }
}

public class ColorFactory: AbstractFactory() {
    override fun getShape(shapeType: String): Shape? {
        return null
    }
    override fun getColor(color: String): Color? {
        if (color == null) {return null}
        when (color) {
            "RED"   -> return Red()
            "GREEN" -> return Green()
            "BLUE"  -> return Blue()
        }
        return null
    }
}

public object FactoryProducer {
    public fun getFactory(choice: String): AbstractFactory? {
        when (choice) {
            "SHAPE" -> return ShapeFactory()
            "COLOR" -> return ColorFactory()
        }
        return null
    }
}

public fun main(args: Array<String>) {
      val shapeFactory = FactoryProducer.getFactory("SHAPE")
      val shape1 = shapeFactory?.getShape("CIRCLE")
      shape1?.draw()
      val shape2 = shapeFactory?.getShape("RECTANGLE")
      shape2?.draw()
      val shape3 = shapeFactory?.getShape("SQUARE")
      shape3?.draw()
      val colorFactory = FactoryProducer.getFactory("COLOR")
      val color1 = colorFactory?.getColor("RED")
      color1?.fill()
      val color2 = colorFactory?.getColor("GREEN")
      color2?.fill()
      val color3 = colorFactory?.getColor("BLUE")
      color3?.fill()
 }
