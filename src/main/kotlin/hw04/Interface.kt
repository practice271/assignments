package hw04

abstract public class SetInterface {
    abstract public fun insert(k: Int):                Unit
    abstract public fun delete(k: Int):                Unit
    abstract public fun search(k: Int):                Boolean
    abstract public fun union (s: SetInterface):       SetInterface
    abstract public fun intersection(s: SetInterface): SetInterface
    abstract public fun toList():                      List<Int>
    abstract public fun isEmpty():                     Boolean
}