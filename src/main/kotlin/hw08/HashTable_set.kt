package hw08
import java.util.*

public class HashTable_set (val size: Int): SetInterface{
    private var table = Array(this.size, { i -> ArrayList<Int>() })
    private fun hash(key: Int) = key.hashCode() mod this.size

    private inner class HashTable_Iterator() : Iterator<Int> {
        var col = 0
        var str = 0
        public override fun hasNext() : Boolean {
            return (str < size)
        }
        public override fun next() : Int {
            if (hasNext()) {
                while (table[str].isEmpty()) str++
                val temp = table[str]
                if (col >= temp.size - 1) {
                    str++
                    col = 0
                    return temp[temp.size - 1]
                }
                col++
                return temp[col - 1]
            }
            else throw NoSuchElementException()
        }
    }
    override fun iterator() : Iterator<Int> = HashTable_Iterator()

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
        val set2 = set.toList()
        for (i in set2) res = res.insert(i)
        for (i in set1) res = res.insert(i)
        return res
    }

    public override fun intersection(set: SetInterface): SetInterface{
        var res = HashTable_set(size) as SetInterface
        val set1 = toList()
        val set2 = set.toList()
        for (i in set2) if (set1.contains(i)){
            res = res.insert(i)
        }
        return res
    }
}
fun main (args : Array<String>) {
    val table = HashTable_set(2)
    table.insert(3)
    table.insert(6)
    table.insert(2)
}

