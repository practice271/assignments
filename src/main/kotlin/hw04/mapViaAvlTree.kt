package hw04

import java.util.*

abstract public class Map<A> () {
    abstract public fun insert      (value : Pair<Int, A>) : Map<A>
    abstract public fun delete      (key : Int) : Map<A>?
    abstract public fun search      (key : Int) : A?
    abstract public fun unite       (map2 : Map<A>?) : Map<A>
    abstract public fun intersect   (map2 : Map<A>) : Map<A>?

    abstract internal fun toList () : List<Pair<Int, A>>
}


public class NodeAvl<A>(var value : Pair<Int, A>, var diff : Int = 0, var leftChild : NodeAvl<A>?,
                        var rightChild : NodeAvl<A>?) : hw04.Map<A>(), Iterable<Pair<Int, A>>{

    override fun iterator(): Iterator<Pair<Int, A>> = CLRNodeIterator(this)

    inner private class EmptyIterator() : Iterator<Pair<Int, A>> {
        override fun hasNext(): Boolean = false
        override fun next(): Pair<Int, A> = throw NoSuchElementException()
    }

    inner private abstract class NodeIterator(protected val node: NodeAvl<A>): Iterator<Pair<Int, A>> {
        protected val lIterator: Iterator<Pair<Int, A>> =
                node.leftChild?.iterator() ?: EmptyIterator()
        protected val rIterator: Iterator<Pair<Int, A>> =
                node.rightChild?.iterator() ?: EmptyIterator()
        protected var observed: Boolean = false
        protected var lHasNext: Boolean = true
            get() = if (field) { field = lIterator.hasNext(); field } else false
        protected var rHasNext: Boolean = true
            get() = if (field) { field = rIterator.hasNext(); field } else false

        override fun hasNext(): Boolean {
            if (!observed) return true
            if (lHasNext ) return true
            if (rHasNext ) return true
            return false
        }
    }

    private inner class LCRNodeIterator(node: NodeAvl<A>): NodeIterator(node) {
        override fun next(): Pair<Int, A> {
            if (lHasNext ) return lIterator.next()
            if (!observed) {
                observed = true
                return node.value
            }
            if (rHasNext ) return rIterator.next()
            throw NoSuchElementException()
        }
    }

    private inner class CLRNodeIterator(node: NodeAvl<A>): NodeIterator(node) {
        override fun next(): Pair<Int, A> {
            if (!observed) {
                observed = true
                return node.value
            }
            if (lHasNext ) return lIterator.next()
            if (rHasNext ) return rIterator.next()
            throw NoSuchElementException()
        }
    }

    private inner class LRCNodeIterator(node: NodeAvl<A>): NodeIterator(node) {
        override fun next(): Pair<Int, A> {
            if (lHasNext ) return lIterator.next()
            if (rHasNext ) return rIterator.next()
            if (!observed) {
                observed = true
                return node.value
            }
            throw NoSuchElementException()
        }
    }

    override public fun insert (value : Pair<Int, A>) : NodeAvl<A> {
        return add(value, this)
    }
    override public fun delete (key : Int) : NodeAvl<A>? {
        return del(key, this)
    }
    override public fun search (key : Int) : A? {
        return search(key, this)
    }
    override internal fun toList () : List<Pair<Int, A>> {
        val res = linkedListOf(value)
        val left = leftChild?.toList() ?: linkedListOf()
        res.addAll(left)
        val right = rightChild?.toList() ?: linkedListOf()
        res.addAll(right)
        return res
    }
    override public fun unite (map2 : Map<A>?) : NodeAvl<A> {
        if (map2 == null) return this
        val map2 = map2.toList()
        var res = this
        for (i in map2) {
            res = add(i, res)
        }
        return res
    }

    override public fun intersect (map2 : Map<A>) : NodeAvl<A>? {
        val map1 = toList()
        val map2 = map2.toList()
        var res : NodeAvl<A>? = null
        for (i in map2) {
            if (map1.hasSecondValueInPair(i)) res = add(i, res)
        }
        return res
    }

    //the numbers in functions below were calculated this way:
    /*
    y,z stand for single nodes, A,B,C - for subtrees.
    In notation in function names y is NodeGoingDown, and z is NodeGoingUp
    1. Left rotation
    Suppose we have tree
        y
       / \
      A   z
         / \
        B   C
     After rotation it'll look like
             z'
           / \
          y'   C
         / \
        A   B
     We'll only rotate left if diff in y is -1
     Let's define height of A as x. Then height of z is (x+1).
     We have 3 possibilities:
        1. |B| = x, |C| = x.
            In this case y'.diff = 0 ('cause |A| = |B|) and z'.diff = 1 (|y'| = x + 1, |C| = x)
        2. |B| = x - 1, |C| = x.
            In this case y'.diff = 1 ('cause |A| = |B| + 1) and z'.diff = 1 (|y'| = x + 1, |C| = x)
        3. |B| = x, |C| = x - 1.
            In this case y'.diff = 0 ('cause |A| = |B|) and z'.diff = 2 (|y'| = x + 1, |C| = x-1)
     2. Right rotation is conducted in a very similar way.     */
    internal fun leftRotationCalcDiffNodeGoingUp(): Int {
        if (diff == 1) return 2
        else return 1
    }

    internal fun leftRotationCalcDiffNodeGoingDown(upperNodeDiff: Int): Int {
        if (upperNodeDiff == -1) return 1
        else return 0
    }

    internal fun rotateLeft(): NodeAvl<A> {
        val right = rightChild!!//it is guaranteed by calling functions that there won't be NPE
        val oldDiff = right.diff
        val newLeftChild = NodeAvl(value, leftRotationCalcDiffNodeGoingDown(oldDiff), leftChild, right.leftChild)
        return NodeAvl(right.value, right.leftRotationCalcDiffNodeGoingUp(),
                newLeftChild,
                right.rightChild)
    }

    internal fun rightRotationCalcDiffNodeGoingUp(): Int {
        if (diff == -1) return -2
        else return -1
    }

    internal fun rightRotationCalcDiffNodeGoingDown(upperNodeDiff: Int): Int {
        if (upperNodeDiff == 1) return -1
        else return 0
    }

    internal fun rotateRight(): NodeAvl<A> {
        val left = leftChild!!//it is guaranteed by calling functions that there won't be NPE
        val oldDiff = left.diff
        val newRightChild = NodeAvl(value, rightRotationCalcDiffNodeGoingDown(oldDiff),
                left.rightChild, rightChild)
        return NodeAvl(left.value, left.rightRotationCalcDiffNodeGoingUp(),
                left.leftChild,
                newRightChild)
    }

    internal fun findBiggest(): Pair<Int, A> {
        val rightChild_val = rightChild
        if (rightChild_val != null) return rightChild_val.findBiggest()
        else return value
    }

    internal fun findSmallest(): Pair<Int, A> {
        val leftChild_val = leftChild
        if (leftChild_val != null) return leftChild_val.findSmallest()
        else return value
    }

    internal fun balance(): NodeAvl<A> {
        when (diff) {
            1, -1, 0 -> return this
            2 -> {
                val leftChild_val = leftChild
                if (leftChild_val != null && leftChild_val.diff == -1) {
                    return NodeAvl(value, diff, leftChild_val.rotateLeft(), rightChild).rotateRight()
                }
                return this.rotateRight()
            }
            else -> {
                var temp = this
                if (rightChild?.diff == 1) {//it is guaranteed by calling functions that there won't be NPE
                    temp = NodeAvl(value, diff, leftChild, rightChild!!.rotateRight())
                }
                return temp.rotateLeft()
            }
        }
    }
}

public fun <A> add(num: Pair<Int, A>, tree: NodeAvl<A>?): NodeAvl<A> {
    if (tree == null) return NodeAvl<A>(num, 0, null, null)
    if (tree.value.second == num.second) return tree
    if (num.first < tree.value.first)
        return NodeAvl(tree.value, tree.diff + 1, add(num, tree.leftChild), tree.rightChild).balance()

    return NodeAvl(tree.value, tree.diff - 1, tree.leftChild, add(num, tree.rightChild)).balance()
}

public fun <A> search(key : Int, tree : NodeAvl<A>?) : A? {
    if (tree == null)            return null //whoever wanted value that isn't here deserves null
    if (tree.value.first == key) return tree.value.second
    if (tree.value.first <  key) return search(key, tree.rightChild)
    return search(key, tree.leftChild)
}

public fun <A> del(num : Int, tree : NodeAvl<A>?) : NodeAvl<A>? {
    if (tree == null) return null //if there's nothing to delete, nothing will be deleted
    if (num == tree.value.first) {
        if (tree.leftChild != null) {
            val substitute = tree.leftChild!!.findBiggest()
            val newLeftChild = del(substitute.first,tree.leftChild)
            return NodeAvl(substitute, tree.diff - 1, newLeftChild, tree.rightChild).balance()
        }
        if (tree.rightChild != null) {
            val substitute = tree.rightChild!!.findSmallest()
            val newRightChild = del(substitute.first,tree.rightChild)
            return NodeAvl(substitute, tree.diff + 1, tree.leftChild, newRightChild).balance()
        }
        return null
    }
    if (num < tree.value.first) {
        val newLeftChild = del(num,tree.leftChild)
        return NodeAvl(tree.value, tree.diff - 1, newLeftChild, tree.rightChild).balance()
    }
    val newRightChild = del(num,tree.rightChild)
    return NodeAvl(tree.value, tree.diff + 1, tree.leftChild, newRightChild).balance()
}
public fun <A> printTree (spaces : String, node : NodeAvl<A>?) {
    if (node == null) { print("null"); return}
    print("${node.value}\n$spaces|---")
    printTree(spaces + "        ", node.rightChild)
    print("\n$spaces|\n$spaces|---")
    printTree(spaces + "        ", node.leftChild)
}
