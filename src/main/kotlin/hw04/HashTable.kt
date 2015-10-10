package hw04

import java.util.LinkedList

public class HashTable(public val arrsize: Int): SetInterface() {

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
        val indOfElm = reqList.indexOf(k)
        if(indOfElm != -1)
            reqList.remove(indOfElm)
    }

    override fun search(k: Int): Boolean = table[k % arrsize].indexOf(k) != -1

    override fun union(s: SetInterface): SetInterface {
        val resht = HashTable(arrsize)
        if(isEmpty() || s.isEmpty()) return resht
        toList().forEach {
            if(s.search(it)) resht.insert(it)
        }
        return resht
    }

    override fun intersection(s: SetInterface): SetInterface {
        val resht: SetInterface = this
        if(resht.isEmpty()) return s
        if(s.isEmpty()) return resht
        s.toList().forEach {
            if(!resht.search(it)) resht.insert(it)
        }
        return resht
    }

}
