package AbstractFactoryPattern

/**
 * Created by Mikhail on 18.09.2015.
 */
public abstract class AbstractFactory {
    abstract fun getColor(color: String): Color?
    abstract fun getShape(shape: String): Shape?
}