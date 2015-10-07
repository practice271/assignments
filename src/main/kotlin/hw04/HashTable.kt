package hw04
import java.util.*

public class HashTable(): Set() {
    private val arraySize = 1000
    val table = Array(arraySize, {ArrayList<Int>()})

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
        for (elem in secondList) if (result.search(elem) == false) result.insert(elem)
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