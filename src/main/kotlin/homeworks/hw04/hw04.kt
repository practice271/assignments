package homeworks.hw04

import java.util.*

public interface AbstractSet<ValueT> where ValueT : Comparable<ValueT> {
    abstract public fun insert       (value: ValueT)
    abstract public fun delete       (value: ValueT)
    abstract public fun search       (value: ValueT): Boolean
    abstract public fun toList       (): ArrayList<ValueT>?
    abstract public fun union        (set: AbstractSet<ValueT>?): AbstractSet<ValueT>?
    abstract public fun intersection (set: AbstractSet<ValueT>?): AbstractSet<ValueT>?
}

public class AVLTree<ValueT> : AbstractSet<ValueT> where ValueT : Comparable<ValueT> {

    private var root: Node<ValueT>? = null

    override public fun insert(value: ValueT) {
        if (root == null) root = Node(value)
        else root?.insert(value)
    }

    override public fun delete(value: ValueT){
        if (root != null) root?.delete(value)
    }

    override public fun search(value: ValueT): Boolean {
        return root?.search(value) ?: false
    }

    override public fun toList(): ArrayList<ValueT>? {
        return root?.toList() ?: ArrayList()
    }

    override public fun union(set: AbstractSet<ValueT>?): AbstractSet<ValueT>? {
        val rootList = root?.toList()
        val setList  = set ?.toList()
        var res   = AVLTree<ValueT>()

        rootList?.forEach {
            res.insert(it)
        }
        setList?.forEach {
            if (res.search(it) == false) res.insert(it)
        }
        return res
    }

    override public fun intersection(set: AbstractSet<ValueT>?): AbstractSet<ValueT>? {
        val rootList = root?.toList()
        var res   = AVLTree<ValueT>()

        rootList?.forEach {
            if (set?.search(it) == true) res.insert(it)
        }
        return res
    }

    override public fun toString(): String {
        return root.toString()
    }

}

public class HashTable<ValueT>(val arraySize: Int) : AbstractSet<ValueT> where ValueT : Comparable<ValueT> {
    private var table = Array(arraySize, { i -> ArrayList<ValueT>()})
    private fun getHashCode(key: ValueT) = key.hashCode() % arraySize

    override public fun insert(value: ValueT) {
        table[getHashCode(value)].add(value)
    }

    override public fun delete(value: ValueT){
        table[getHashCode(value)].remove(value)
    }

    override public fun search(value: ValueT): Boolean {
        return table[getHashCode(value)].contains(value)
    }

    override public fun toList(): ArrayList<ValueT>? {
        var res = ArrayList<ValueT>()
        table.forEach {
            res.addAll(it)
        }
        return res
    }

    override public fun union(set: AbstractSet<ValueT>?): AbstractSet<ValueT>? {
        val tableList = table.toList()
        val setList   = set ?.toList()
        var res       = HashTable<ValueT>(arraySize)

        tableList.forEach {
            it.forEach {
                res.insert(it)
            }
        }

        setList?.forEach {
            if (res.search(it) == false) res.insert(it)
        }
        return res
    }

    override public fun intersection(set: AbstractSet<ValueT>?): AbstractSet<ValueT>? {

        val tableList = table.toList()
        var res   = HashTable<ValueT>(arraySize)

        tableList.forEach {
            it.forEach {
                if (set?.search(it) == true) res.insert(it)
            }
        }
        return res
    }

    override public fun toString(): String {
        var result = ""
        table.forEach {
            it.forEach {
                if (it != 0) result += " $it"
            }
        }
        return result
    }
}

public class Node<ValueT>(var key: ValueT) where ValueT : Comparable<ValueT> {
    private var left: Node<ValueT>?  = null
    private var right: Node<ValueT>? = null
    private var height_f : Int = calcHeight()
    constructor(key: ValueT, nodeLeft: Node<ValueT>?, nodeRight: Node<ValueT>?) : this(key) {
        left  = nodeLeft
        right = nodeRight
        installHeightInAllTree(this, 1)
    }
    private fun installHeightInAllTree(node: Node<ValueT>?, value: Int) {
        if (node == null) return
        node?.height_f = value
        installHeightInAllTree(node?.left, value + 1)
        installHeightInAllTree(node?.right, value + 1)
    }

    private fun calcHeight() : Int =
            1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)

    public fun height(): Int = height_f

    private fun checkBalance(node: Node<ValueT>?): Int {
        val r = node?.right?.height_f ?: 0
        val l = node?.left ?.height_f ?: 0
        return r - l
    }

    private fun balance(node: Node<ValueT>?): Node<ValueT>? {
        node?.calcHeight()
        if (checkBalance(node) == 2) {
            if (checkBalance(node?.right) < 0) {
                node?.right = rotateRight(node?.right)
            }
            return rotateLeft(node)
        }
        if (checkBalance(node?.left) == -2) {
            if (checkBalance(node?.left) > 0) {
                node?.left = rotateLeft(node?.left)
            }
            return rotateRight(node)
        }
        return node
    }
    private fun rotateRight(node: Node<ValueT>?): Node<ValueT>? {
        var temp = node?.left
        node?.left = temp?.right
        temp?.right = node
        node?.calcHeight()
        temp?.calcHeight()
        return temp
    }
    private fun rotateLeft(node: Node<ValueT>?): Node<ValueT>? {
        var temp = node?.right
        node?.right = temp?.left
        temp?.left = node
        node?.calcHeight()
        temp?.calcHeight()
        return temp
    }

    public fun insert(key: ValueT) {
        insertNode(this, key)
    }
    private fun insertNode(node: Node<ValueT>?, key: ValueT) : Node<ValueT>? {
        if (node == null) { return Node(key, null, null) }
        when {
            key < node.key -> node.left = insertNode(node.left, key)
            else           -> node.right = insertNode(node.right, key)
        }
        return balance(node)
    }

    private fun findMin(node: Node<ValueT>?): Node<ValueT>? {
        if (node?.left != null) { return findMin(node?.left) }
        else { return node }
    }
    private fun deleteMin(node: Node<ValueT>?): Node<ValueT>? {
        if (node?.left == null) { return node?.right }
        node?.left = deleteMin(node?.left)
        return balance(node)
    }
    public fun delete(value: ValueT) {
        deleteNode(this, value)
    }
    private fun deleteNode(node: Node<ValueT>?, key: ValueT): Node<ValueT>? {
        fun copyNode(node: Node<ValueT>?, min: Node<ValueT>?) {
            node?.key    = min?.key
            node?.height_f = min?.height_f
            node?.left   = min?.left
            node?.right  = min?.right
        }

        if (node == null) { return null }
        var temp = node.key
        when {
            key < temp -> node.left = deleteNode(node.left, key)
            key > temp -> node.right = deleteNode(node.right, key)
            else       -> {
                var l = node.left
                var r = node.right
                if (r == null) return l
                var min = findMin(r)
                min?.right = deleteMin(r)
                min?.left = l
                copyNode(node, balance(min))
                return node
            }
        }
        return balance(node)
    }

    public fun printTree(count: Int){
        this.right?.printTree(count + 4)
        var temp = this.key.toString()
        for (i in 0..count) {
            temp = " " + temp
        }
        println(temp)
        this.left?.printTree(count + 4)
    }

    public fun search(value: ValueT): Boolean {
        when {
            key < value -> return right?.search(value) ?: false
            key > value -> return left ?.search(value) ?: false
            else        -> return true
        }
    }

    public override fun toString(): String {
        return "${this.left.toString()} ${this.key} ${this.right.toString()}"
    }

    public fun toList(): ArrayList<ValueT>? {
        fun addInList(node: Node<ValueT>?, list: ArrayList<ValueT>): ArrayList<ValueT> {
            if (node == null) return list
            else {
                val resultList = addInList(node.left, list)
                resultList.add(node.key)
                return addInList(node.right, resultList)
            }
        }
        return addInList(this, ArrayList<ValueT>())
    }

}
fun main(args: Array<String>) {

    var tree: Node<Int>? = Node(5,
            Node<Int>(3, Node<Int>(2, null, null), Node<Int>(4, null, null)),
            Node<Int>(7, Node<Int>(6, null, null), Node<Int>(8, null, null))
    )
    //tree?.insert(10)
    //print(tree.toString())
}
