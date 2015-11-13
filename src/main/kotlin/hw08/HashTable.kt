package hw08

import java.util.*

public class HashTable(): Set() {
    private val arraySize = 1000
    val table = Array(arraySize, {ArrayList<Int>()})

    private inner class HashTableIterator(): Iterator<Int> {
        var arrayInd = 0
        var listInd = 0

        override fun hasNext(): Boolean {
            if ((arrayInd == table.size - 1) && (listInd == table[arrayInd].size)) return false
            while (arrayInd < table.size - 1) {
                if (listInd == table[arrayInd].size) { arrayInd++; listInd = 0 }
                else return true
            }
            return false
        }
        override fun next(): kotlin.Int {
            if (hasNext()) {
                return table[arrayInd][listInd++]
            } else throw NoSuchElementException()
        }
    }

    override fun iterator(): Iterator<Int> = HashTableIterator()

    private fun hashFun(value: Int) = value.hashCode() % arraySize

    override public fun search (value: Int): Boolean {
        return table[hashFun(value)].contains(value)
    }

    override public fun insert(value: Int): Unit {
        val arrayInd = hashFun(value)
        table[arrayInd].add(value)
    }

    override public fun delete(value: Int): Unit {
        table[hashFun(value)].remove(value)
    }

    override public fun toList(): ArrayList<Int> {
        var result = ArrayList<Int>()
        table.forEach {
            result.addAll(it)
        }
        return result
    }

    override public fun union(set: Set): Set {
        var result = HashTable()
        val firstList = this.toList()
        val secondList = set.toList()
        for (elem in firstList) result.insert(elem)
        for (elem in secondList) if (!result.search(elem)) result.insert(elem)
        return result
    }

    override public fun insersection(set: Set): Set {
        val result = HashTable()
        val firstList = this.toList()
        val secondList = set.toList()
        for (elem in firstList) if (secondList.contains(elem)) result.insert(elem)
        return result
    }
}