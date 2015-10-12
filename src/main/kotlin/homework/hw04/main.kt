// Homework 4
// Alekseev Aleksei, group 271.

package homework.hw04

import java.util.*

interface Set {
    public fun insertion (value : Int)
    public fun deletion (value : Int)
    public fun search (value : Int) : Boolean
    public fun toList() : List<Int>
    public fun union(set : Set) : Set
    public fun intersection(set : Set): Set
    public fun isEmpty(): Boolean
}

public class AVLTree : Set {
    class Node (val value : Int, var left_param : Node?, var right_param : Node? ) {
        public var left : Node? = left_param
            get() = $left
            set(newLeft : Node?) {
                $left = newLeft
                height_f = calcHeight()
            }
        public var right : Node? = right_param
            get() = $right
            set(newRight : Node?) {
                $right = newRight
                height_f = calcHeight()
            }
        private fun calcHeight() : Int =
                1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
        private var height_f : Int = calcHeight()
        fun height() : Int = height_f
    }

    private fun balanceFactor(node : Node?) : Int {
        return (node?.right?.height() ?: 0) - (node?.left?.height() ?: 0)
    }

    private fun rotateLeft(node : Node?) : Node? {
        var temp = node?.right
        node?.right = temp?.left
        temp?.left = node
        return temp
    }

    private fun rotateRight(node : Node?) : Node? {
        var temp = node?.left
        node?.left = temp?.right
        temp?.right = node
        return temp
    }

    public fun balance(node : Node?) : Node? {
        when {
            balanceFactor(node)  == 2 -> {
                if (balanceFactor(node?.right) < 0) node?.right = rotateRight(node?.right)
                return rotateLeft(node)
            }
            balanceFactor(node) == -2 -> {
                if (balanceFactor(node?.left) > 0) node?.left = rotateLeft(node?.left)
                return rotateRight(node)
            }
        }
        return node
    }

    private fun insert(key : Int, node : Node?) : Node? {
        when {
            node == null     -> return Node(key, null, null)
            key < node.value -> node.left = insert(key , node.left)
            key > node.value -> node.right = insert(key , node.right)
        }
        return balance(node)
    }

    private fun findMin(node : Node?) : Node? {
        if (node?.left == null)
            return node
        else
            return findMin(node?.left)
    }

    private fun removeMin(node : Node?) : Node? {
        if (node?.left == null) return node?.right
        else {
            node?.left = removeMin(node?.left)
            return balance(node)
        }
    }

    private fun remove(key : Int, node : Node?) : Node? {
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

    private fun find(key : Int, node : Node?) : Boolean {
        when {
            node == null      -> return false
            key < node.value  -> return find(key, node.left)
            key > node.value  -> return find(key, node.right)
            else              -> return true
        }
    }

    private var tree : Node? = null

    override fun insertion(value : Int) {
        tree = insert(value, tree)
    }

    override fun deletion(value : Int) {
        tree = remove(value, tree)
    }

    override fun search(value : Int) : Boolean {
        return find(value, tree)
    }

    override fun toList() : List<Int> {
        fun Node.toList() : List<Int> {
            return listOf(value) +  (left?.toList() ?: listOf()) + (right?.toList() ?: listOf())
        }
        return tree?.toList() ?: listOf()
    }

    override public fun isEmpty() : Boolean = tree == null

    override fun union(set : Set) : Set {
        val temp : Set = set
        if (isEmpty()) return temp
        if (set.isEmpty()) return this
        toList().forEach {
            if (!set.search(it)) temp.insertion(it)
        }
        return temp
    }

    override fun intersection(set : Set) : Set {
        val temp : Set = AVLTree()
        if (isEmpty()) return set
        if (set.isEmpty()) return temp
        toList().forEach {
            if (set.search(it)) temp.insertion(it)
        }
        return temp
    }
}

public class HashTable(val size: Int) : Set {
    public val table = Array(size, { LinkedList<Int>() })

    override fun insertion(value : Int) {
        table[value % size].add(value)
    }

    override fun deletion(value : Int) {
        table.forEach {
            it.removeFirstOccurrence(value)
        }
    }

    override fun search(value: Int): Boolean = table[value % size].indexOf(value) != -1

    override fun toList() : List<Int> {
        var temp = emptyList<Int>()
        table.forEach {
            temp += it
        }
        return temp
    }

    override fun isEmpty(): Boolean = toList() == emptyList<Int>()

    override fun union(set : Set) : Set {
        val temp : Set = set
        if (isEmpty()) return temp
        if (set.isEmpty()) return this
        toList().forEach {
            if (!set.search(it)) temp.insertion(it)
        }
        return temp
    }

    override fun intersection(set : Set) : Set {
        val temp : Set = HashTable(size)
        if (isEmpty()) return set
        if (set.isEmpty()) return temp
        toList().forEach {
            if (set.search(it)) temp.insertion(it)
        }
        return temp
    }
}