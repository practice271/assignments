package homework.hw04

import java.util.*
import kotlin.test.assertEquals

interface Set<A : Comparable<A>>: Iterable<A>{
    fun insertion(value:A)
    fun deletion(value:A)
    fun search(value:A):Boolean
    fun union(set:Set<A>):Set<A>
    fun intersection(set:Set<A>):Set<A>
    fun toList():List<A>
}

public class AVLTree<A : Comparable<A>>(): Set<A>, Iterable<A>{
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

    private fun turnRight(node : Node<A>?):Node<A>?{
        var tmp     = node?.left
        node?.left  = tmp?.right
        tmp?.right  = node
        return tmp
    }

    private fun turnLeft(node : Node<A>?):Node<A>?{
        var tmp     = node?.right
        node?.right = tmp?.left
        tmp?.left   = node
        return tmp
    }

    private fun balance(node : Node<A>?):Node<A>?{
        when {
            getBalanceFactor(node)  == 2 -> {
                if (getBalanceFactor(node?.right) < 0) node?.right = turnRight(node?.right)
                return turnLeft(node)
            }

            getBalanceFactor(node) == -2 -> {
                if (getBalanceFactor(node?.left) > 0) node?.left = turnLeft(node?.left)
                return turnRight(node)
            }
        }
        return node
    }

    fun insert(key: A , node : Node<A>?):Node<A>?{
        when {
            node == null     -> return Node(key, null, null)
            key < node.value -> node.left = insert(key , node.left)
            else             -> node.right = insert(key , node.right)
        }
        return balance(node)
    }

    private fun findMin(node : Node<A>?):Node<A>?{
        if (node?.left == null)
            return node
        else
            return findMin(node?.left)
    }

    private fun removeMin(node : Node<A>?):Node<A>?{
        if (node?.left==null) return node?.right
        else{
            node?.left = removeMin(node?.left)
            return balance(node)
        }
    }

    private fun remove(key:A, node : Node<A>?):Node<A>?{
        when{
            node == null     -> return null
            key < node.value -> {
                node.left = remove(key, node.left)
            }
            key > node.value -> {
                node.right = remove(key, node.right)
            }
            else             -> {
                var tmpLeft  = node.left
                var tmpRight = node.right ?: return tmpLeft
                var nodeMin   = findMin(tmpRight)
                nodeMin?.right = removeMin(tmpRight)
                nodeMin?.left  = tmpLeft
                return balance(nodeMin)
            }
        }
        return balance(node)
    }

    private fun find(key:A,node: Node<A>?):Boolean{
        when{
            node == null      -> return false
            key < node.value  -> return find(key, node.left)
            key > node.value  -> return find(key, node.right)
            else              -> return true
        }
    }

    private fun Node<A>?.treeToText():String{
        if (this == null) return "null"
        var leftStr: String
        var rightStr: String
        if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
        if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
        return leftStr + "," + value.toString() + "," + rightStr
    }

    //AVLTree overriding

    private var elem:Node<A>? = null
    //overriding AVLTree methods
    override fun insertion(value:A){
        elem = insert(value,elem)
    }

    override fun deletion(value:A){
        elem = remove(value,elem)
    }

    override fun search(value:A):Boolean{
        return find(value, elem)
    }

    override fun toList(): List<A> {
        fun Node<A>.toList() : List<A> {
            val leftList  = left?.toList() ?: listOf()
            val rightList = right?.toList() ?: listOf()
            val nodeList  = listOf(value)
            return leftList + rightList + nodeList
        }
        return elem?.toList() ?: listOf()
    }

    override fun union(set:Set<A>):AVLTree<A>{
        var result = this
        set.toList().forEach { if (!search(it)) result.insertion(it) }
        return result
    }

    override fun intersection(set:Set<A>):AVLTree<A>{
        var result = AVLTree<A>()
        set.toList().forEach { if (search(it)) result.insertion(it)}
        return result
    }
}

//HashTable overriding

public class HashTable<A : Comparable<A>>(val size:Int) :Set<A>{
    //Creating HashTable
    public val HashTable = Array(size, { LinkedList<A>() })

    private fun Hash(value: A): Int{
        if (value is Int){
            return value % size
        }
        return value.hashCode()
    }

    override fun iterator() : Iterator<A> {return HashTableIterator()}

    private inner class HashTableIterator() : Iterator<A> {
        private var pointer = -1
        private var visited = 0

        override fun hasNext() : Boolean = pointer != visited

        public override fun next() : A {
            while (HashTable[pointer].size == 0) pointer++
            val temp = HashTable[pointer]
            if (visited >= temp.size - 1) {
                pointer++
                visited = 0
                return temp[temp.size - 1]
            }
            visited++
            return temp[visited - 1]
        }
    }

    override fun toList(): List<A> {
        var result = listOf<A>()
        HashTable.forEach { result+=it }
        return result
    }

    override fun deletion(value: A) {
        HashTable.forEach { it.removeFirstOccurrence(value) }
    }

    override fun insertion(value: A) {
        HashTable[Hash(value)].add(value)
    }

    override fun search(value: A): Boolean {
        if (HashTable[Hash(value)].indexOf(value)!= -1) return true
        return false
    }

    override fun union(set: Set<A>): Set<A> {
        val result = set
        toList().forEach { if (!set.search(it)) result.insertion(it) }
        return result
    }

    override fun intersection(set: Set<A>): Set<A> {
        var result = HashTable<A>(size)
        toList().forEach { if (set.search(it)) result.insertion(it) }
        return result
    }
}