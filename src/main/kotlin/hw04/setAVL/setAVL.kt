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
public fun <T : Comparable<T>>AVL<T>.toList (): ArrayList<T> {
    when (this) {
        is Empty -> return ArrayList<T>()
        is Node ->{
            val list = leftChild.toList()
            list.add(key)
            list.addAll(rightChild.toList())
            return list
        }
        else -> throw Exception("Unknown class")
    }
}
public class setAVL<T : Comparable<T>> : AbstractSet<T> {
    private  var tree : AVL<T> = Empty()
    override operator fun iterator(): Iterator<T> {
        return tree.iterator()
    }
    override public fun makeEmpty() {
        tree = Empty()
    }
    override  public fun insert  (value : T){
        tree = addToAVL(tree, value)
    }
    override public fun delete (value : T) : Boolean{
        val findElem = tree.find(value)
        if (findElem) {
            tree = removeInAVL(tree, value)
        }
        return findElem
    }
    override public fun search (value : T) : Boolean{
        return tree.find(value)
    }
    override public fun toList(): ArrayList<T> {
        return tree.toList()
    }
    override public fun union (set : AbstractSet<T>) : AbstractSet<T> {
        val list = set.toList()
        val set = this
        for (value in list){
            set.insert(value)
        }
        return set
    }
    override public fun intersection (set : AbstractSet<T>) : AbstractSet<T> {
        fun getResultedList(set : AbstractSet<T>) : List<T> {
            val list = this.toList()
            val list2 = set.toList()
            var resultedList = ArrayList<T>()

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
            set.insert (elem)
        }
        return set
    }
}
