package hw04

import java.util.LinkedList

public class HashTable(public val arrsize: Int): SetInterface() {

    public val table = Array(arrsize, { LinkedList<Int>() })

    private fun isEmpty(): Boolean = toList().toString() == "[]"

    public override fun insert(k: Int): Unit {
        table[k % arrsize].add(k)
    }

    public override fun delete(k: Int): Unit {
        val reqList = table[k % arrsize]
        val indOfElm = reqList.indexOf(k)
        if(indOfElm != -1)
            reqList.remove(indOfElm)
    }

    public override fun search(k: Int): Boolean = table[k % arrsize].indexOf(k) != -1

    public override fun union(s: SetInterface): SetInterface {
        val resht = HashTable(Math.max(arrsize, (s as HashTable).arrsize))
        if(isEmpty() || s.isEmpty()) return resht
        toList().forEach {
            if(s.search(it)) resht.insert(it)
        }
        return resht
    }

    public override fun intersection(s: SetInterface): SetInterface {
        val resht: SetInterface = this
        if((resht as HashTable).isEmpty()) return s
        if((s as HashTable).isEmpty()) return resht
        s.toList().forEach {
            if(!resht.search(it)) resht.insert(it)
        }
        return resht
    }



    private fun toList(): List<Int> {
        var res: List<Int> = listOf()
        table.forEach { res += it }
        return res
    }

}
