package hw03

internal class  NodeAvl<A>(var value : Pair<Int, A>, var diff : Int = 0,
                           var leftChild : NodeAvl<A>?, var rightChild : NodeAvl<A>?) {
    //the numbers in functions below may seem magical,
    // but they've been calculated (in scientific way) by me.
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
        val newRightChild = NodeAvl(value, rightRotationCalcDiffNodeGoingDown(oldDiff), left.rightChild, rightChild)
        return NodeAvl(left.value, left.rightRotationCalcDiffNodeGoingUp(),
                left.leftChild,
                newRightChild)
    }

    internal fun findBiggest(): Pair<Int, A> {
        val rightChild_val = rightChild
        if (rightChild_val != null) return rightChild_val.findBiggest()
        //!! is required by Kotlin (for unknown reasons)
        else return value
    }

    internal fun findSmallest(): Pair<Int, A> {
        val leftChild_val = leftChild
        if (leftChild_val != null) return leftChild_val.findSmallest()
        //!! is required by Kotlin (for unknown reasons)
        else return value
    }

    internal fun balance(): NodeAvl<A> {
        if ((diff == -1) || (diff == 1) || (diff == 0)) return this
        if (diff == 2) {
            /*
            var temp = this
            if (leftChild?.diff == -1) //it is guaranteed by calling functions that there won't be NPE
            {
                temp = NodeAvl(value, diff, leftChild!!.rotateLeft(), rightChild)
            }
            return temp.rotateRight()
            */
            val leftChild_val = leftChild
            if (leftChild_val != null && leftChild_val.diff == -1) {
                return NodeAvl(value, diff, leftChild_val.rotateLeft(), rightChild).rotateRight()
            }
            return this.rotateRight()
        } else {
            var temp = this
            if (rightChild?.diff == 1) {//it is guaranteed by calling functions that there won't be NPE
                temp = NodeAvl(value, diff, leftChild, rightChild!!.rotateRight())
            }
            return temp.rotateLeft()
        }
    }
}
    public fun <A> add(num: Pair<Int, A>, tree: NodeAvl<A>?): NodeAvl<A> {
        if (tree == null) return NodeAvl<A>(num, 0, null, null)
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

    public fun <A> del(num : Pair<Int, A>, tree : NodeAvl<A>?) : NodeAvl<A>? {
        if (tree == null) return null //if there's nothing to delete, nothing will be deleted
        if (num.first == tree.value.first) {
            val substitute : Pair<Int, A>
            if (tree.leftChild != null) {
                substitute = tree.leftChild!!.findBiggest()
                val newLeftChild = del(substitute,tree.leftChild)
                return NodeAvl(substitute, tree.diff - 1, newLeftChild, tree.rightChild).balance()
            }
            if (tree.rightChild != null) {
                substitute = tree.rightChild!!.findSmallest()
                val newRightChild = del(substitute,tree.rightChild)
                return NodeAvl(substitute, tree.diff + 1, tree.leftChild, newRightChild).balance()
            }
            return null
        }
        if (num.first < tree.value.first) {
            val newLeftChild = del(num,tree.leftChild)
            return NodeAvl(tree.value, tree.diff - 1, newLeftChild, tree.rightChild).balance()
        }
        val newRightChild = del(num,tree.rightChild)
        return NodeAvl(tree.value, tree.diff + 1, tree.leftChild, newRightChild).balance()
    }
    public fun <A> printTree (spaces : String, node : NodeAvl<A>?) {
        if (node == null) print("null")
        else {
            print("${node.value}\n$spaces|---")
            printTree(spaces + "        ", node.rightChild)
            print("\n$spaces|\n$spaces|---")
            printTree(spaces + "        ", node.leftChild)
        }
    }

public fun addInt(num : Int, tree: NodeAvl<Int>?) : NodeAvl<Int> {
    return add(Pair(num, num), tree)
}

public fun main(args : Array<String>) {
    var t = NodeAvl<Int>(Pair(0, 0), 0, null, null)
    t = addInt(12, t)
    t = addInt(-3, t)
    t = addInt(1, t)
    t = addInt(-3, t)
    t = addInt(4, t)
    t = addInt(25, t)
    var nt = del(Pair(12, 12), t)
    printTree("", nt)

}