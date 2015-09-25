package AbstractFactoryPattern

/**
 * Created by Mikhail on 18.09.2015.
 */
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
