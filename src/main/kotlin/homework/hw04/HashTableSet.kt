package homework.hw04

import java.util.*

fun ArrayList<Int>.copyWithoutRepeate ( list : ArrayList<Int>)  :ArrayList<Int>
{
    for(item in list){
        if(!this.contains(item)) this.add(item)
    }
    return this
}

class hashTableSet(val set : Array<Int> ) : setInterface
{
    private val size = 100
    private   var arrayOfValue = Array(size,{i -> ArrayList<Int>()})
    private fun hashFun(value : Int) = value.hashCode() mod size

    init {
        for (value in set) {
            val index = hashFun(value)
            if (!arrayOfValue[index].contains(value)) arrayOfValue[index].add(value)
        }
    }

    override fun Insert(value: Int) {
        val index = hashFun(value)
        if(!arrayOfValue[index].contains(value)) arrayOfValue[index].add(value)
    }

    override fun Delete(value: Int) {
        arrayOfValue[hashFun(value)].remove(value : Any)
    }

    override fun Search(value: Int): Boolean {
        return arrayOfValue[hashFun(value)].contains(value)
    }

    override fun Union(set1: setInterface): setInterface {
        val finalSet = toArrayList().copyWithoutRepeate(set1.toArrayList())
        return hashTableSet(finalSet.toTypedArray())
    }

    override fun Intersect(set1: setInterface): setInterface {
        val first = set1.toArrayList()
        val second = toArrayList()
        val finalSet = ArrayList<Int>()
        for(item in first) if(second.contains(item)) finalSet.add(item)
        return hashTableSet(finalSet.toTypedArray())
    }

    override fun toArrayList(): ArrayList<Int> {
        var res = ArrayList<Int>()
        for(item in arrayOfValue) res = res.copyWithoutRepeate(item)
        return res
    }
}


