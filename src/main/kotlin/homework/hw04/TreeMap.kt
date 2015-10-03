// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw04

/**
 * Map implementation with AVL tree.
 * @param K The type of tree's keys (must be comparable).
 * @param V The type of tree's values.
 */
class TreeMap<K, V>() : AbstractMap<K, V> where K : Comparable<K> {
    var root : Node<K, V>? = null

    override fun insert(newKey : K, newValue : V) {
        root = insert(root, newKey, newValue)
    }

    override fun search(key : K) : V? = search(root, key)

    override fun remove(key : K) {
        root = remove(root, key)
    }

    override fun forEach(f : (K, V) -> Unit) {
        fun <K : Comparable<K>, V> Node<K, V>.forEach_(f : (K, V) -> Unit) {
            f(key, value)
            if (left != null) left.forEach_(f)
            if (right != null) right.forEach_(f)
        }
        root?.forEach_(f)
    }

    override fun newClassInstance() : AbstractMap<K, V> = TreeMap<K, V>()

    /**
     * A Node (empty, leaf or non-leaf) for AVL tree.
     * @param key Node key.
     * @param value Node value.
     * @param left Left subtree.
     * @param right Right subtree.
     */
    private class Node<K : Comparable<K>, V>(key : K, value : V, left : Node<K, V>?, right : Node<K, V>?) {
        var key : K = key
        var value : V = value
        var left : Node<K, V>? = left
        var right : Node<K, V>? = right

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
        fun findMin() : K {
            val left_ = left
            return if (left_ == null) key else left_.findMin()
        }

        /**
         * Returns the biggest key of the node and its subtrees.
         */
        fun findMax() : K {
            val right_ = right
            return if (right_ == null) key else right_.findMax()
        }

        /**
         * Creates an AVL tree using the node as root.
         */
        fun toTree() : TreeMap<K, V> {
            val tree = TreeMap<K, V>()
            tree.root = this
            return tree
        }

        // ROTATIONS

        internal fun rotateSmallLeft() {
            val nodeB = right
            if (nodeB != null) {
                left = Node(key, value, left, nodeB.left)
                right = nodeB.right
                key = nodeB.key
                value = nodeB.value
            }
        }

        internal fun rotateSmallRight() {
            val nodeB = left
            if (nodeB != null) {
                right = Node(key, value, nodeB.right, right)
                left = nodeB.left
                key = nodeB.key
                value = nodeB.value
            }
        }

        internal fun rotateBigLeft() {
            right?.rotateSmallRight()
            this.rotateSmallLeft()
        }

        internal fun rotateBigRight() {
            left?.rotateSmallLeft()
            this.rotateSmallRight()
        }

        internal fun restoreBalance() {
            val balanceA = getBalance()
            if (balanceA == 2) {
                val balanceB = right?.getBalance() ?: 0
                if (balanceB > 0) {
                    rotateSmallLeft();
                } else {
                    rotateBigLeft();
                }
            } else if (balanceA == -2) {
                val balanceB = left?.getBalance() ?: 0
                if (balanceB <= 0) {
                    rotateSmallRight();
                } else {
                    rotateBigRight();
                }
            }
        }
    }

    // main functions

    private fun <K : Comparable<K>, V> insert(node : Node<K, V>?, keyN : K, valueN : V) : Node<K, V>? {
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

    private fun <K : Comparable<K>, V> remove(node : Node<K, V>?, keyR : K) : Node<K, V>? {
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
            } else {
                // right subtree is higher
                val nearestKey = node.right!!.findMin()
                node.value = search(node, nearestKey)!!
                node.key = nearestKey
                node.right = remove(node.right, nearestKey)
                node.restoreBalance()
                return node
            }
        } else if (keyR < node.key) {
            node.left = remove(node.left, keyR)
            node.restoreBalance()
            return node
        } else {
            node.right = remove(node.right, keyR)
            node.restoreBalance()
            return node
        }
    }

    private fun <K : Comparable<K>, V> search(node : Node<K, V>?, keyS : K) : V? {
        if (node == null) return null
        if (keyS == node.key) return node.value
        else if (keyS < node.key) return search(node.left, keyS)
        else return search(node.right, keyS)
    }
}