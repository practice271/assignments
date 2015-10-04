package homework4
import homework3.*

interface  Set{
    public fun insert(value: Int):Set
    public fun delete(value: Int):Set
    public fun search(value: Int): Boolean
    public fun toArray():Array<Int>
    public fun union(set: Set): Set
    public fun intersect(set: Set): Set
}

public class AvlSet(var array: Array<Int>): Set{
    private fun treeToArray(tree:avlTree?):Array<Int> {
        var arr = emptyArray<Int>()
        fun LCR(tr: avlTree?) {
            if (tr == null) {
            } else {
                LCR(tr.lTree)
                arr = arr.plus(tr.key)
                LCR(tr.rTree)
            }
        }
        LCR(tree)
        return arr
    }

    private fun toTree(arr:Array<Int>):avlTree?{
        var tree:avlTree? = null
        arr.forEach{
            tree = insert(tree, it)
        }
        return tree
    }

    private var tree = toTree(array)

    override fun insert(value: Int):Set{
        tree = insert(tree, value)
        return AvlSet(treeToArray(tree))
    }
    override fun delete(value: Int):Set{
        tree = delete(tree, value)
        return AvlSet(treeToArray(tree))
    }
    override fun search(value: Int):Boolean{
        return search(value, tree)
    }
    override fun toArray():Array<Int>{
        return array
    }
    override fun union(set: Set):Set{
        var tree = toTree((set as AvlSet).array)
        array.forEach {
            tree = insert(tree, it)
        }
        return AvlSet(treeToArray(tree))
    }

    override fun intersect(set:Set):Set{
        var arr = emptyArray<Int>()
        set.toArray().forEach {
            if (search(it)) {arr = arr.plus(it)}
        }
        return AvlSet(arr)
    }
}

public class HashSet(val size: Int):Set{
    private fun hashF(value: Int) = value.hashCode() mod size
    private var hashTable = arrayOfNulls<Int>(size)

    override fun insert(value: Int):Set{
        if (hashTable[hashF(value)] == null) hashTable[hashF(value)] = value
        return this
    }

    override fun delete(value: Int):Set{
        if (hashTable[hashF(value)] == value) hashTable[hashF(value)] = null
        return this
    }
    override fun search(value: Int):Boolean{
        if (hashTable[hashF(value)] == null) return false
        else return true
    }
    override fun toArray():Array<Int>{
        var arr = emptyArray<Int>()
        hashTable.forEach {
            if(it != null) arr = arr.plus(it)
        }
        return arr
    }
    override fun union(set: Set):Set{
        var newHash = HashSet((set as HashSet).size + size )
        set.toArray().forEach {
            newHash.insert(it)
        }
        this.toArray().forEach {
            newHash.insert(it)
        }
        return newHash
    }
    override fun intersect(set: Set):Set{
        var newHash = HashSet(size)
        set.toArray().forEach {
            newHash.insert(it)
        }
        set.toArray().forEach {
            if (!search(it)) newHash.delete(it)
        }
        return newHash
    }
}

fun main(args: Array<String>) {
}