package hw03

/**
 * Created by Alexander on 23.09.2015.
 */

internal class  NodeAvl<A>(var value : Pair<Int, A>, var diff : Int = 0,
                           var leftChild : NodeAvl<A>?, var rightChild : NodeAvl<A>?) {
    //the numbers in functions below may seem magical,
    // but they've been calculated (in scientific way) by me.
    internal fun LeftRotationCalcDiffNodeGoingUp(): Int {
        if (diff == 1) return 2
        else return 1
    }

    internal fun LeftRotationCalcDiffNodeGoingDown(upperNodeDiff: Int): Int {
        if (upperNodeDiff == -1) return 1
        else return 0
    }

    internal fun rotateLeft(): NodeAvl<A> {
        val right = rightChild!!//it is guaranteed by calling functions that there won't be NPE
        val oldDiff = right.diff
        return NodeAvl(right.value, right.LeftRotationCalcDiffNodeGoingUp(),
                NodeAvl(value, LeftRotationCalcDiffNodeGoingDown(oldDiff), leftChild, right.leftChild),
                right.rightChild)
    }

    internal fun RightRotationCalcDiffNodeGoingUp(): Int {
        if (diff == -1) return -2
        else return -1
    }

    internal fun RightRotationCalcDiffNodeGoingDown(upperNodeDiff: Int): Int {
        if (upperNodeDiff == 1) return -1
        else return 0
    }

    internal fun rotateRight(): NodeAvl<A> {
        val left = leftChild!!
        val oldDiff = left.diff
        return NodeAvl(left.value, left.RightRotationCalcDiffNodeGoingUp(),
                left.leftChild,
                NodeAvl(value, RightRotationCalcDiffNodeGoingDown(oldDiff), left.rightChild, rightChild))
    }

    internal fun findBiggest(): Pair<Int, A> {
        if (rightChild != null) return rightChild!!.findBiggest()
        //!! is required by Kotlin (for unknown reasons)
        else return value
    }

    internal fun findSmallest(): Pair<Int, A> {
        if (leftChild != null) return leftChild!!.findSmallest()
        //!! is required by Kotlin (for unknown reasons)
        else return value
    }

    internal fun balance(): NodeAvl<A> {
        if ((diff == -1) || (diff == 1) || (diff == 0)) return this
        if (diff == 2) {
            var temp = this
            if (leftChild?.diff == -1) //it is guaranteed by calling functions that there won't be NPE
            {
                temp = NodeAvl(value, diff, leftChild!!.rotateLeft(), rightChild)
            }
            return temp.rotateRight()
        } else {
            var temp = this
            if (rightChild?.diff == 1) //it is guaranteed by calling functions that there won't be NPE
            {
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

    public fun <A> search(key : Int, tree : NodeAvl<A>?) : A?
    {
        if (tree == null)            return null //whoever wanted value that isn't here deserves null
        if (tree.value.first == key) return tree.value.second
        if (tree.value.first <  key) return search(key, tree.rightChild)
        return search(key, tree.leftChild)
    }

    public fun <A> del(num : Pair<Int, A>, tree : NodeAvl<A>?) : NodeAvl<A>? {
        if (tree == null) return null //if there's nothing to delete, nothing will be deleted
        if (num.first == tree.value.first)
        {
            val substitute : Pair<Int, A>
            if (tree.leftChild != null)
            {
                substitute = tree.leftChild!!.findBiggest()
                return NodeAvl(substitute, tree.diff - 1, del(substitute,tree.leftChild), tree.rightChild).balance()
            }
            if (tree.rightChild != null)
            {
                substitute = tree.rightChild!!.findSmallest()
                return NodeAvl(substitute, tree.diff + 1, tree.leftChild, del(substitute,tree.rightChild)).balance()
            }
            return null
        }
        if (num.first < tree.value.first)
        {
            return NodeAvl(tree.value, tree.diff - 1, del(num, tree.leftChild), tree.rightChild).balance()
        }

        return NodeAvl(tree.value, tree.diff + 1, tree.leftChild, del(num, tree.rightChild)).balance()
    }
    public fun <A> printTree (spaces : String, node : NodeAvl<A>?)
    {
        if (node == null) print("null")
        else
        {
            print("${node.value}\n$spaces|---")
            printTree(spaces + "        ", node.rightChild)
            print("\n$spaces|\n$spaces|---")
            printTree(spaces + "        ", node.leftChild)
        }
    }

public fun addInt(num : Int, tree: NodeAvl<Int>) : NodeAvl<Int>
{
    return add(Pair(num, num), tree)
}

public fun main(args : Array<String>)
{
    var t = NodeAvl<Int>(Pair(0, 0), 0, null, null)
    t = addInt(12, t)
    t = addInt(-3, t)
    t = addInt(1, t)
    t = addInt(-3, t)
    t = addInt(4, t)
    t = addInt(25, t)
    print("${search(12, t)}")
    var nt = del(Pair(12, 12), t)
    print("${search(12, nt)}")
    printTree("", nt)

}