package hw08

/**
 * Created by Mikhail on 07.11.2015.
 */
import java.util.*

class HashTable(private val size: Int): Set {
    private var table = Array(size, { ArrayList<Int>()})
    private fun hashFun(value : Int) = value.hashCode() mod size

    override fun iterator(): Iterator<Int> {return HTIterator()}

    private inner class HTIterator() : Iterator<Int> {
        private var currentPos = 0
        private var visited = 0
        override fun hasNext(): Boolean = currentPos != size

        override fun next(): Int {
            while (table[currentPos].isEmpty()){
                currentPos++
            }
            val temp = table[currentPos]
            if (visited >= temp.size - 1) {
                currentPos++
                visited = 0
                return temp[temp.size - 1]
            }
            visited++
            return temp[visited - 1]
        }
    }

    override public fun insert(x: Int) {
        table[hashFun(x)].add(x)
    }

    override public fun delete(x: Int) {
        table[hashFun(x)].remove(x)
    }

    override public fun search(x: Int): Boolean {
        return table[hashFun(x)].contains(x)
    }

    override public fun toList(): List<Int> {
        var list = listOf<Int>()
        table.forEach { list += it }
        return list
    }

    override public fun union(set: Set): HashTable {
        var table = HashTable(size)
        for (i in this.toList()) table.insert(i)
        for (i in set.toList()){
            if (!this.search(i)) table.insert(i)
        }
        return table
    }

    override public fun intersection(set: Set): HashTable {
        var table = HashTable(size)
        for (i in set.toList()) {
            if (this.search(i)) table.insert(i)
        }
        return table
    }

    public fun print() {
        println("Key:  value ")
        for (i in 0..size - 1) {
            print("$i  :  ")
            for (j in 0..table[i].size() - 1)
                print(table[i].get(j).toString() + "  ")
            println()
        }
    }
}