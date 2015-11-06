/*
 * Homework 8 (03.11.2015)
 * AVLTree implementation
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw08

import java.util.*

abstract public class Set<T : Comparable<T>> : Iterable<T> {
    abstract public fun insert(elem : T) : Set<T>
    abstract public fun remove(elem : T) : Set<T>
    abstract public fun contains(elem : T) : Boolean
    abstract public fun unite(set : Set<T>) : Set<T>
    abstract public fun intersect(set : Set<T>) : Set<T>
}

/** Implementation of AVL tree. */
open public class AVLTree<T : Comparable<T>>() : Set<T>() {

    /** Defines empty tree. */
    public class Empty<T : Comparable<T>>() : AVLTree<T>() {
        public override fun iterator() : Iterator<T> {
            return object : Iterator<T> {
                override fun hasNext() : Boolean = false
                override fun next(): T {
                    throw NoSuchElementException()
                }
            }
        }
    }

    /** Defines a leaf of tree. */
    public class Leaf<T : Comparable<T>>(val value : T) : AVLTree<T>() {
        public override fun iterator() : Iterator<T> {
            return object : Iterator<T> {
                private var ok = true

                override fun hasNext() : Boolean = ok
                override fun next(): T {
                    if (ok) {
                        ok = false
                        return value
                    }
                    else throw NoSuchElementException()
                }
            }
        }
    }

    /** Defines a node of tree. */
    public class Node<T : Comparable<T>>(val value : T, val left : AVLTree<T>, val right : AVLTree<T>) : AVLTree<T>() {
        public val height = Math.max(left.getHeight(), right.getHeight()) + 1

        public override fun iterator() : Iterator<T> {
            return object : Iterator<T> {
                private val lIterator : Iterator<T> = left.iterator()
                private val rIterator : Iterator<T> = right.iterator()
                private var observed : Boolean = false
                private var lHasNext : Boolean = true
                    get() =
                    if (field) {
                        field = lIterator.hasNext(); field
                    } else false
                private var rHasNext : Boolean = true
                    get() =
                    if (field) {
                        field = rIterator.hasNext(); field
                    } else false

                // CLR
                override fun next() : T {
                    if (!observed) {
                        observed = true
                        return value
                    }
                    if (lHasNext) return lIterator.next()
                    if (rHasNext) return rIterator.next()
                    throw NoSuchElementException()
                }

                override fun hasNext(): Boolean {
                    if (!observed || lHasNext || rHasNext) return true
                    return false
                }
            }
        }
    }

    /** Iterator for AVL Tree*/
    public override fun iterator() : Iterator<T> {
        return this.iterator()
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
    private fun AVLTree<T>.getLeftTree() : AVLTree<T> {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> return left
            else     -> throw Exception("Error")
        }
    }

    /** Gets right subtree. */
    private fun AVLTree<T>.getRightTree() : AVLTree<T> {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> return right
            else     -> throw Exception("Error")
        }
    }

    /** Gets tree value. */
    private fun AVLTree<T>.getTreeValue() : T {
        when(this) {
            is Empty -> throw Exception("Tree is empty")
            is Leaf  -> return value
            is Node  -> return value
            else     -> throw Exception("Error")
        }
    }

    /** Implements small left rotation of tree. */
    private fun AVLTree<T>.leftRotation() : AVLTree<T> {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> {
                val center = this.right.getLeftTree()
                val right  = this.right.getRightTree()
                val temp   = Node(value, left, center)

                return Node(this.right.getTreeValue(), temp, right)
            }
            else     -> throw Exception("Error")
        }
    }

    /** Implements small right rotation of tree. */
    private fun AVLTree<T>.rightRotation() : AVLTree<T> {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> {
                val left   = this.left.getLeftTree()
                val center = this.left.getRightTree()
                val temp   = Node(value, center, right)

                return Node(this.left.getTreeValue(), left, temp)
            }
            else     -> throw Exception("Error")
        }
    }

    /** Restores balance of tree. */
    public fun balance() : AVLTree<T> {
        when(this) {
            is Empty -> return this
            is Leaf  -> return this
            is Node  -> {
                val balance    = this.right.getHeight() - this.left.getHeight()
                val leftLeft   = this.left.getLeftTree()
                val leftRight  = this.left.getRightTree()
                val rightLeft  = this.right.getLeftTree()
                val rightRight = this.right.getRightTree()

                when (balance) {
                    2 -> {
                        // small left rotation
                        if (rightLeft.getHeight() <= rightRight.getHeight())
                            return this.leftRotation()

                        // big left rotation
                        else {
                            val temp = this.right.rightRotation()
                            return (Node(this.value, this.left, temp)).leftRotation()
                        }
                    }
                    -2 -> {
                        // small right rotation
                        if (leftRight.getHeight() <= leftLeft.getHeight())
                            return this.rightRotation()

                        // big right rotation
                        else {
                            val temp = this.left.leftRotation()
                            return (Node(this.value, temp, this.right)).rightRotation()
                        }
                    }
                    else -> return Node(this.value, this.left.balance(), this.right.balance())
                }
            }
            else    -> throw Exception("Error")
        }
    }

    /** Inserts element in tree. */
    public override fun insert(elem : T) : AVLTree<T> {
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
    private fun AVLTree<T>.fold(f : (T, T) -> T, acc : T) : T {
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
    public override fun remove(elem : T) : AVLTree<T> {
        fun min(a : T, b : T) : T {
            if (a.compareTo(b) <= 0) return a
            return b
        }

        fun max(a : T, b : T) : T {
            if (a.compareTo(b) <= 0) return b
            return a
        }

        when(this) {
            is Empty -> return this
            is Leaf  -> {
                when (this.value) {
                    elem -> return Empty()
                    else -> return this
                }
            }
            is Node  -> {
                if (this.value == elem) {
                    if (this.left.getHeight() < this.right.getHeight()) {
                        // finds minimum element in right subtree
                        val temp = this.right.fold({a, b -> min(a, b)}, this.value)
                        return Node(temp, this.left, this.right.remove(temp)).balance()
                    }
                    else {
                        // finds maximum element in left subtree
                        val temp = this.left.fold({a, b -> max(a, b)}, this.left.getTreeValue())
                        return Node(temp, this.left.remove(temp), this.right).balance()
                    }
                }
                else if (elem < this.value) {
                    val temp   = this.left.remove(elem)
                    return Node(this.value, temp, this.right).balance()
                }
                else {
                    val temp = this.right.remove(elem)
                    return Node(this.value, this.left, temp).balance()
                }
            }
            else     -> throw Exception("Error")
        }
    }

    /** Returns true if tree contains given element. */
    public override fun contains(elem : T) : Boolean {
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
    public fun toList() : List<T> {
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
            else -> throw Exception("Error")
        }
    }

    /** Returns tree which is union of given trees. */
    public override fun unite(set : Set<T>) : AVLTree<T> {
        var tree = Empty<T>() as AVLTree<T>
        for (i in this) tree = tree.insert(i)
        for (i in (set as AVLTree))
            if (!this.contains(i)) tree = tree.insert(i)
        return tree
    }

    /** Returns tree which is intersection of given trees. */
    public override fun intersect(set : Set<T>) : AVLTree<T> {
        var tree = Empty<T>() as AVLTree<T>
        for (i in (set as AVLTree))
            if (this.contains(i)) tree = tree.insert(i)
        return tree
    }
}