package hw04

/**
 * Created by Mikhail on 30.09.2015.
 */
interface Set {
    public fun insert(value: Int)
    public fun delete(value: Int)
    public fun search(value: Int): Boolean
    public fun union(set: Set): Set
    public fun intersection(set: Set): Set
    public fun toList(): List<Int>
}
