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

public class Tree(var tree: AvlTree?): Set{

    override fun insert(value: Int):Set{
        return Tree(tree?.insert(value))
    }
    override fun delete(value: Int):Set{
        return Tree(tree?.delete(value))
    }
    override fun search(value: Int):Boolean{
        return (tree as AvlTree).search(value)
    }
    override fun toArray():Array<Int>{
        var arr = emptyArray<Int>()
        fun LCR(tr: AvlTree?) {
            if (tr == null) {}
            else {
                LCR(tr.lTree)
                arr = arr.plus(tr.key)
                LCR(tr.rTree)
            }
        }
        LCR(tree)
        return arr
    }
    override fun union(set: Set):Set{
        var newTree = tree
        set.toArray().forEach {
            newTree?.insert(it)
        }
        return Tree(newTree)
    }
    override fun intersect(set:Set):Set{
        var newTree = set
        newTree.toArray().forEach {
            if (!search(it)) {
                newTree.delete(it)
            }
        }
        return newTree
    }
}

public class Hash(val size: Int):Set{
    private fun hashF(value: Int) = value mod size
    private var hashTable = Array<List<Int>>(size, {i -> listOf()})

    override fun insert(value: Int):Set{
        if (!search(value)) hashTable[hashF(value)] = hashTable[hashF(value)] + value
        return this
    }
    override fun delete(value: Int):Set{
        hashTable[hashF(value)] = hashTable[hashF(value)] - value
        return this
    }
    override fun search(value: Int):Boolean{
        var flag = false
        hashTable[hashF(value)].forEach {
            if (it == value) flag = true
        }
        return flag
    }
    override fun toArray():Array<Int>{
        var arr = emptyArray<Int>()
        hashTable.forEach {
            arr = arr.plus(it)
        }
        return arr
    }
    override fun union(set: Set):Set{
        var newHash = Hash(size )
        set.toArray().forEach {
            newHash.insert(it)
        }
        this.toArray().forEach {
            newHash.insert(it)
        }
        return newHash
    }
    override fun intersect(set: Set):Set{
        var newHash = Hash(size)
        set.toArray().forEach {
            newHash.insert(it)
        }
        set.toArray().forEach {
            if (!search(it)) newHash.delete(it)
        }
        return newHash
    }
}
