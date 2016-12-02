package homework4

import homework3.*
import java.util.*

interface Set : Iterable<Int> {
    public fun insert(value: Int): Set
    public fun delete(value: Int): Set
    public fun search(value: Int): Boolean
    public fun toArray(): Array<Int>
    public fun union(set: Set): Set
    public fun intersect(set: Set): Set
}

public class Tree(var tree: AvlTree?) : Set {
    override fun iterator(): Iterator<Int> {
        if (tree == null) return EmptyIterator()
        return LCRNodeIterator(this)
    }

    private class EmptyIterator<Int>(): Iterator<Int> {
        override fun hasNext(): Boolean = false
        override fun next(): Int { throw NoSuchElementException() }
    }
    private abstract class TreeIterator(val root: Tree) : Iterator<Int> {
        protected val lIterator: Iterator<Int> = Tree(root.tree?.lTree).iterator()
        protected val rIterator: Iterator<Int> = Tree(root.tree?.rTree).iterator()
        protected var observed: Boolean = false
        protected var lHasNext: Boolean = true
            get() =
            if (field) { field = lIterator.hasNext(); field } else false
        protected var rHasNext: Boolean = true
            get() =
            if (field) { field = rIterator.hasNext(); field } else false

        override fun hasNext(): Boolean {
            if (!observed) {return true}
            if (lHasNext ) {return true}
            if (rHasNext ) {return true}
            return false
        }
    }

    private class LCRNodeIterator(root: Tree) : TreeIterator(root) {
        override fun next(): Int {
            if (lHasNext ) {return lIterator.next()}
            if (!observed) {observed = true; return root.tree?.key ?: 0}
            if (rHasNext ) {return rIterator.next()}
            throw NoSuchElementException()
        }
    }

    override fun insert(value: Int): Set {
        return Tree(tree?.insert(value))
    }

    override fun delete(value: Int): Set {
        return Tree(tree?.delete(value))
    }

    override fun search(value: Int): Boolean {
        return (tree as AvlTree).search(value)
    }

    override fun toArray(): Array<Int> {
        var arr = emptyArray<Int>()
        fun LCR(tr: AvlTree?) {
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

    override fun union(set: Set): Set {
        var newTree = tree
        set.toArray().forEach {
            newTree?.insert(it)
        }
        return Tree(newTree)
    }

    override fun intersect(set: Set): Set {
        var newTree = set
        newTree.toArray().forEach {
            if (!search(it)) {
                newTree.delete(it)
            }
        }
        return newTree
    }
}

public class Hash(val size: Int) : Set {
    private fun hashF(value: Int): Int = value mod size
    private  var hashTable = Array<List<Int>>(size, { listOf() })

    override fun iterator(): Iterator<Int> {
        return HashTableIterator<Int>()
    }

    private inner class HashTableIterator<Int>() : Iterator<kotlin.Int> {
        private var arraycount = 0
        private var listcount = 0

        override fun hasNext(): Boolean {
            if ((arraycount == size - 1) && (listcount == hashTable[arraycount].size)) return false
            while (arraycount < size - 1) {
                if (listcount == hashTable[arraycount].size) {arraycount++; listcount = 0}
                else return true
            }
            return false
        }

        override fun next(): kotlin.Int {
            if (hasNext()) {
                return hashTable[arraycount][listcount++]
            } else throw NoSuchElementException()
        }
    }

    override fun insert(value: Int): Set {
        if (!search(value)) hashTable[hashF(value)] = hashTable[hashF(value)] + value
        return this
    }

    override fun delete(value: Int): Set {
        hashTable[hashF(value)] = hashTable[hashF(value)] - value
        return this
    }

    override fun search(value: Int): Boolean {
        var flag = false
        hashTable[hashF(value)].forEach {
            if (it == value) flag = true
        }
        return flag
    }

    override fun toArray(): Array<Int> {
        var arr = emptyArray<Int>()
        hashTable.forEach {
            arr = arr.plus(it)
        }
        return arr
    }

    override fun union(set: Set): Set {
        var newHash = Hash(size)
        set.toArray().forEach {
            newHash.insert(it)
        }
        this.toArray().forEach {
            newHash.insert(it)
        }
        return newHash
    }

    override fun intersect(set: Set): Set {
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