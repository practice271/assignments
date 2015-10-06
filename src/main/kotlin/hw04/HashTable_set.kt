package hw04
import java.util.*

public class HashTable_set (val size: Int): SetInterface{
    private var table = Array(this.size, { i -> ArrayList<Int>() })
    private fun hash(key: Int) = key.hashCode() mod this.size

    public override fun insert(key: Int): SetInterface {
        val hashCode = hash(key)
        table[hashCode].add(key)
        return this
    }

    public override fun remove(key: Int): SetInterface{
        val hashCode = hash(key)
        table[hashCode].remove(key)
        return this
    }

    public override fun search(key: Int): Boolean{
        val hashCode = hash(key)
        return  table[hashCode].contains(key)
    }
    public override fun toList(): List<Int> {
        var list = LinkedList<Int>()
        for (i in table){list.addAll(i)}
            return list
    }

    public override fun union(set: SetInterface): SetInterface{
        var res  = HashTable_set(size) as SetInterface
        val set1 = toList()
        val set2 = (set as HashTable_set).toList()
        for (i in set2) res = res.insert(i)
        for (i in set1) res = res.insert(i)
        return res
    }

    public override fun intersection(set: SetInterface): SetInterface{
        var res = HashTable_set(size) as SetInterface
        val set1 = toList()
        val set2 = (set as HashTable_set).toList()
        for (i in set2) if (set1.contains(i)){
            res = res.insert(i)
        }
        return res
    }
}

fun main (args : Array<String>) {
    val table1 = HashTable_set(4)
    val table2 = HashTable_set(2)
    table1.insert(1)
    table1.insert(5)
    table1.insert(4)
    table1.insert(9)
    table2.insert(5)
    table2.insert(10)
    val res1 = table1.union(table2).toList().delRep()
    val res2 = table1.intersection(table2).toList()
    println("${res1}")
    println("${res2}")


}

