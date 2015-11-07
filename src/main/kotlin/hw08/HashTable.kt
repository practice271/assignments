package hw08

import java.util.*

public class HashTable(): Set() {
    private val arraySize = 1000
    val table = Array(arraySize, {ArrayList<Int>()})

    private inner class HashTableIterator(): Iterator<Int> {
        var ind1 = 0
        var ind2 = 0

        override fun hasNext(): Boolean {
         if (table[ind1].isEmpty() || ind1 == arraySize) { return false}
            else { return true }
        }

        override fun next(): Int {
            while (table[ind1].size == 0) ind1++
            var i = table[ind1].size
            if (ind2 >= i - 1) {ind1++; ind2 = 0; return table[ind1][i - 1]}
            ind2++
            return table[ind1][ind2 - 1]
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
        val arrayInd = hashFun(value)
        val x = table[arrayInd]
        val y = x.indexOf(value)
        if(y != -1) x.remove(y)
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