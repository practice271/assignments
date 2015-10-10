package hw04

abstract class AbstractMap
{
    public abstract fun insert (value: Int) : Unit
    public abstract fun delete (value: Int) : Unit
    public abstract fun search (value: Int) : Boolean
    public abstract fun union (map : AbstractMap) : AbstractMap
    public abstract fun intersection (map : AbstractMap) : AbstractMap
    public abstract fun toList () : List<Int>
}