package hw04

import java.util.*

/*  Abstract set interface  made by Guzel Garifullina
   Estimated time 15 minutes
   real time      10 minutes
*/
public interface AbstractSet {
    abstract protected fun makeEmpty()
    abstract public fun insert<T : Comparable<T>> (value : Int)
    abstract public fun delete<T : Comparable<T>> (value : Int) : Boolean
    abstract public fun search<T : Comparable<T>> (value : Int) : Boolean
    abstract public fun toList<T : Comparable<T>> (): ArrayList<Int>
    abstract public fun union (set : AbstractSet) : AbstractSet
    abstract public fun intersection (set : AbstractSet) : AbstractSet
}