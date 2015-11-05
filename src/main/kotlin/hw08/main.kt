/* An Iterable implementation for containers from the problem 4.1.
(expectation: 5 h; reality: 5 h)
by Sokolova Polina */

package hw08

import java.util.*

interface MySet : Iterable<Int> {
    public fun insert(a : Int) : MySet
    public fun delete(a : Int) : MySet
    public fun search(a : Int) : Boolean
    public fun union(t : MySet) : MySet
    public fun intersection(t : MySet) : MySet
    public fun setToString() : String
    public fun setToList() : List<Int>
}

public class Empty() : Tree() {
    override fun iterator() : Iterator<Int> = EmptyIterator()
}
public class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {
    override fun iterator() : Iterator<Int> = NodeIterator(this)
}
abstract public class Tree() : MySet {
    protected class EmptyIterator(): Iterator<Int> {
        override fun hasNext() : Boolean = false
        override fun next() : Int = throw NoSuchElementException()
    }
    protected class NodeIterator(protected val node : Node) : Iterator<Int> {
        private val lIterator : Iterator<Int> = node.l.iterator()
        private val rIterator : Iterator<Int> = node.r.iterator()
        private var observed : Boolean = false
        private var lHasNext : Boolean = true
            get() = if (field) { field = lIterator.hasNext(); field } else false
        private var rHasNext  : Boolean = true
            get() = if (field) { field = rIterator.hasNext(); field } else false
        override fun hasNext() : Boolean {
            if (!observed) { return true }
            if (lHasNext ) { return true }
            if (rHasNext ) { return true }
            return false
        }
        override fun next() : Int {
            if (lHasNext ) { return lIterator.next() }
            if (!observed) { observed = true; return node.value }
            if (rHasNext ) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }
    internal fun height() : Int  {
        if (this is Node) { return 1 + Math.max(this.l.height(), this.r.height()) }
        return 0
    }
    internal fun balanceFactor() : Int {
        if (this is Node) { return this.l.height() - this.r.height() }
        return 0
    }
    override public fun setToString() : String {
        when (this) {
            is Node  -> {
                val lRes = this.l.setToString()
                val rRes = this.r.setToString()
                return "Node(${this.value}, $lRes, $rRes)"
            }
            else -> { return "Empty" }
        }
    }
    internal fun smallLeftRotation() : Tree {
        var L : Tree = Empty()
        var cValue : Int = 0
        var rValue : Int = 0
        var lR : Tree = Empty()
        var rR : Tree = Empty()
        if (this is Node) {
            cValue = this.value
            L = this.l
            if (this.r is Node) {
                rValue = this.r.value
                lR = this.r.l
                rR = this.r.r
            }
        }
        return Node(rValue, Node(cValue, L, lR), rR)
    }
    internal fun smallRightRotation() : Tree {
        var R : Tree = Empty()
        var cValue : Int = 0
        var lValue : Int = 0
        var lL : Tree = Empty()
        var rL : Tree = Empty()
        if (this is Node) {
            R = this.r
            cValue = this.value
            if (this.l is Node) {
                lValue = this.l.value
                lL = this.l.l
                rL = this.l.r
            }
        }
        return Node(lValue, lL, Node(cValue, rL, R))
    }
    internal fun balance() : Tree {
        if (this is Node) {
            val balance = this.balanceFactor()
            if (balance == -2) {
                when {
                    this.r.balanceFactor() > 0 -> {
                        val right = this.r.smallRightRotation()
                        val tree = Node(this.value, this.l, right)
                        return tree.smallLeftRotation()
                    }
                    else -> { return this.smallLeftRotation() }
                }
            }
            if (balance == 2) {
                when {
                    this.l.balanceFactor() < 0 -> {
                        val left = this.l.smallLeftRotation()
                        val tree = Node(this.value, left, this.r)
                        return tree.smallRightRotation()
                    }
                    else -> { return this.smallRightRotation() }
                }
            }
        }
        return this
    }
    override public fun insert(a : Int) : Tree {
        when (this) {
            is Empty -> { return Node(a, Empty(), Empty()) }
            is Node -> {
                when {
                    a < this.value -> { return Node(this.value, this.l.insert(a),
                            this.r).balance() }
                    a > this.value -> { return Node(this.value, this.l,
                            this.r.insert(a)).balance() }
                    else -> { return this }
                }
            }
        }
        return Empty()
    }
    internal fun minInRight() : Int {
        if (this is Node) {
            when (this.l) {
                is Empty -> { return this.value }
                is Node  -> { return this.l.minInRight() }
            }
        }
        return 0
    }
    internal fun replace(a : Int, b : Int) : Tree {
        if (this is Node) {
            when {
                a < this.value -> { return Node(this.value,
                        this.l.replace(a, b), this.r) }
                a > this.value -> { return Node(this.value, this.l,
                        this.r.replace(a, b)) }
                else -> { return Node(b, this.l, this.r) }
            }
        }
        return Empty()
    }
    override public fun delete(a : Int) : Tree {
        if (this is Node) {
            when {
                a < this.value -> { return Node(this.value, this.l.delete(a),
                        this.r).balance() }
                a > this.value -> { return Node(this.value, this.l,
                        this.r.delete(a)).balance() }
                else -> {
                    when (this.r) {
                        is Empty -> { return this.l }
                        is Node -> {
                            val minValue = this.r.minInRight()
                            val resTree = Node(this.value, this.l, this.r.delete(minValue))
                            return resTree.replace(a, minValue).balance()
                        }
                    }
                }
            }
        }
        return Empty()
    }
    override public fun search(a : Int) : Boolean {
        if (this is Node) {
            return (a == this.value) || this.l.search(a) || this.r.search(a)
        }
        return false
    }
    internal fun <B> fold(fEmpty : B, fNode : (Int, B, B) -> B) : B {
        when (this) {
            is Empty -> { return fEmpty }
            is Node  -> { return fNode(this.value, this.l.fold(fEmpty, fNode),
                    this.r.fold(fEmpty, fNode)) }
        }
        return fEmpty
    }
    public fun toText() : List<String> {
        return fold(listOf("-\n")) { value: Int, lRes: List<String>, rRes: List<String> ->
            val lText = lRes.map { "| $it" }
            val rText = rRes.map { "| $it" }
            val vText = listOf("$value\n")
            lText + vText + rText
        }
    }
    override public fun union(t : MySet) : Tree {
        val list1 = t.setToList()
        val list2 = this.setToList()
        var res : Tree = Empty()
        for (i in list1) { res = res.insert(i) }
        for (i in list2) { res = res.insert(i) }
        return res
    }
    override public fun intersection(t : MySet) : Tree {
        val list = t.setToList()
        var res : Tree = Empty()
        for (i in list) {
            if (this.search(i)) {
                res = res.insert(i)
            }
        }
        return res
    }
    override public fun setToList() : List<Int> {
        when (this) {
            is Node -> {
                val left  = this.l.setToList()
                val right = this.r.setToList()
                return listOf(this.value) + left + right
            }
            else -> return listOf()
        }
    }
}

public class HashTable(protected val size : Int) : MySet {
    internal fun hash(a : Int) : Int = a % size
    internal var hashTable = Array(size, { ArrayList<Int>() })
    private inner class HashTableIterator() : Iterator<Int> {
        var i = 0
        private fun nextIterator() : Iterator<Int> {
            while (i < size - 1 && hashTable[i].isEmpty()) { i++ }
            if (i >= size - 1) { return EmptyIterator() }
            return hashTable[i].iterator()
        }
        inner class EmptyIterator() : Iterator<Int> {
            override fun hasNext() : Boolean = false
            override fun next() : Int = throw NoSuchElementException()
        }
        var iterator = nextIterator()
        override fun hasNext() : Boolean {
            when (iterator) {
                is EmptyIterator -> { return false }
            }
            if (iterator.hasNext()) { return true }
            iterator = nextIterator()
            return iterator.hasNext()
        }
        override fun next() : Int {
            if (!iterator.hasNext()) { iterator = nextIterator() }
            return iterator.next()
        }
    }
    override fun iterator() : Iterator<Int> = HashTableIterator()

    override public fun insert(a : Int) : HashTable {
        if (!this.search(a)) { hashTable[hash(a)].add(0, a) }
        return this
    }
    override public fun search(a : Int) : Boolean {
        return hashTable[hash(a)].contains(a)
    }
    override public fun delete(a : Int) : HashTable {
        if (this.search(a)) {
            val index = hashTable[hash(a)].indexOf(a)
            hashTable[hash(a)].remove(index) }
        return this
    }
    override public fun union(t : MySet) : HashTable {
        val list1 = t.setToList()
        val list2 = this.setToList()
        var res = HashTable(size)
        for (i in list1) { res.insert(i) }
        for (i in list2) { res.insert(i) }
        return res
    }
    override public fun intersection(t : MySet) : HashTable {
        val list = t.setToList()
        var res = HashTable(size)
        for (i in list) {
            if (this.search(i)) { res.insert(i) }
        }
        return res
    }
    override public fun setToString() : String {
        var res = ""
        for (i in hashTable) {
            if (i.size == 0) { res += "null" }
            else {
                for (j in i) { res = res + j + " " }
            }
            res += "\n"
        }
        return res
    }
    override public fun setToList() : List<Int> {
        var res: List<Int> = listOf()
        for (i in hashTable) {
            res += i
        }
        return res
    }
}

public fun main(args : Array<String>) {
   /* val tree = Node(5, Node(3, Node(1, Empty(), Empty()), Empty()), Empty())
    val hash = HashTable(3)
    hash.insert(0)
    hash.insert(1)
    hash.insert(2)
    hash.insert(3)
    print(hash)
    print(tree) */
}
