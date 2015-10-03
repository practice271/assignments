package hw03

/**
 * Created by Zarina Kurbatova on 02/10/15.
 */

open class AVLTree() {
    public var root: Node? = null
}

public class Node (var value: Int, var height: Int = 1): AVLTree() {
    var leftChild: Node? = null
    var rightChild: Node? = null

    constructor(value: Int, leftChild_: Node?, rightChild_: Node?, height: Int = 1) : this(value, height) {
        leftChild = leftChild_
        rightChild = rightChild_
    }


    private fun height(node: Node?): Int {
        return node?.height ?: 0
    }

    private fun fixHeight(node: Node?) {
        var l = height(node?.leftChild)
        var r = height(node?.rightChild)
        node?.height = Math.max(l, r) + 1
    }

    private fun checkBalanceFactor(node: Node?): Int {
        return (height(node?.rightChild) - height(node?.leftChild))
    }

    private fun rotateRight(node: Node?): Node? {
        var tmp: Node? = node?.leftChild
        node?.leftChild = tmp?.rightChild
        tmp?.rightChild = node
        fixHeight(node)
        fixHeight(tmp)
        return tmp
    }

    private fun rotateLeft(node: Node?): Node? {
        var tmp: Node? = node?.rightChild
        node?.rightChild = tmp?.leftChild
        tmp?.leftChild = node
        fixHeight(node)
        fixHeight(tmp)
        return tmp
    }


    private fun balanceFactor(node: Node?): Node? {
        fixHeight(node)
        when (checkBalanceFactor(node)) {
            2 -> if (checkBalanceFactor(node?.rightChild) < 0) {
                node?.rightChild = rotateRight(node?.rightChild)
                return rotateLeft(node)
            }

            (-2) -> if (checkBalanceFactor(node?.leftChild) > 0) {
                node?.leftChild = rotateLeft(node?.leftChild)
                return rotateRight(node)
            }
        }
        return node
    }

    private fun insert(node: Node?, key: Int): Node? {
        if (node == null)
            return Node(key, null, null)
        if (key < node.value) {
            node.leftChild = insert(node.leftChild, key)
        } else {
            node.rightChild = insert(node.rightChild, key)
        }
        return balanceFactor(node)
    }

    public fun insertNode(value: Int) {
        insert(this, value)
    }


    private fun findMin(node: Node?): Node? {
        if (node?.leftChild != null)
            return findMin(node?.leftChild)
        else return node
    }

    private fun removeMin(node: Node?): Node? {
        if (node?.leftChild == null) {
            return node?.rightChild
        }
        node?.leftChild = removeMin(node?.leftChild)
        return balanceFactor((node))
    }

    private fun remove(node: Node?, key: Int): Node? {
        if (node == null) return null

        if ( key < node.value) {
            node.leftChild = remove(node.leftChild, key)
        }

        if ( key > node.value) {
            node.rightChild = remove(node.rightChild, key)
        } else {
            var x: Node? = node.leftChild
            var y: Node? = node.rightChild
            if (y == null) return x
            var min: Node? = findMin(y)
            min?.rightChild = removeMin(y)
            min?.leftChild = x
            return balanceFactor(min)
        }
        return balanceFactor(node)
    }

    public fun removeNode(value: Int) {
        remove(this, value)
    }

    public fun search(key: Int): Boolean {
        if (key == value) return true
        if (key < value ) return leftChild?.search(key) ?: false
        else return rightChild?.search(key) ?: false
    }

    public fun printTree(): String {
        fun Node.printTree_(): List <String> {
            val lText = leftChild?.printTree_()?.map { "| $it" } ?: listOf("| _\n")
            val rText = rightChild?.printTree_()?.map { "| $it" } ?: listOf("| _\n")
            val vText = listOf("$(value)\n")
            return lText + vText + rText
        }

        val builder = StringBuilder()
        val lines = root?.printTree_() ?: listOf("_\n")
        lines.forEach { builder.append(it) }
        return builder.toString()
    }

    public fun toString_(): String {
        var lText = leftChild?.toString_()
        var rText = rightChild?.toString_()
        var vText = value.toString()
        return lText + " " + vText + " " + rText
    }
}

