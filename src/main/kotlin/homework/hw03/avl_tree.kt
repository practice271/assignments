// Homework #03 (22.09 - 06.10)
// Author: Kirill Smirenko, group 271
package homework.hw03

/**
 * A Node (empty, leaf or non-leaf) for AVL tree.
 * @param key Node key.
 * @param value Node value.
 * @param left Left subtree.
 * @param right Right subtree.
 */
class Node<K : Comparable<K>, V>(key: K, value : V, left: Node<K, V>?, right: Node<K, V>?) {
    var key: K = key
    var value: V = value
    var left: Node<K, V>? = left
    var right: Node<K, V>? = right

    /**
     * Returns the height of the node.
     */
    fun getHeight() : Int = 1 + Math.max(left?.getHeight() ?: 0, right?.getHeight() ?: 0)

    /**
     * Returns the balance (right subtree height - left subtree height) of the node.
     */
    fun getBalance() : Int = (right?.getHeight() ?: 0) - (left?.getHeight() ?: 0)

    /**
     * Returns the smallest key of the node and its subtrees.
     */
    fun findMin() : K =
        if (left == null) key else left!!.findMin()

    /**
     * Returns the biggest key of the node and its subtrees.
     */
    fun findMax() : K =
        if (right == null) key else right!!.findMax()

    /**
     * Creates an AVL tree using the node as root.
     */
    fun toTree() : AVLTree<K, V> {
        val tree = AVLTree<K, V>()
        tree.root = this
        return tree
    }
}

/**
 * Self-balancing AVL tree.
 * @param K The type of tree's keys (must be comparable).
 * @param V The type of tree's values.
 */
class AVLTree<K : Comparable<K>, V>() {
    internal var root: Node<K, V>? = null

    /**
     * Inserts a new element ([key], [value]) into the tree.
     */
    fun insert(key : K, value : V) {
        root = insert(root, key, value)
    }

    /**
     * Returns the value of a node with [key], if found, or null otherwise.
     */
    fun search(key : K) : V? {
        return search(root, key)
    }

    /**
     * Removes the element with [key], if possible.
     */
    fun remove(key : K) {
        root = remove(root, key)
    }

    /**
     * Returns a pretty visual representation of the tree.
     */
    fun toText() : String {
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

fun <K : Comparable<K>, V> remove(node : Node<K, V>?, keyR : K) : Node<K, V>? {
    // empty
    if (node == null) return null
    // leaf
    if ((node.left == null) && (node.right == null)) return if (node.key == keyR) null else node
    // non-leaf
    if (node.key == keyR) {
        if (node.getBalance() < 0) {
            // left subtree is higher
            val nearestKey = node.left!!.findMax()
            node.value = search(node, nearestKey)!!
            node.key = nearestKey
            node.left = remove(node.left, nearestKey)
            node.restoreBalance()
            return node
        }
        else {
            // right subtree is higher
            val nearestKey = node.right!!.findMin()
            node.value = search(node, nearestKey)!!
            node.key = nearestKey
            node.right = remove(node.right, nearestKey)
            node.restoreBalance()
            return node
        }
    }
    else if (keyR < node.key) {
        node.left = remove(node.left, keyR)
        node.restoreBalance()
        return node
    }
    else {
        node.right = remove(node.right, keyR)
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

// TODO: move
/**
 * Generates simplified node representation (for testing).
 */
internal fun <K : Comparable<K>, V> Node<K, V>.toStringCLR() : String {
    fun Node<K, V>.toStringCLR_() : List<String> {
        val lText = left?.toStringCLR_() ?: listOf("-")
        val rText = right?.toStringCLR_() ?: listOf("-")
        val vText = listOf("$value")
        return vText + lText + rText
    }
    val builder = StringBuilder()
    val lines = this.toStringCLR_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

/**
 * Creates a tree leaf.
 */
internal fun <K : Comparable<K>, V> leaf(key : K, value : V) : Node<K, V> = Node(key, value, null, null)

fun main(args : Array<String>) {
    // TODO: remove
    val tree =
            Node(8, "8",
                    Node(5, "5",
                            Node(3, "3",
                                    Node(2, "2", leaf(1, "1"), null),
                                    leaf(4, "4")),
                            Node(7, "7", leaf(6, "6"), null)),
                    Node(11, "B",
                            Node(10, "A", leaf(9, "9"), null),
                            leaf(12, "C"))).toTree()
    tree.insert(0, "0")
    tree.insert(-1, "X")
    tree.remove(12)
    print(tree.toText())
    print(tree.root?.toStringCLR())
}