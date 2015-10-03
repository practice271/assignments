package hw04

abstract class IMap
{
    public abstract fun insert (value: Int) : Unit
    public abstract fun delete (value: Int) : Unit
    public abstract fun search (value: Int) : Boolean
    public abstract fun union (map : IMap) : IMap
    public abstract fun intersection (map : IMap) : IMap
}