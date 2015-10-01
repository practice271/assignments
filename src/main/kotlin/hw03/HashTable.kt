package hw04

import java.util.LinkedList
class HashTable<Key, Value> : AbstractMap<Key, Value>() where Key : Comparable<Key> {
    private val arraySize = 1000
    private val array: Array<LinkedList<Pair<Key, Value>>?> = Array(arraySize, { null })

    private fun getHashCode(key: Key) = key.hashCode() % arraySize

    override public fun insert(key: Key, value: Value) {
        val arrayIndex = getHashCode(key)
        if (array[arrayIndex] == null)
            array[arrayIndex] = linkedListOf(Pair(key, value))
        else {
            delete(key)
            array[arrayIndex]!!.addFirst(Pair(key, value))
        }
    }

    override public fun delete(key: Key) {
        val arrayIndex = getHashCode(key)
        if (array[arrayIndex] == null) return
        var iterator = array[arrayIndex]!!.listIterator()               //it`s guaranteed that array[arrayIndex]
        while (iterator.hasNext()) if (iterator.next().first == key) {  //is not null because in this case function
            iterator.remove()                                           //would have already returned
            return
        }
    }

    override public fun search(key: Key): Value? {
        val arrayIndex = getHashCode(key)
        if (array[arrayIndex] == null) return null
        var iterator = array[arrayIndex]!!.listIterator()               //it`s guaranteed that array[arrayIndex]
        while (iterator.hasNext()) {                                    //is not null because in this case function
            val elem = iterator.next()                                  //would have already returned
            if (elem.first == key) return elem.second
        }
        return null
    }

    override public fun toList(): LinkedList<Pair<Key, Value>> {
        val resList = linkedListOf<Pair<Key, Value>>()
        for (list in array) {
            if (list != null) resList.addAll(list)
        }
        return resList
    }

    override public fun union(map : AbstractMap<Key, Value>) : HashTable<Key, Value> {
        val res = HashTable<Key, Value>()
        val hashTableList = this.toList()
        val mapList = map.toList()
        for (elem in hashTableList) res.insert(elem.first, elem.second)
        for (elem in mapList) if (res.search(elem.first) == null) res.insert(elem.first, elem.second)
        return res
    }

    override public fun intersection(map : AbstractMap<Key, Value>) : HashTable<Key, Value> {
        val res = HashTable<Key, Value>()
        val treeList = this.toList()
        val mapList = map.toList()
        for (elem in treeList) if (mapList.contains(elem)) res.insert(elem.first, elem.second)
        return res
    }
}