package hw08

import java.util.*

internal class HashTable (hashKey: Int) : AbstractMap()
{
    public val table = Array(hashKey, {LinkedList<Int>()})

    public val hashSize = hashKey

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int>
        {
            private var idx = 0
            private var countVisited = 0

            override fun next(): Int {
                if (hasNext())
                {
                    val elem : Int = table[idx].get(countVisited)
                    countVisited++
                    return elem
                }
                throw NoSuchElementException()
            }

            override fun hasNext(): Boolean {
                if (idx < hashSize)
                {
                    if (table[idx].isEmpty())
                    {
                        idx++
                        return hasNext()
                    }
                    else
                    {
                        if (countVisited < table[idx].count())
                            return true
                        else
                        {
                            countVisited = 0
                            idx++
                            return hasNext()
                        }
                    }
                }
                else return false
            }

        }
    }

    override public fun toList() : List<Int>
    {
        var list = emptyList<Int>()
        table.forEach { list += it }
        return list
    }

    private fun isEmpty() : Boolean = toList() == emptyList<Int>()

    override public fun insert (value: Int)
    {
        table[value % hashSize].add(value)
    }

    override public fun delete (value : Int)
    {
        val hashList = table[value % hashSize]
        val indexElem = hashList.indexOf(value)

        if (indexElem != -1) hashList.remove(indexElem)
    }

    override public fun search (value : Int) : Boolean = table[value % hashSize].indexOf(value) != -1

    override public fun union (map: AbstractMap) : HashTable
    {
        val resTable = HashTable(hashSize)

        map.toList().forEach { resTable.insert(it) }
        toList().forEach {
            if (!resTable.search(it)) resTable.insert(it)
        }

        return resTable
    }

    override public fun intersection (map: AbstractMap) : HashTable
    {
        val resTable = HashTable(hashSize)

        map.toList().forEach {
            if (search(it)) resTable.insert(it)
        }

        return resTable
    }
}
