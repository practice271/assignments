package homework.hw08

import java.util.*

abstract class Tree() : Iterable<Int> {

    override fun iterator(): Iterator<Int>{return this.iterator()}
    abstract internal var height: Int
}

class Empty : Tree() {
    override fun iterator(): Iterator<Int> {return EmptyIterator()
    }
    override var height = 0

    private class EmptyIterator() : Iterator<Int> {
        override fun hasNext(): Boolean = false
        override fun next(): Int {
            throw NoSuchElementException()
        }
    }
}

class Node(val value : Int, val l : Tree, val r: Tree) : Tree() {

    override fun iterator(): Iterator<Int> {return LCRNodeIterator(this)}
    override var height = fixHeight()

    private abstract class NodeIterator(protected val node: Node): Iterator<Int> {
        protected val lIterator =  node.l.iterator()
        protected val rIterator = node.r.iterator()
        protected var  observed: Boolean = false
        protected var lHasNext: Boolean = true
            get() =
            if (field) { field = lIterator.hasNext(); field } else false
        protected var rHasNext: Boolean = true
            get() =
            if (field) { field = rIterator.hasNext(); field } else false

        override fun hasNext(): Boolean {
            if (!observed) { return true }
            if (lHasNext ) { return true }
            if (rHasNext ) { return true }
            return false
        }
    }


    private class LCRNodeIterator(node: Node): NodeIterator(node) {

        override fun next(): Int {
            if (lHasNext ) { return lIterator.next() }
            if (!observed) { observed = true; return node.value }
            if (rHasNext ) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    private class CLRNodeIterator(node: Node): NodeIterator(node) {

        override fun next(): Int {
            if (!observed) { observed = true; return node.value }
            if (lHasNext ) { return lIterator.next() }
            if (rHasNext ) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    private class LRCNodeIterator(node: Node): NodeIterator(node) {

        override fun next(): Int {
            if (lHasNext ) { return lIterator.next() }
            if (rHasNext ) { return rIterator.next() }
            if (!observed) { observed = true; return node.value }
            throw NoSuchElementException()
        }
    }
}

fun Tree.height() : Int {
    when (this) {
        is Empty -> return 0
        is Node -> return height
        else ->  throw Exception("Error")
    }
}

private fun Tree.value():Int {
    when (this) {
        is Node -> return value
        is Empty -> return 0
        else -> throw Exception ("Error")
    }
}

private fun bfactor(tree : Tree) : Int {
    when (tree) {
        is Node -> return (tree.l).height() - (tree.r).height()
        is Empty -> return 0
        else -> throw Exception("Error")
    }
}

private fun Tree.fixHeight() : Int{
    when (this) {
        is Node -> return Math.max(l.fixHeight()?:0, r.fixHeight()?:0) + 1
        is Empty -> return 0
        else -> throw Exception("Error")
    }
}

fun Tree.toText() : String {
    fun Tree.toText_(): List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Node -> {
                val lText = l.toText_().map { "  $it" }
                val rText = r.toText_().map { "  $it" }
                val vText = listOf("$value\n")
                return rText + vText + lText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

private fun Tree.l() : Tree {
    when (this) {
        is Node -> return l
        is Empty -> return Empty()
        else -> throw Exception("Error")
    }
}

private fun Tree.r() : Tree {
    when (this) {
        is Node -> return r
        is Empty -> return Empty()
        else -> throw Exception("Error")
    }
}

private fun Tree.leftRotation() : Tree {

    val temp = Node(this.value(), l().r(), r())
    val temp2 = Node(l().value(), l().l(), temp)
    temp.fixHeight()
    temp2.fixHeight()
    return temp2
}

private fun Tree.rightRotation() : Tree {
    val temp = Node(this.value(), l(), r().l())
    val temp2 = Node(r().value(), temp, r().r())
    temp.fixHeight()
    temp2.fixHeight()
    return temp2
}

private fun Tree.balance() : Tree {
    fixHeight()
    if (bfactor(this) == 2) {
        if (bfactor(r()) < 0) {
            val temp = r().rightRotation()
            return temp.leftRotation()
        }
        return this.leftRotation()
    }
    if (bfactor(this) == -2) {
        if (bfactor(l()) > 0) {
            val temp = l().leftRotation()
            return temp.rightRotation()

        }
        return this.rightRotation()
    }
    return this
}

fun Tree.Insert(v : Int) : Tree {
    when (this) {
        is Empty -> return Node(v, Empty(), Empty())
        is Node -> {
            when {
                v < value -> return Node(value, l.Insert(v), r).balance()
                v > value -> return Node(value, l, r.Insert(v)).balance()
                else -> return this
            }
        }
        else -> throw Exception("Error")
    }
}

private fun Tree.lastLeft() :Int {
    when (this) {
        is Node ->
            when (this.l) {
                is Empty -> return value
                is Node -> return l.lastLeft()
                else -> throw Exception("Error")
            }
        is Empty -> return 0
        else -> throw Exception ("Error")
    }
}

fun Tree.Delete(v : Int) : Tree {
    when (this) {
        is Empty -> return Empty()
        is Node -> {
            when {
                value < v -> return Node(value, l, r.Delete(v)).balance()
                value > v -> return Node(value, l.Delete(v), r).balance()
            }
            when (l) {is Empty -> return r}
            when (r) {is Empty -> return l}
            when (r.l()) {
                is Empty -> return Node(r.value(), l, r.r()).balance()
                is Node -> {
                    val temp = r.l().lastLeft()
                    return Node(temp, l, r.Delete(temp)).balance()
                }
                else -> throw Exception("Error")
            }
        }
        else -> throw Exception("Error")
    }
}

fun Tree.Search(v : Int) : Boolean {
    when (this) {
        is Empty -> return false
        is Node -> {
            if (value == v) return true
            if (value > v) return this.l.Search(v)
            else return r.Search(v)
        }
        else -> throw Exception("Error")
    }
}

fun Tree.toArray() : Array<Int> {
    val temp = ArrayList<Int>()
    val res = arrayOf(Int)
    fun CLR(tree: Tree) {
        when (tree) {
            is Empty -> 0
            is Node -> {
                temp.add(0, tree.value)
                CLR(tree.l)
                CLR(tree.r)
            }
            else -> throw Exception("Errro")
        }
    }
    CLR(this)
    return temp.toTypedArray()
}