package hw04

import kotlin.properties.Delegates

public class HashMap<A> (arraySize : Int) : Map<A>(), Iterable<Pair<Int,A>> {
    val values = Array(arraySize, {linkedListOf<Pair<Int,A>>()})

    override fun iterator(): Iterator<Pair<Int,A>> = HashMapIterator()

    inner class HashMapIterator() : Iterator<Pair<Int,A>>{
        var curList = 0
        var curIter by Delegates.notNull<Iterator<Pair<Int, A>>>()
        init {
            while (curList < values.size - 1 && values[curList].isEmpty()) curList++
            curIter = values[curList].iterator()
        }
        override fun hasNext(): Boolean {
            if (curList >= values.size) return false
            if (curIter.hasNext()) return true

            curList++
            while (curList < values.size - 1 && values[curList].isEmpty()) curList++
            curIter = values[curList].iterator()
            return curIter.hasNext()
        }

        override fun next(): Pair<Int,A> {
            return curIter.next()
        }
    }

    override public fun insert (pair : Pair<Int, A>) : HashMap<A> {
        val ind = Math.abs(pair.first.hashCode()) % values.size
        values[ind].addIfNotAlready(pair)//Since there'll be only one thread, values[ind] != null
        return this
    }
    override public fun delete (key : Int) : HashMap<A>? {
        val ind = Math.abs(key.hashCode()) % values.size
        values[ind].delByKeyIfExists(key)//Since there'll be only one thread, values[ind] != null
        return this
    }
    override  public fun search (key : Int) : A? {
        val ind = Math.abs(key.hashCode()) % values.size
        return values[ind].findByKeyInPair(key)?.second//Since there'll be only one thread, values[ind] != null
    }

    override  public fun toList () : List<Pair<Int, A>> {
        var res : MutableList<Pair<Int, A>> = linkedListOf() //: MutableList<Pair<Int, A>>
        for (i in values)
            res.addAll(i)
        return res
    }

    override public fun unite (map2 : Map<A>?) : HashMap<A> {
        if (map2 == null) return this
        val map2 = map2.toList()
        for (i in map2) {
            insert(i)
        }
        return this
    }

    override public fun intersect (map2 : Map<A>) : HashMap<A>? {
        val map1 = toList()
        val map2 = map2.toList()
        var res : HashMap<A>? = null
        for (i in map2) {
            if (map1.hasSecondValueInPair(i)) {
                if (res == null) res = HashMap<A> (values.size())
                res.insert(i)
            }
        }
        return res
    }
}