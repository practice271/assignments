package hw08

import java.util.*

abstract class AbstractMap : Iterable<Int>
{
    public abstract fun insert (value: Int) : Unit
    public abstract fun delete (value: Int) : Unit
    public abstract fun search (value: Int) : Boolean
    public abstract fun union (map : AbstractMap) : AbstractMap
    public abstract fun intersection (map : AbstractMap) : AbstractMap
    public abstract fun toList () : List<Int>
}

internal class EmptyIterator<A>() : Iterator<A>
{
    override fun next(): A {
        throw NoSuchElementException()
    }
    override fun hasNext(): Boolean = false
}
