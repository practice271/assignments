package hw04.hashTable

import hw04.AbstractSet
import java.util.*

/* Realization of abstract set interface
   for AVL trees
   made by Guzel Garifullina
   Estimated time 4 hours
   real time      3 hour
*/

public class hashTable<T : Comparable<T>>() : AbstractSet<T> {
    internal  var size = 100
    internal var elemAmount = 0
    private  fun returnEmpty() : ArrayList<ArrayList<T>>{
        var arr = ArrayList<ArrayList<T>>()
        for (i in 0.. (size - 1)){
            arr.add(i, ArrayList<T>())
        }
        elemAmount = 0
        return arr
    }
    private var table  :  ArrayList<ArrayList<T>> = returnEmpty()

    private inner class HIterator : Iterator<T> {
        private var amt = elemAmount
        private var indexOfList = 0
        private var indexOfElem = 0
        override fun hasNext(): Boolean {
            return (amt > 0)
        }
        override fun next(): T {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            else {
                for (i in indexOfList .. (size - 1)) {
                    val l = table[i].size - 1
                    for (j in indexOfElem .. l){
                        amt --
                        val res = table[i][j]
                        if (j + 1 <= l){
                            indexOfElem = j + 1
                        }
                        else {
                            indexOfElem = 0
                            var ind = i + 1
                            while (table[ind] == null){
                                ind ++
                            }
                            indexOfList = ind
                        }
                        return res
                    }
                }
                throw Exception ("Error in next function")
            }
        }
    }
    override operator fun iterator(): Iterator<T> {
        return HIterator()
    }
    override public  fun makeEmpty() {
        elemAmount = 0
        table = returnEmpty()
    }
    private fun toHash<A : Comparable<A>>(value : A) : Int {
        return value.hashCode() % size
    }
    override public fun insert (value: T) {
        if (! search(value)){
            var list = table.get(toHash<T>(value))
            list.add(value)
        }
        elemAmount ++
    }
    private  fun find (list: ArrayList<T>, value: T) : Int{
        for (i in list.indices){
            if (list.get(i) == value){
                return i
            }
        }
        return -1
    }
    override public fun delete (value : T) : Boolean{
        var list = table.get(toHash<T>(value))
        val index = find(list, value)
        if (index > -1){
            list.removeAt(index)
            elemAmount --
            return true
        }
        else {
            return false
        }
    }
    override public fun search (value : T) : Boolean{
        for ( list in table){
            if (find(list, value) > -1){
                return true
            }
        }
        return false
    }
    override public fun toList(): ArrayList<T> {
        var result = ArrayList<T>()
        for (i in table.indices){
            result.addAll(table.get(i))
        }
        return result
    }
    override public fun union (set : AbstractSet<T>) : AbstractSet<T> {
        val list = set.toList()
        val set2 = this
        for (value in list){
            set2.insert(value)
        }
        return set2
    }
    override public fun intersection (set : AbstractSet<T>) : AbstractSet<T> {
        fun getResultedList(set : AbstractSet<T>) : List<T> {
            val list = this.toList()
            val list2 = set.toList()
            var resultedList = ArrayList<T>()
            for (elem in list2){
                if (list.contains (elem)){
                    resultedList.add(elem)
                }
            }
            return  resultedList
        }
        val resultedList = getResultedList(set)
        val set2 = this
        set2.makeEmpty()
        for (elem in resultedList){
            set2.insert(elem)
        }
        return set2
    }
}

fun main(args: Array<String>) {
    val set = hashTable<Int>()
    set.insert(1)
    set.insert(2)
    set.insert(7)
    set.insert(0)
    val list = set.toList()
    println(list)

    val set2 = hashTable<Char>()
    set2.insert('1')
    set2.insert('2')
    set2.insert('7')
    set2.insert('0')
    val list2 = set2.toList()
    println(list2)
}