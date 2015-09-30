package hw04.setAVL

import hw03.*
import hw04.AbstractSet
import java.util.*

/* Realization of abstract set interface
   for AVL trees
   made by Guzel Garifullina
   Estimated time 2 hours
   real time      2 hours
*/
public fun AVL.toList (): ArrayList<Int> {
    when (this) {
        is Empty -> return ArrayList<Int>()
        is Node ->{
            val list = leftChild.toList()
            list.add(key)
            list.addAll(rightChild.toList())
            return list
        }
        else -> throw Exception("Unknown class")
    }
}
public class setAVL : AbstractSet {
    private  var tree : AVL = Empty()
    override protected fun makeEmpty() {
        tree = Empty()
    }
    override  public fun insert<T : Comparable<T>> (value : Int){
        tree = addToAVL(tree, value)
    }
    override public fun delete<T : Comparable<T>> (value : Int) : Boolean{
        val findElem = tree.find(value)
        if (findElem) {
            tree = removeInAVL(tree, value)
        }
        return findElem
    }
    override public fun search<T : Comparable<T>> (value : Int) : Boolean{
        return tree.find(value)
    }
    override public fun toList<T : Comparable<T>> (): ArrayList<Int> {
        return tree.toList()
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
    var set= setAVL()
    set.insert<Int>(1)
    set.insert<Int>(2)
    set.insert<Int>(7)
    set.insert<Int>(0)
    println (set.search<Int>(1))
    val l = set.toList<Int>()
    println(l.get(1))
}