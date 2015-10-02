package hw04

import java.util.LinkedList
class HashMap<Key, Value> : AbstractMap<Key, Value>() where Key : Comparable<Key> {
    private val arraySize = 1000
    private val array: Array<LinkedList<Pair<Key, Value>>?> = Array(arraySize, { null })

    private fun getHashCode(key: Key) = key.hashCode() % arraySize

    override public fun insert(key: Key, value: Value) {
        val arrayIndex = getHashCode(key)
        if (array[arrayIndex] == null)
            array[arrayIndex] = linkedListOf(Pair(key, value))
        else {
            delete(key)
            array[arrayIndex]?.addFirst(Pair(key, value))               //it`s guaranteed that array[arrayIndex]
        }                                                               //is not null
    }

    override public fun delete(key: Key) {
        val arrayIndex = getHashCode(key)
        val listAtIndex = array[arrayIndex]
        if (listAtIndex == null) return
        var iterator = listAtIndex.listIterator()
        while (iterator.hasNext())
            if (iterator.next().first == key) {
                iterator.remove()
                return
            }
    }

    override public fun search(key: Key): Value? {
        val arrayIndex = getHashCode(key)
        val listAtIndex = array[arrayIndex]
        if (listAtIndex == null) return null
        var iterator = listAtIndex.listIterator()
        while (iterator.hasNext()) {
            val elem = iterator.next()
            if (elem.first == key) return elem.second
        }
        return null
    }

    override public fun toList(): LinkedList<Pair<Key, Value>> {
        val resList = linkedListOf<Pair<Key, Value>>()
        for (list in array)
            if (list != null) resList.addAll(list)
        return resList
    }

    override public fun union(map : AbstractMap<Key, Value>) : HashMap<Key, Value> {
        val res = HashMap<Key, Value>()
        val hashMapList = this.toList()
        val mapList = map.toList()
        for (elem in hashMapList) res.insert(elem.first, elem.second)
        for (elem in mapList)
            if (res.search(elem.first) == null) res.insert(elem.first, elem.second)
        return res
    }

    override public fun intersection(map : AbstractMap<Key, Value>) : HashMap<Key, Value> {
        val res = HashMap<Key, Value>()
        val treeList = this.toList()
        val mapList = map.toList()
        for (elem in treeList)
            if (mapList.contains(elem)) res.insert(elem.first, elem.second)
        return res
    }
}