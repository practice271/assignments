/*
 * Homework 8 (03.11.2015)
 * HashTable implementation
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw08

import java.util.*

/** Implementation of hash table. */
public class HashTable<T : Comparable<T>>(val size : Int) : Set<T>() {

    private var table = Array(size, { LinkedList<T>() })

    /** Iterator for hash table. */
    public override fun iterator() : MutableIterator<T> {
        return object : MutableIterator<T> {
            var index1 = 0
            var index2 = 0

            public override fun hasNext() : Boolean {
                return (index1 != size)
            }

            public override fun next() : T {
                while (table[index1].size == 0) index1++
                val temp = table[index1]
                if (index2 >= temp.size - 1) {
                    index1++
                    index2 = 0
                    return temp[temp.size - 1]
                }
                index2++
                return temp[index2 - 1]
            }

            public override fun remove() {}
        }
    }

    /** Inserts element in hash table. */
    public override fun insert(elem : T) : HashTable<T> {
        val hashCode = elem.hashCode() % size
        table[hashCode].add(elem)
        return this
    }

    /** Removes element from hash table. */
    public override fun remove(elem : T) : HashTable<T> {
        val hashCode  = elem.hashCode() % size
        val cellList = table[hashCode]
        if (cellList.contains(elem)) cellList.remove(elem)
        return this
    }

    /** Returns true if hash table contains given element. */
    public override fun contains(elem : T) : Boolean {
        val hashCode = elem.hashCode() % size
        return table[hashCode].contains(elem)
    }

    /** Converts hash table to list. */
    public fun toList() : List<T> {
        var list = LinkedList<T>()
        for (i in table) list.addAll(i)
        return list
    }

    /** Returns hash table which is union of given hash tables. */
    public override fun unite(set : Set<T>) : HashTable<T> {
        var result = HashTable<T>(size)
        for (i in this.toList()) result = result.insert(i)
        for (i in (set as HashTable).toList())
            if (!this.contains(i)) result = result.insert(i)
        return result
    }

    /** Returns hash table which is intersection of given hash tables. */
    public override fun intersect(set : Set<T>) : HashTable<T> {
        var result = HashTable<T>(size)
        for (i in (set as HashTable).toList())
            if (this.contains(i)) result = result.insert(i)
        return result
    }
}