/**
 * Example for pattern "iterator"
 *
 * Author: Mikhail Kita, group 271
 */

abstract class Tree {
    public fun size() : Int {
        when(this) {
            is Node -> return 1 + this.left.size() + this.right.size()
            else    -> return 0
        }
    }
}

abstract class Iterator() {
    abstract public fun first()
    abstract public fun next()
    abstract public fun isDone() : Boolean
    abstract public fun currentItem() : Int
}

class Empty() : Tree() {}

// it's syntax sugar for Node(value, ListEmpty(), ListEmpty())
class Leaf(val _value : Int) : Node(_value, Empty(), Empty()) {}

open class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {
    public fun createCLRIterator() : Iterator {
        return CLRIterator(this)
    }

    public fun createLCRIterator() : Iterator {
        return LCRIterator(this)
    }

    public fun createLRCIterator() : Iterator {
        return LRCIterator(this)
    }
}

open class TreeIterator(val tree : Tree) : Iterator() {
    private var index = 0
    private val elem  = toList(tree)

    open public fun toList(t : Tree) : List<Int> {
        when (t) {
            is Node -> {
                val left = toList(t.left)
                val right = toList(t.right)

                val list = listOf(t.value).toLinkedList()
                for (i in left) list.add(i)
                for (i in right) list.add(i)

                return list
            }
            else    -> return listOf()
        }
    }

    public override fun first() {
        index = 0
    }

    public override fun next() {
        index++
    }

    public override fun isDone() : Boolean {
        return (index == tree.size())
    }

    public override fun currentItem() : Int {
        return elem[index]
    }
}

class CLRIterator(val _tree : Tree) : TreeIterator(_tree) {}

class LCRIterator(val _tree : Tree) : TreeIterator(_tree) {
    public override fun toList(t : Tree) : List<Int> {
        when (t) {
            is Node -> {
                val left = toList(t.left)
                val right = toList(t.right)

                val list = left.toLinkedList()
                list.add(t.value)
                for (i in right) list.add(i)

                return list
            }
            else    -> return listOf()
        }
    }
}

class LRCIterator(val _tree : Tree) : TreeIterator(_tree) {
    public override fun toList(t : Tree) : List<Int> {
        when (t) {
            is Node -> {
                val left = toList(t.left)
                val right = toList(t.right)

                val list = left.toLinkedList()
                for (i in right) list.add(i)
                list.add(t.value)

                return list
            }
            else   -> return listOf()
        }
    }
}

fun main (args : Array<String>) {
    val tree = Node(10, Leaf(5), Node(15, Leaf(13), Node(100, Leaf(17), Empty())))

    val iter = tree.createCLRIterator()
    while (!iter.isDone()) {
        println(iter.currentItem())
        iter.next()
    }
}