package hw04
import java.util.*

abstract class Set {
    abstract public fun search(value: Int): Boolean
    abstract public fun insert(value: Int): Unit
    abstract public fun delete(value: Int): Unit
    abstract public fun toList(): ArrayList <Int>
    abstract public fun union(set: Set ): Set
    abstract public fun insersection(set: Set): Set
}