package hw04

/**
 * Created by Mikhail on 03.10.2015.
 */
import java.util.ArrayList

class HashTable(private val size: Int): Set {
    private var table = Array(size, {i -> ArrayList<Int>()})
    private fun hashFun(value : Int) = value.hashCode() mod size

    override public fun insert(x: Int) {
        table[hashFun(x)].add(x)
    }

    override public fun delete(x: Int) {
        table[hashFun(x)].remove(x: Any)
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
