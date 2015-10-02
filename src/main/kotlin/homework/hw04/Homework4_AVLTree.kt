/*
 * Homework 4 (29.09.2015)
 * AVLTree implementation
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw04

abstract class Set {
    abstract public fun insert(elem : Int) : Set
    abstract public fun remove(elem : Int) : Set
    abstract public fun contains(elem : Int) : Boolean
    abstract public fun unite(set : Set) : Set
    abstract public fun intersect(set : Set) : Set
}

/** Implementation of AVL tree. */
open class AVLTree() : Set() {

    class Empty() : AVLTree() {}
    class Leaf(val value : Int) : AVLTree() {}
    class Node(val value : Int, val left : AVLTree, val right : AVLTree) : AVLTree() {
        public val height = Math.max(left.getHeight(), right.getHeight()) + 1
    }

    /** Gets height of tree. */
    private fun getHeight() : Int {
        when(this) {
            is Empty -> return 0
            is Leaf  -> return 1
            is Node  -> return this.height
            else     -> throw Exception("Error")
        }
    }

    /** Gets left subtree. */
    private fun AVLTree.getLeftTree() : AVLTree {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> return left
            else     -> throw Exception("Error")
        }
    }

    /** Gets right subtree. */
    private fun AVLTree.getRightTree() : AVLTree {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> return right
            else     -> throw Exception("Error")
        }
    }

    /** Gets tree value. */
    private fun AVLTree.getTreeValue() : Int {
        when(this) {
            is Empty -> return 0
            is Leaf  -> return value
            is Node  -> return value
            else     -> throw Exception("Error")
        }
    }

    /** Implements small left rotation of tree. */
    private fun AVLTree.leftRotation() : AVLTree {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> {
                val center  = this.right.getLeftTree()
                val right   = this.right.getRightTree()
                val temp    = Node(value, left, center)

                return Node(this.right.getTreeValue(), temp, right)
            }
            else     -> throw Exception("Error")
        }
    }

    /** Implements small right rotation of tree. */
    private fun AVLTree.rightRotation() : AVLTree {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> {
                val left    = this.left.getLeftTree()
                val center  = this.left.getRightTree()
                val temp    = Node(value, center, right)

                return Node(this.left.getTreeValue(), left, temp)
            }
            else     -> throw Exception("Error")
        }
    }

    /** Restores balance of tree. */
    internal fun balance() : AVLTree {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> {
                val balance    = this.right.getHeight() - this.left.getHeight()
                val leftLeft   = this.left.getLeftTree()
                val leftRight  = this.left.getRightTree()
                val rightLeft  = this.right.getLeftTree()
                val rightRight = this.right.getRightTree()

                // small left rotation
                if (balance == 2 && rightLeft.getHeight() <= rightRight.getHeight())
                    return this.leftRotation()

                // small right rotation
                else if (balance == -2 && leftRight.getHeight() <= leftLeft.getHeight())
                    return this.rightRotation()

                // big left rotation
                else if (balance == 2 && rightLeft.getHeight() > rightRight.getHeight())
                    return (Node(this.value, this.left, this.right.rightRotation())).leftRotation()

                // big right rotation
                else if (balance == -2 && leftRight.getHeight() > leftLeft.getHeight())
                    return (Node(this.value, this.left.leftRotation(), this.right)).rightRotation()

                else
                    return Node(this.value, this.left.balance(), this.right.balance())
            }
            else    -> throw Exception("Error")
        }
    }

    /** Inserts element in tree. */
    public override fun insert(elem : Int) : AVLTree {
        when(this) {
            is Empty -> return Leaf(elem)
            is Leaf  -> {
                if (elem < this.value)
                    return Node(this.value, Leaf(elem), Empty())
                else
                    return Node(this.value, Empty(), Leaf(elem))
            }
            is Node  -> {
                if (elem < this.value) {
                    val left = this.left.insert(elem)
                    return Node(this.value, left, this.right).balance()
                }
                else {
                    val right = this.right.insert(elem)
                    return Node(this.value, this.left, right).balance()
                }
            }
            else     -> throw Exception("Error")
        }
    }

    /** Fold for tree. */
    private fun AVLTree.fold(f : (Int, Int) -> Int, acc : Int) : Int {
        when (this) {
            is Empty -> return acc
            is Leaf  -> return f(acc, value)
            is Node  -> {
                val temp = f(acc, value)
                return f(temp, f(left.fold(f, acc), right.fold(f, acc)))
            }
            else -> throw Exception("Error")
        }
    }

    /** Removes element from tree. */
    public override fun remove(elem : Int) : AVLTree {
        when(this) {
            is Empty -> return this
            is Leaf  -> {
                if (this.value == elem)
                    return Empty()
                else
                    return this
            }
            is Node  -> {
                if (this.value == elem) {
                    if (this.left.getHeight() < this.right.getHeight()) {

                        // finds minimum element in right subtree
                        val temp   = this.right.fold({a, b -> Math.min(a, b)}, this.value)
                        return Node(temp, this.left, this.right.remove(temp)).balance()
                    }
                    else {

                        // finds maximum element in left subtree
                        val temp   = this.left.fold({a, b -> Math.max(a, b)}, 0)
                        return Node(temp, this.left.remove(temp), this.right).balance()
                    }
                }
                else if (elem < this.value) {
                    val temp   = this.left.remove(elem)
                    return Node(this.value, temp, this.right).balance()
                }
                else {
                    val temp   = this.right.remove(elem)
                    return Node(this.value, this.left, temp).balance()
                }
            }
            else     -> throw Exception("Error")
        }
    }

    /** Returns true if tree contains given element. */
    public override fun contains(elem : Int) : Boolean {
        when(this) {
            is Empty -> return false
            is Leaf  -> return (this.value == elem)
            is Node  -> {
                if (this.value == elem)
                    return true
                else if (elem < this.value)
                    return (this.left.contains(elem))
                else
                    return (this.right.contains(elem))
            }
            else     -> throw Exception("Error")
        }
    }

    /** Converts tree to list. */
    internal fun toList() : List<Int> {
        when(this) {
            is Empty -> return listOf()
            is Leaf  -> return listOf(this.value)
            is Node  -> {
                val left  = this.left.toList()
                val right = this.right.toList()

                // CLR
                val list = listOf(this.value).toLinkedList()
                for (i in left)  list.add(i)
                for (i in right) list.add(i)

                return list
            }
            else     -> throw Exception("Error")
        }
    }

    /** Returns tree which is union of given trees. */
    public override fun unite(set : Set) : AVLTree {
        var tree = this
        for (i in (set as AVLTree).toList()) {
            if (!this.contains(i)) tree = tree.insert(i)
        }
        return tree
    }

    /** Returns tree which is intersection of given trees. */
    public override fun intersect(set : Set) : AVLTree {
        var tree = Empty() as AVLTree
        for (i in (set as AVLTree).toList()) {
            if (this.contains(i)) tree = tree.insert(i)
        }
        return tree
    }

    /** Prints AVL tree. */
    public fun toText() {

        var leaf = 0
        var correction = -1

        /** Returns number of digits in value. */
        fun digits(value : Int) : Int {
            if (value == 0) return 0
            else return 1 + digits(value / 10)
        }

        /** Prints n spaces. */
        fun nSpaces(n : Int) : String {
            if (n == 0) return ""
            else return " " + nSpaces(n - 1)
        }

        /** Makes list of levels of tree. */
        fun makeList(tree : AVLTree) : List<String> {
            when (tree) {
                is Empty -> return listOf(" _ ")
                is Leaf -> {
                    leaf++
                    return listOf(" " + tree.value.toString() + " ")
                }
                is Node -> {
                    val lText = makeList(tree.left)
                    val rText = makeList(tree.right)

                    val temp = nSpaces(lText[0].length()) + tree.value.toString() +
                            nSpaces(rText[0].length())
                    var list = linkedListOf(temp)

                    if (lText.size() < rText.size() && correction == -1)
                        correction = leaf - 1
                    else if (lText.size() > rText.size() && correction == -1)
                        correction = leaf - 2

                    for (i in 0 .. tree.height - 2) {
                        var tmp = ""

                        if (lText.size() <= i) tmp = " "
                        else tmp = lText[i]

                        if (rText.size() <= i) tmp += nSpaces(digits(tree.value)) + "   "
                        else tmp += nSpaces(digits(tree.value)) + rText[i]

                        list.add(tmp)
                    }

                    return list
                }
                else -> throw Exception("Unknown class")
            }
        }

        val list = makeList(this)
        for (i in 0 .. list.size() - 2) println(list[i])

        if (correction == -1) correction = 0

        //we can't simplify this because of integer division
        println(nSpaces(correction * 2 + (correction / 2) * 2) + list.last())
    }
}