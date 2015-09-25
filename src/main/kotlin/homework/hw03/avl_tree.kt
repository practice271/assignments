// Homework #03 (22.09 - 06.10)
// Author: Kirill Smirenko, group 271
package homework.hw03

abstract class AvlNode<K : Comparable<K>, V>() {

}
internal class Empty<K : Comparable<K>, V>() : AvlNode<K, V>() {}
internal class Node<K : Comparable<K>, V>(
        var key : K,
        var value : V,
        var left: AvlNode<K, V>,
        var right: AvlNode<K, V>
) : AvlNode<K, V>() {}

open class AVLTree<K : Comparable<K>, V>() {
    internal var root : AvlNode<K, V> = Empty()

    /**
     * Generates detailed visual tree representation (LCR).
     */
    fun toText() : String {
        fun AvlNode<K, V>.toText_() : List<String> {
            when (this) {
                is Empty -> return listOf("_\n")
                is Node  -> {
                    val lText = left.toText_().map { "|  $it"}
                    val rText = right.toText_().map { "|  $it"}
                    val vText = listOf("($key,$value)\n")
                    return lText + vText + rText
                }
                else -> throw Exception("Unknown class")
            }
        }
        val builder = StringBuilder()
        val lines = root.toText_()
        lines.forEach { builder.append(it) }
        return builder.toString()
    }

    /**
     * Inserts a new pair (key, value) into the tree.
     *
     * @param keyN The new key.
     * @param valueN The new value.
     */
    fun insert(keyN : K, valueN : V) {
        fun AvlNode<K, V>.insert_(keyN: K, valueN: V) : AvlNode<K, V> {
            when (this) {
                is Empty -> return Node(keyN, valueN, Empty<K, V>(), Empty<K, V>())
                is Node -> {
                    if (keyN == key) {
                        value = valueN
                        return this
                    } else if (keyN < key) {
                        left = left.insert_(keyN, valueN)
                        this.restoreBalance()
                        return this
                    } else {
                        right = right.insert_(keyN, valueN)
                        this.restoreBalance()
                        return this
                    }
                }
                else -> throw Exception("Unknown class")
            }
        }
        root = root.insert_(keyN, valueN)
    }

    /**
     * Looks up a value in the tree by key.
     *
     * @param keyS The key to search.
     * @return The value, if found, otherwise null.
     */
    fun search(keyS : K) : V? {
        fun AvlNode<K, V>.search_(keyS: K) : V? {
            when (this) {
                is Empty -> return null
                is Node -> {
                    if (keyS == key) return value
                    else if (keyS < key) return left.search_(keyS)
                    else return right.search_(keyS)
                }
                else -> throw Exception("Unknown class")
            }
        }
        return root.search_(keyS)
    }

    // ------------------ PRIVATE   --------------------------------

    fun AvlNode<K, V>.height() : Int = when (this) {
        is Empty -> 0
        is Node -> 1 + Math.max(left.height(), right.height())
        else -> throw Exception("Unknown class")
    }

    fun AvlNode<K, V>.restoreBalance() {
        if (this is Node) {
            val balance = right.height() - left.height()
            if (balance == -2) {
                if ((left is Node) && (left.left.height() >= left.right.height)) {
                    this.rotateSmallRight();
                } else {
                    cur = doubleRotateLeftRight(cur);
                }
            } else if(balance==2) {
                if(height(cur.right.right)>=height(cur.right.left)) {
                    cur = rotateLeft(cur);
                } else {
                    cur = doubleRotateRightLeft(cur);
                }
            }
    }

    // ------------------ ROTATIONS --------------------------------
    fun AvlNode<K, V>.rotateSmallLeft() {
        if ((this is Node) && (this.right is Node)) {
            val nodeB = this.right
            this.right = nodeB.left
            nodeB.left = this
        }
        else throw Exception("Incorrect operation")
    }

    fun AvlNode<K, V>.rotateSmallRight() {
        if ((this is Node) && (this.left is Node)) {
            val nodeA = this.left
            this.left = nodeA.right
            nodeA.right = this
        }
        else throw Exception("Incorrect operation")
    }

    fun AvlNode<K, V>.rotateBigLeft() {
        if ((this is Node) && (this.right is Node)) {
            right.rotateSmallRight()
            this.rotateSmallLeft()
        }
        else throw Exception("Incorrect operation")
    }

    fun AvlNode<K, V>.rotateBigRight() {
        if ((this is Node) && (this.left is Node)) {
            left.rotateSmallLeft()
            this.rotateSmallRight()
        }
        else throw Exception("Incorrect operation")
    }
}

fun <V> newIntLeaf(k : Int, v: V) : AvlNode<Int, V> = Node(k, v, Empty<Int, V>(), Empty<Int, V>())

fun main(args: Array<String>) {
    //val tree = Node(4, "A", Node(3, "B", newIntLeaf(1, "C"), newIntLeaf(2, "D")), newIntLeaf(5, "E"))
    //print(tree.toText())
}