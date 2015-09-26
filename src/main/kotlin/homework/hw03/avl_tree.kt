// Homework #03 (22.09 - 06.10)
// Author: Kirill Smirenko, group 271
package homework.hw03

class Node<K : Comparable<K>, V>(key: K, value : V, left: Node<K, V>?, right: Node<K, V>?) {
    var key: K = key
    var value: V = value
    var left: Node<K, V>? = left
    var right: Node<K, V>? = right

    fun getHeight() : Int = 1 + Math.max(left?.getHeight() ?: 0, right?.getHeight() ?: 0)

    fun getBalance() : Int = (right?.getHeight() ?: 0) - (left?.getHeight() ?: 0)

    fun toTree() : AVLTree<K, V> {
        val tree = AVLTree<K, V>()
        tree.root = this
        return tree
    }
}

class AVLTree<K : Comparable<K>, V>() {
    internal var root: Node<K, V>? = null

    fun insert(key : K, value : V) {
        root = insert(root, key, value)
    }

    fun search(key : K) : V? {
        return search(root, key)
    }

    // TODO: delete
    fun delete(key : K) {
        
    }

    fun toText(): String {
        fun Node<K, V>.toText_(): List<String> {
            val lText = left?.toText_()?.map { "|  $it" } ?: listOf("|  _\n")
            val rText = right?.toText_()?.map { "|  $it" } ?: listOf("|  _\n")
            val vText = listOf("($key,$value)\n")
            return lText + vText + rText
        }
        val builder = StringBuilder()
        val lines = root?.toText_() ?: listOf("_\n")
        lines.forEach { builder.append(it) }
        return builder.toString()
    }
}

fun <K : Comparable<K>, V> insert(node : Node<K, V>?, keyN : K, valueN : V) : Node<K, V>? {
    if (node == null) return Node(keyN, valueN, null, null)
    if (keyN == node.key) {
        node.value = valueN
        return node
    } else if (keyN < node.key) {
        node.left = insert(node.left, keyN, valueN)
        node.restoreBalance()
        return node
    } else {
        node.right = insert(node.right, keyN, valueN)
        node.restoreBalance()
        return node
    }
}

fun <K : Comparable<K>, V> search(node : Node<K, V>?,keyS: K): V? {
    if (node == null) return null
    if (keyS == node.key) return node.value
    else if (keyS < node.key) return search(node.left, keyS)
    else return search(node.right, keyS)
}

fun <K : Comparable<K>, V> Node<K, V>.restoreBalance() {
    val balanceA = getBalance()
    if (balanceA == 2) {
        val balanceB = right?.getBalance() ?: 0
        if (balanceB > 0) {
            rotateSmallLeft();
        } else {
            rotateBigLeft();
        }
    } else if(balanceA == -2) {
        val balanceB = left?.getBalance() ?: 0
        if (balanceB <= 0) {
            rotateSmallRight();
        } else {
            rotateBigRight();
        }
    }
}

// ------------------ ROTATIONS --------------------------------
fun <K : Comparable<K>, V> Node<K, V>.rotateSmallLeft() {
    val nodeB = right
    if (nodeB != null) {
        left = Node(key, value, left, nodeB.left)
        right = nodeB.right
        key = nodeB.key
        value = nodeB.value
    }
}

fun <K : Comparable<K>, V> Node<K, V>.rotateSmallRight() {
    val nodeB = left
    if (nodeB != null) {
        right = Node(key, value, nodeB.right, right)
        left = nodeB.left
        key = nodeB.key
        value = nodeB.value
    }
}

fun <K : Comparable<K>, V> Node<K, V>.rotateBigLeft() {
    right?.rotateSmallRight()
    this.rotateSmallLeft()
}

fun <K : Comparable<K>, V> Node<K, V>.rotateBigRight() {
    left?.rotateSmallLeft()
    this.rotateSmallRight()
}

//fun <V> newIntLeaf(k : Int, v: V) : Node<Int, V> = Node1(k, v, Empty<Int, V>(), Empty<Int, V>())

fun main(args: Array<String>) {
    //val tree = Node(4, "A", Node(3, "B", newIntLeaf(1, "C"), newIntLeaf(2, "D")), newIntLeaf(5, "E"))
    //print(tree.toText())
}