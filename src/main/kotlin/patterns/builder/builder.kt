// Example for pattern "Bilder"
// Alekseev Aleksei, group 271

package patterns.builder

/* "Product" */
public class House {
    public var foundation: String = ""
    public var wall: String = ""
    public var roof: String = ""
}

/* "Abstract Builder" */
public abstract class HouseBuilder() {
    public var house : House = House()
    public fun createNewHouse() {
        house = House()
    }
    public abstract fun buildFoundation()
    public abstract fun buildWall()
    public abstract fun buildRoof()
}

/* "ConcreteBuilder" */
internal class WoodenHouseBuilder : HouseBuilder() {
    override fun buildFoundation() {
        house.foundation = "pier"
    }
    override fun buildWall() {
        house.wall = "wooden"
    }
    override fun buildRoof() {
        house.roof = "slate"
    }
}

/* "ConcreteBuilder" */
internal class BrickHouseBuilder : HouseBuilder() {
    override fun buildFoundation() {
        house.foundation = "strip"
    }
    override fun buildWall() {
        house.wall = "brick"
    }
    override fun buildRoof() {
        house.roof = "tile"
    }
}

/* "Director" */
public class Director (internal var houseBuilder : HouseBuilder) {
    public fun constructHouse() {
        houseBuilder.createNewHouse()
        houseBuilder.buildFoundation()
        houseBuilder.buildWall()
        houseBuilder.buildRoof()
    }
}

/*
fun main(args : Array<String>) {
    var director = Director(WoodenHouseBuilder())
    director.constructHouse()
    var temp = director.houseBuilder.house
    var house = "foundation: " + temp.foundation + "\nwalls: " + temp.wall + "\nroof: " + temp.roof
    println("=========Wooden house=========")
    print(house)
    println()
    director = Director(BrickHouseBuilder())
    director.constructHouse()
    temp = director.houseBuilder.house
    house = "foundation: " + temp.foundation + "\nwalls: " + temp.wall + "\nroof: " + temp.roof
    println("=========Brick house=========")
    print(house)
}
*/