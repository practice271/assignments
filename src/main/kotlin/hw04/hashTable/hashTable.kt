package hw04.hashTable

import hw04.AbstractSet
import java.util.*

/* Realization of abstract set interface
   for AVL trees
   made by Guzel Garifullina
   Estimated time 4 hours
   real time      3 hour
*/

public class hashTable() : AbstractSet {
    private  val size = 100
    private var place = size
    private fun nextHash (hash : Int) : Int {
        return (hash + 1).hashCode() % size
    }
    private var table : Array<Int?>  = arrayOfNulls(size)
    override protected fun makeEmpty() {
        table = arrayOfNulls(size)
    }

    override public fun insert<T : Comparable<T>> (value: Int) {
        fun insertf(hashCode : Int, value: Int) {
            if (table.get(hashCode) == null ) {
                table.set(hashCode, value)
            }
            else if (table.get(hashCode) != value) {
                insertf(nextHash(hashCode), value)
            }
        }
        if (place == 0) {
            if (! this.search<Int>(value)){
                throw  Exception("There no free place")
            }
            else  {
                return
            }
        }
        insertf(value.hashCode() % size, value)
    }
    override public fun delete<T : Comparable<T>> (value : Int) : Boolean{
        fun del (hashCode:Int, value: Int, iter: Int) : Boolean{
            if (iter > size){
                return false
            }
            /*if (table.get(hashCode) == null ) {
                return false
            }*/
            if (table.get(hashCode) != value) {
                return del (nextHash(hashCode), value, iter + 1)
            }
            else {
                table.set(hashCode, null)
                return true
            }
        }
        return del (value.hashCode() % 100, value, 0)
    }
    override public fun search<T : Comparable<T>> (value : Int) : Boolean{
        fun find (hashCode:Int, value: Int, iter: Int) : Boolean{
            if (iter > size){
                return false
            }
            if (table.get(hashCode) == null ) {
                return false
            }
            if (table.get(hashCode) != value) {
                return find (nextHash(hashCode), value, iter + 1)
            }
            else {
                return true
            }
        }
        return find (value.hashCode() % 100, value, 0)
    }
    override public fun toList<T : Comparable<T>> (): ArrayList<Int> {
        var list = ArrayList<Int>()
        for (element in table){
            if (element != null){
                list.add(element)
            }
        }
        return list
    }
    override public fun union (set : AbstractSet) : AbstractSet {
        val list = set.toList<Int>()
        val set = this
        for (value in list){
            set.insert<Int>(value)
        }
        return set
    }
    override public fun intersection (set : AbstractSet) : AbstractSet {
        fun getResultedList(set : AbstractSet) : List<Int> {
            val list = this.toList<Int>()
            val list2 = set.toList<Int>()
            var resultedList = ArrayList<Int>()

            val lastElem2 = list2.lastIndex
            var i2 = 0
            for (elem in list){
                while ((i2 <= lastElem2) && (list2.get(i2) < elem)){
                    i2 ++
                }
                if (elem == list2.get(i2)){
                    resultedList.add(elem)
                }
            }
            return  resultedList
        }
        val resultedList = getResultedList(set)
        val set = this
        set.makeEmpty()
        for (elem in resultedList){
            set.insert<Int>(elem)
        }
        return set
    }
}

fun main(args: Array<String>) {
    var set = hashTable()
    set.insert<Int>(1)
    set.insert<Int>(2)
    set.insert<Int>(7)
    set.insert<Int>(0)
    val list = set.toList<Int>()
    println(list)
}