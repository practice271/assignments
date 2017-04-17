// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw04

import java.util.*

/**
 * Map implementation with hash table.
 * @param K The type of table's keys (must be comparable).
 * @param V The type of table's values.
 */
class HashMap<K, V>() : AbstractMap<K, V> where K : Comparable<K> {
    private val TABLE_SIZE = 1024
    private val hashTable = Array<ArrayList<MapEntry<K, V>>>(TABLE_SIZE, { ArrayList<MapEntry<K, V>>() })

    override fun insert(newKey : K, newValue : V) {
        remove(newKey)
        val index = newKey.hashCode() % TABLE_SIZE
        hashTable[index].add(MapEntry(newKey, newValue))
    }

    override fun insert(newEntry : MapEntry<K, V>) {
        remove(newEntry.key)
        val index = newEntry.key.hashCode() % TABLE_SIZE
        hashTable[index].add(newEntry)
    }

    override fun search(key : K) : V? {
        val tableIndex = key.hashCode() % TABLE_SIZE
        val listIndex = hashTable[tableIndex].indexOfRaw(MapEntry(key, null))
        return if (listIndex != -1)
            hashTable[tableIndex].elementAt(listIndex).value
        else null
    }

    override fun remove(key : K) {
        fun MapEntry<K, V>.equals(another : MapEntry<K, V>) = (this.key.equals(another.key))
        val index = key.hashCode() % TABLE_SIZE
        hashTable[index].removeRaw(MapEntry(key, null))
    }

    override fun newClassInstance() : AbstractMap<K, V> = HashMap<K, V>()

    override fun iterator() : Iterator<MapEntry<K, V>> = HashMapIterator(this)

    private class HashMapIterator<K : Comparable<K>, V>(map : HashMap<K, V>) : Iterator<MapEntry<K, V>> {
        private val arrayIterator = map.hashTable.iterator()
        private var listIterator = nextListIterator()

        override fun hasNext() : Boolean {
            if (listIterator.hasNext()) return true
            try {
                listIterator = nextListIterator()
                return listIterator.hasNext()
            }
            catch (e : NoSuchElementException) {
                return false
            }
        }

        override fun next() : MapEntry<K, V> {
            if (!listIterator.hasNext()) {
                listIterator = nextListIterator()
            }
            return listIterator.next()
        }

        private fun nextListIterator() : Iterator<MapEntry<K, V>> {
            while (arrayIterator.hasNext()) {
                val list = arrayIterator.next()
                if (list.size > 0) return list.iterator()
            }
            return EmptyIterator<MapEntry<K, V>>()
        }
    }
}