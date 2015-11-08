package hw08

abstract public class ISet: Iterable<Int> {
    abstract public fun insert(k: Int)
    abstract public fun delete(k: Int)
    abstract public fun toList (): List<Int>
    abstract public fun isEmpty(): Boolean
    abstract public fun union       (s: ISet): ISet
    abstract public fun intersection(s: ISet): ISet

    // We don't really need this function now, thanks to Iterators
    //abstract public fun search(k: Int):        Boolean
}
