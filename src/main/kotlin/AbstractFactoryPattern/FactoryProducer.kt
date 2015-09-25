package AbstractFactoryPattern

/**
 * Created by Mikhail on 18.09.2015.
 */
public object FactoryProducer {
    public fun getFactory(choice: String): AbstractFactory? {
        if (choice.equals("SHAPE")) {return ShapeFactory()
        }
        else if (choice.equals("COLOR")) {return ColorFactory()
        }
        return null
    }
}