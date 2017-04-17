package hw08

/**
 * Created by Mikhail on 07.11.2015.
 */

interface Set : Iterable<Int> {
    public fun insert(value: Int)
    public fun delete(value: Int)
    public fun search(value: Int): Boolean
    public fun union(set: Set): Set
    public fun intersection(set: Set): Set
    public fun toList(): List<Int>
}