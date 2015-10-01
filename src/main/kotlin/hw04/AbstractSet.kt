package hw04

import java.util.*

/*  Abstract set interface  made by Guzel Garifullina
   Estimated time 15 minutes
   real time      10 minutes
*/
public interface AbstractSet<T : Comparable<T>> {
    abstract protected fun makeEmpty()
    abstract public fun insert (value : T)
    abstract public fun delete (value : T) : Boolean
    abstract public fun search (value : T) : Boolean
    abstract public fun toList (): ArrayList<T>
    abstract public fun union (set : AbstractSet<T>) : AbstractSet<T>
    abstract public fun intersection (set : AbstractSet<T>) : AbstractSet<T>
}