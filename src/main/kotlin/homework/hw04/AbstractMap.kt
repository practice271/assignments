// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw04

import java.util.*

/**
 * Abstract map interface.
 * @param K The type of map's keys (must be comparable).
 * @param V The type of map's values.
 */
interface AbstractMap<K, V> where K : Comparable<K> {

    /**
     * Inserts a new entry ([newKey], [newValue]) into the map.
     */
    fun insert(newKey : K, newValue : V)

    /**
     * Returns the value of the entry with [key], if found, or null otherwise.
     */
    fun search(key : K) : (V?)

    /**
     * Removes the entry with [key], if possible.
     */
    fun remove(key : K)

    /**
     * Returns whether this map contains an entry with [key]
     */
    operator fun contains(key : K) : Boolean = (search(key) != null)

    /**
     * Applies [f] to each of the map entries.
     * The order depends on AbstractMap implementation and cannot be guaranteed or predicted.
     */
    fun forEach(f : (K, V) -> Unit)

    /**
     * Creates and returns a new empty instance of the same class as this object's.
     */
    fun newClassInstance() : AbstractMap<K, V>

    /**
     * Returns a new map constructed by uniting this map with [anotherMap].
     * In case of key collision the data from this map is used.
     */
    fun uniteWith(anotherMap : AbstractMap<K, V>) : AbstractMap<K, V> {
        val newMap = newClassInstance()
        forEach { k, v -> newMap.insert(k, v) }
        anotherMap.forEach { k, v ->
            if (!this.contains(k)) {
                newMap.insert(k, v)
            }
        }
        return newMap
    }

    /**
     * Returns a new map constructed by intersecting this map with [anotherMap].
     * In case of key collision the data from this map is used.
     */
    fun intersectWith(anotherMap : AbstractMap<K, V>) : AbstractMap<K, V> {
        val newMap = newClassInstance()
        forEach { k, v ->
            if (anotherMap.contains(k)) {
                newMap.insert(k, v)
            }
        }
        return newMap
    }

    /**
     * Converts an AbstractMap to list and sorts it.
     */
    fun toSortedList() : List<Pair<K, V>> {
        // <K : Comparable<K>, V> AbstractMap<K, V>.
        val list = LinkedList<Pair<K, V>>()
        this.forEach({ k, v -> list.add(k to v) })
        return list.sortedBy { it.first }
    }
}