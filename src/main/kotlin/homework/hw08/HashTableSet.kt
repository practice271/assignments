package homework.hw08
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
    override fun iterator(): Iterator<Int> {
        return HashTableIterator(size, arrayOfValue)
    }

    private val size = 100
    private   var arrayOfValue = Array(size,{i -> ArrayList<Int>()})
    private fun hashFun(value : Int) = value.hashCode() mod size


    private class HashTableIterator(private val size : Int, private val array: Array<ArrayList<Int>> ) : Iterator<Int> {
        private var xi = 0
        private var yi = 0

        override fun hasNext(): Boolean {
            if (xi < size )
                if (array[xi].isEmpty()) {
                    xi++
                    return hasNext()
               } else {

                    if (yi < array[xi].size) {
                        return true
                    } else {
                        yi = 0
                        xi++
                        return hasNext()
                    }
                }
            else return false
        }

        override fun next(): Int {
            if(hasNext()) {
                yi++
                return array[xi].get(yi - 1)
            }
            throw NoSuchElementException()
        }
    }

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
        arrayOfValue[hashFun(value)].remove(value)
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