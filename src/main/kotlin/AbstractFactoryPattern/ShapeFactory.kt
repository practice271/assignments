package AbstractFactoryPattern

/**
 * Created by Mikhail on 18.09.2015.
 */
public class ShapeFactory : AbstractFactory() {
    override fun getShape(shapeType: String): Shape? {
        if (shapeType == null) { return null }
        if (shapeType.equals("CIRCLE")) {return Circle()
        }
        else if (shapeType.equals("RECTANGLE")) {return Rectangle()
        }
        else if (shapeType.equals("SQUARE")) {return Square()
        }
        return null
    }
    override fun getColor(color: String): Color? {
        return null
    }
}
