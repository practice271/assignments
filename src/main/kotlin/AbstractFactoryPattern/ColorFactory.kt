package AbstractFactoryPattern

/**
 * Created by Mikhail on 18.09.2015.
 */
public class ColorFactory : AbstractFactory() {
    override fun getShape(shapeType: String): Shape? {
        return null
    }
    override fun getColor(color: String): Color? {
        if (color == null) {return null}
        if (color.equals("RED")) {return Red()
        }
        else if (color.equals("GREEN")) {return Green()
        }
        else if (color.equals("BLUE")) {return Blue()
        }
        return null
    }
}