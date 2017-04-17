package hw04.hashTable

import hw04.AbstractSet
import java.util.*

/* Realization of abstract set interface
   for AVL trees
   made by Guzel Garifullina
   Estimated time 4 hours
   real time      3 hour

   Replaced open addressing hash
   with separate chaining hash,
   because AbstractSet doesn't know
   anything about hash changed size.
   So any insertion to the union
   of 2 hashes(with resized hash)
   will crash program
*/

public class hashTable<T : Comparable<T>>() : AbstractSet<T> {
    internal  var size = 100
    private  fun returnEmpty() : ArrayList<ArrayList<T>>{
        var arr = ArrayList<ArrayList<T>>()
        for (i in 0.. (size - 1)){
            arr.add(i, ArrayList<T>())
        }
        return arr
    }
    private var table  :  ArrayList<ArrayList<T>> = returnEmpty()
    override public  fun makeEmpty() {
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
            list.remove(index)
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