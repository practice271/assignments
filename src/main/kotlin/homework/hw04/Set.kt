package homework.hw04

import java.util.*

interface setInterface
{
    fun Insert(value : Int)
    fun Delete(value : Int)
    fun Search(value: Int) : Boolean
    fun Union(set1 : setInterface) : setInterface
    fun Intersect(set1 : setInterface) : setInterface
    fun toArrayList() : ArrayList<Int>
}