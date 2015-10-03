package hw04

interface SetInterface{
    fun insert(key: Int): Tree
    fun toList(): List<Int>
    fun search(key: Int): Boolean
    fun remove(key: Int): Tree
    fun union(set: SetInterface): Tree
    fun intersection(set: SetInterface): Tree



}