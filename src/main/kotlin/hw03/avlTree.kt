package hw03

internal class  NodeAvl<A>(var value : Pair<Int, A>, var diff : Int = 0,
                           var leftChild : NodeAvl<A>?, var rightChild : NodeAvl<A>?) {
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
     2. Right rotation is conducted in a very similar way.
     I don't know any short way to write it and explanation above is WAAAAY long,
     so I'll leave this one for curious reader to fill by (him/her)self.
     Антон, практически полностью дублировать описание из п.1 было бы бесполезной механической работой.
     Буду рад услышать советы, как можно прокомментировать эти вычисления адекватным и коротким образом.
     Github seems to dislike Russian, so there're two above lines in English:
     Anton, writing almost the same things as in p.1 seem to me as useless mechanical labor.
     So I would be grateful to hear your advice on how to comment these calculations in adequate and short way.
     */
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