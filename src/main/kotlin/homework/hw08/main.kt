// Homework 8
// Alekseev Aleksei, group 271.

package homework.hw08

import java.util.*

interface Set<A : Comparable<A>> {
    public fun insertion (value : A)
    public fun deletion (value : A)
    public fun search (value : A) : Boolean
    public fun toList() : List<A>
    public fun union(set : Set<A>) : Set<A>
    public fun intersection(set : Set<A>): Set<A>
    public fun isEmpty(): Boolean
}

public class AVLTree<A : Comparable<A>>() : Set<A>, Iterable<A> {
    class Node<A : Comparable<A>>(val value: A, var left_param: Node<A>?, var right_param: Node<A>?) : Iterable<A>{
        public var left: Node<A>? = left_param
            get() = field
            set(newLeft: Node<A>?) {
                field = newLeft
                height_f = calcHeight()
            }

        public var right: Node<A>? = right_param
            get() = field
            set(newRight: Node<A>?) {
                field = newRight
                height_f = calcHeight()
            }
        override fun iterator(): Iterator<A> = LCRNodeIterator(this)
        private fun calcHeight() : Int =
                1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
        private var height_f : Int = calcHeight()
        fun height(): Int = height_f
    }

    private class EmptyIterator<A>(): Iterator<A> {
        override fun hasNext(): Boolean = false
        override fun next(): A { throw NoSuchElementException() }
    }

    private abstract class NodeIterator<A: Comparable<A>>(
            protected val node: Node<A>
    ): Iterator<A> {
        protected val lIterator: Iterator<A> =
                node.left?.iterator() ?: EmptyIterator()
        protected val rIterator: Iterator<A> =
                node.right?.iterator() ?: EmptyIterator()
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

    private class LCRNodeIterator<A: Comparable<A>>(
            node: Node<A>
    ): NodeIterator<A>(node) {
        override fun next(): A {
            if (lHasNext ) { return lIterator.next() }
            if (!observed) { observed = true; return node.value }
            if (rHasNext ) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    override fun iterator(): Iterator<A> =
            root?.iterator() ?: EmptyIterator()
    private fun getBalanceFactor(node : Node<A>?):Int{
        return (node?.right?.height() ?:0) - (node?.left?.height() ?:0)
    }

    var root: Node<A>? = null

    private fun rotateLeft(node : Node<A>?) : Node<A>? {
        var temp = node?.right
        node?.right = temp?.left
        temp?.left = node
        return temp
    }

    private fun rotateRight(node : Node<A>?) : Node<A>? {
        var temp = node?.left
        node?.left = temp?.right
        temp?.right = node
        return temp
    }

    public fun balance(node : Node<A>?) : Node<A>? {
        when {
            getBalanceFactor(node)  == 2 -> {
                if (getBalanceFactor(node?.right) < 0) node?.right = rotateRight(node?.right)
                return rotateLeft(node)
            }
            getBalanceFactor(node) == -2 -> {
                if (getBalanceFactor(node?.left) > 0) node?.left = rotateLeft(node?.left)
                return rotateRight(node)
            }
        }
        return node
    }

    private fun insert(key : A, node : Node<A>?) : Node<A>? {
        when {
            node == null     -> return Node(key, null, null)
            key < node.value -> node.left = insert(key , node.left)
            key > node.value -> node.right = insert(key , node.right)
        }
        return balance(node)
    }

    private fun findMin(node : Node<A>?) : Node<A>? {
        if (node?.left == null)
            return node
        else
            return findMin(node?.left)
    }

    private fun removeMin(node : Node<A>?) : Node<A>? {
        if (node?.left == null) return node?.right
        else {
            node?.left = removeMin(node?.left)
            return balance(node)
        }
    }

    private fun remove(key : A, node : Node<A>?) : Node<A>? {
        when {
            node == null     -> return null
            key < node.value -> node.left = remove(key, node.left)
            key > node.value -> node.right = remove(key, node.right)
            else             -> {
                var tempLeft = node.left
                var tempRight = node.right ?: return tempLeft
                var nodeMin = findMin(tempRight)
                nodeMin?.right = removeMin(tempRight)
                nodeMin?.left = tempLeft
                return balance(nodeMin)
            }
        }
        return balance(node)
    }

    private fun find(key : A, node : Node<A>?) : Boolean {
        when {
            node == null      -> return false
            key < node.value  -> return find(key, node.left)
            key > node.value  -> return find(key, node.right)
            else              -> return true
        }
    }

    private var tree : Node<A>? = null

    override fun insertion(value : A) {
        tree = insert(value, tree)
    }

    override fun deletion(value : A) {
        tree = remove(value, tree)
    }

    override fun search(value : A) : Boolean {
        return find(value, tree)
    }

    override fun toList() : List<A> {
        fun Node<A>.toList() : List<A> {
            return listOf(value) +  (left?.toList() ?: listOf()) + (right?.toList() ?: listOf())
        }
        return tree?.toList() ?: listOf()
    }

    override public fun isEmpty() : Boolean = tree == null

    override fun union(set : Set<A>) : Set<A> {
        val temp : Set<A> = set
        if (isEmpty()) return temp
        if (set.isEmpty()) return this
        toList().forEach {
            if (!set.search(it)) temp.insertion(it)
        }
        return temp
    }

    override fun intersection(set : Set<A>) : Set<A> {
        val temp : Set<A> = AVLTree()
        if (isEmpty()) return set
        if (set.isEmpty()) return temp
        toList().forEach {
            if (set.search(it)) temp.insertion(it)
        }
        return temp
    }
}

public class HashTable<A : Comparable<A>>(val size: Int) : Set<A>, Iterable<A> {
    public val table = Array(size, { LinkedList<A>() })

    public override fun iterator() : MutableIterator<A> {
        return object : MutableIterator<A> {
            var index1 = 0
            var index2 = 0
            public override fun hasNext() : Boolean {
                return (index1 != size)
            }
            public override fun next() : A {
                while (table[index1].size == 0) index1++
                val temp = table[index1]
                if (index2 >= temp.size - 1) {
                    index1++
                    index2 = 0
                    return temp[temp.size - 1]
                }
                index2++
                return temp[index2 - 1]
            }
            public override fun remove() {}
        }
    }

    override fun insertion(value : A) {
        table[value.hashCode() % size].add(value)
    }

    override fun deletion(value : A) {
        table.forEach {
            it.removeFirstOccurrence(value)
        }
    }

    override fun search(value: A): Boolean = table[value.hashCode() % size].indexOfRaw(value) != -1

    override fun toList() : List<A> {
        var temp = LinkedList<A>()
        for (i in table) temp.addAll(i)
        return temp
    }

    override fun isEmpty(): Boolean = toList() == emptyList<Int>()

    override fun union(set : Set<A>) : Set<A> {
        val temp : Set<A> = set
        if (isEmpty()) return temp
        if (set.isEmpty()) return this
        toList().forEach {
            if (!set.search(it)) temp.insertion(it)
        }
        return temp
    }

    override fun intersection(set : Set<A>) : Set<A> {
        val temp : Set<A> = HashTable(size)
        if (isEmpty()) return set
        if (set.isEmpty()) return temp
        toList().forEach {
            if (set.search(it)) temp.insertion(it)
        }
        return temp
    }
}
