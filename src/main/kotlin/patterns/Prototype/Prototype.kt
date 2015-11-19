/**
 * Example for prototype pattern
 *
 * Author: Mikhail Kita, group 271
 */

package patterns.Prototype

import java.util.*

abstract class Monster() : Cloneable {
    public abstract var pos : Pair<Float, Float>
    public abstract var name : String
    public abstract val type : String
    public var health = 1000

    public abstract fun attack()

    // this function also can be overridden in inherited classes
    public override fun clone() : Monster {
        return (super.clone() as Monster)
    }
}

public class GroundMonster() : Monster() {
    public override var pos  = Pair(0f, 0f)
    public override val type = "ground"
    public override var name = "Deathclaw"

    public override fun attack() {
        println("Ground monster '$name' at position $pos attacked you. He has $health HP")
    }

    public override fun clone() : GroundMonster {
        return (super.clone() as GroundMonster)
    }
}

public class AirMonster() : Monster() {
    public override var pos  = Pair(0f, 0f)
    public override val type = "air"
    public override var name = "Dragon"

    public override fun attack() {
        println("Air monster '$name' at position $pos attacked you. He has $health HP")
    }
}

public class WaterMonster() : Monster() {
    public override var pos  = Pair(0f, 0f)
    public override val type = "water"
    public override var name = "Drowner"

    public override fun attack() {
        println("Water monster '$name' at position $pos attacked you. He has $health HP")
    }
}

private class MonsterMaker() {
    private val existingMonsters = LinkedList<Monster>()

    public fun makeMonster(type : String) : Monster {
        var found = false

        for (m in existingMonsters) {
            if (m.type == type) {
                found = true
                val monster = m.clone()
                monster.health = 1000
                existingMonsters.add(monster)
                break
            }
        }
        if (!found) {
            when (type) {
                "ground" -> existingMonsters.add(GroundMonster())
                "air"    -> existingMonsters.add(AirMonster())
                "water"  -> existingMonsters.add(WaterMonster())
                else     -> throw Exception("Incorrect type of monster")
            }
        }
        return existingMonsters.last
    }
}

fun main (args : Array<String>) {
    val maker = MonsterMaker()

    val monster = maker.makeMonster("air")
    monster.attack()
    monster.health -= 150
    monster.pos = Pair(100f, 50f)

    val monster1 = maker.makeMonster("air")
    monster.attack()
    monster1.attack()
}