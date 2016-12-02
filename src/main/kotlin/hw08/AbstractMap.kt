package hw08

import java.util.LinkedList

abstract class AbstractMap<Key, Value> : Iterable<Pair<Key, Value>> where Key : Comparable<Key> {
    abstract public fun insert(key: Key, value: Value)
    abstract public fun delete(key: Key)
    abstract public fun search(key: Key): Value?
    abstract public fun toList(): LinkedList<Pair<Key, Value>>
    abstract public fun union(map: AbstractMap<Key, Value>): AbstractMap<Key, Value>
    abstract public fun intersection(map: AbstractMap<Key, Value>): AbstractMap<Key, Value>
}