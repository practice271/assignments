package hw08

import java.util.*

public class HashTable(public val arrsize: Int): ISet() {

    public val table = Array(arrsize, { LinkedList<Int>() })

    override fun isEmpty(): Boolean = toList() == emptyList<Int>()

    override fun toList(): List<Int> {
        var res = emptyList<Int>()
        table.forEach { res += it }
        return res
    }

    override fun insert(k: Int): Unit {
        table[k % arrsize].add(k)
    }

    override fun delete(k: Int): Unit {
        val reqList = table[k % arrsize]
        if(reqList.indexOf(k) != -1)
            reqList.remove(k)
    }


    override public fun intersection(s: ISet): ISet {
        val resht = HashTable(arrsize)
        if(isEmpty() || s.isEmpty()) return resht
        toList().forEach {
            if(s.contains(it)) resht.insert(it)
        }
        return resht
    }

    override public fun union(s: ISet): ISet {
        val resht: ISet = this
        if(resht.isEmpty()) return s
        if(s.isEmpty()) return resht
        s.toList().forEach {
            if(!resht.contains(it)) resht.insert(it)
        }
        return resht
    }

    override public fun iterator(): Iterator<Int> = if(isEmpty()) EmptyIterator() else HTIterator()


    private class EmptyIterator(): Iterator<Int> {
        override fun hasNext(): Boolean = false
        override fun next(): Int { throw NoSuchElementException() }
    }

    private inner class HTIterator(): Iterator<Int> {
        private var listInTable = 0
        private var elmInList   = 0

        override fun hasNext(): Boolean = listInTable < arrsize
        override fun next(): Int {
            while(table[listInTable].isEmpty()) listInTable++
            val tmp = table[listInTable]
            if(elmInList >= tmp.size - 1) {
                if(listInTable < arrsize-1) listInTable++
                elmInList = 0
                return tmp[tmp.size - 1]
            }
            return tmp[++elmInList - 1]
        }
    }

}