package hw04

import java.util.*

internal class HashTable (HashKey : Int) : IMap()
{
    public val table = Array(HashKey, {LinkedList<Int>()})

    public val hashKey = HashKey

    private fun toList() : List<Int>
    {
        var list = emptyList<Int>()
        table.forEach { list += it }
        return list
    }

    private fun isEmpty() : Boolean = toList() == emptyList<Int>()

    override public fun insert (value: Int)
    {
        table[value % hashKey].add(value)
    }

    override public fun delete (value : Int)
    {
        val hashList = table[value % hashKey]
        val indexElem = hashList.indexOf(value)

        if (indexElem != -1) hashList.remove(indexElem)
    }

    override public fun search (value : Int) : Boolean = table[value % hashKey].indexOf(value) != -1

    override public fun union (map: IMap) : IMap
    {
        val resTable = HashTable(Math.max(hashKey, (map as HashTable).hashKey))
        if (isEmpty() || map.isEmpty()) return resTable

        toList().forEach {
            if (map.search(it)) resTable.insert(it)
        }

        return resTable
    }

    override public fun intersection (map: IMap) : IMap
    {
        val resTable = this
        if (isEmpty()) return map
        if ((map as HashTable).isEmpty()) return resTable

        toList().forEach {
            if (!resTable.search(it)) resTable.insert(it)
        }

        return resTable
    }
}
