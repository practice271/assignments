package homeworks

open class AVLTree() {}

public class Node(var key: Int, var height: Int = 1): AVLTree() {
    private var left: Node?  = null
    private var right: Node? = null
    constructor(key: Int, nodeLeft: Node?, nodeRight: Node?, height: Int = 1) : this(key, height) {
        left  = nodeLeft
        right = nodeRight
    }

    private fun checkBalance(node: Node?): Int {
        return height(node?.right) - height(node?.left)
    }
    private fun height(node: Node?): Int {
        return node?.height ?: 0
    }
    private fun fixHeight(node: Node?) {
        var heightLeft  = height(node?.left)
        var heightRight = height(node?.right)
        node?.height = Math.max(heightLeft, heightRight) + 1
    }
    private fun balance(node: Node?): Node? {
        fixHeight(node)
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
    private fun rotateRight(node: Node?): Node? {
        var temp = node?.left
        node?.left = temp?.right
        temp?.right = node
        fixHeight(node)
        fixHeight(temp)
        return temp
    }
    private fun rotateLeft(node: Node?): Node? {
        var temp = node?.right
        node?.right = temp?.left
        temp?.left = node
        fixHeight(node)
        fixHeight(temp)
        return temp
    }

    public fun insert(value: Int) {
        insertNode(this, value)
    }
    private fun insertNode(node: Node?, value: Int) : Node? {
        if (node == null) { return Node(value, null, null) }
        if (value < node.key)
            { node.left = insertNode(node.left, value) }
        else
            { node.right = insertNode(node.right, value) }
        return balance(node)
    }

    private fun findMin(node: Node?): Node? {
        if (node?.left != null) { return findMin(node?.left) }
        else { return node }
    }
    private fun removeMin(node: Node?): Node? {
        if (node?.left == null) {
            return node?.right
        }
        node?.left = removeMin(node?.left)
        return balance(node)
    }
    public fun remove(value: Int) {
        removeNode(this, value)
    }
    private fun removeNode(node: Node?, key: Int): Node? {
        if (node == null) {
            return null
        }
        var temp = node?.key ?: throw Exception("Error")
        if (key < temp) {
            node?.left = removeNode(node?.left, key)
        } else if (key > temp) {
            node?.right = removeNode(node?.right, key)
        } else {
            var l = node?.left
            var r = node?.right
            if (r == null) return l
            var min = findMin(r)
            min?.right = removeMin(r)
            min?.left = l
            copyNode(node, balance(min)) //help method
            return node
        }
        return balance(node)
    }
    //help method, i don't know, how to do it in another
    private fun copyNode(node: Node?, min: Node?) {
        node?.key    = min?.key
        node?.height = min?.height
        node?.left   = min?.left
        node?.right  = min?.right
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

    public fun Search(value: Int): Boolean {
        if      (key < value) { return right?.Search(value) ?: false }
        else if (key > value) { return left ?.Search(value) ?: false }
        else                  { return true }
    }

    public override fun toString(): String {
        return "${this.left.toString()} ${this.key} ${this.right.toString()}"
    }

}


fun main(args: Array<String>) {
    var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
}

