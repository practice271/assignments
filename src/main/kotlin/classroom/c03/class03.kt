package classroom.c03

abstract class Tree() {
    abstract public fun height(): Int
}
class Leaf(): Tree() {
    override public fun height() = 0
}
class Node(val value: Int, val left: Tree, val right: Tree): Tree() {
    override public fun height() = 1 + Math.max(left.height(), right.height())
}

class AVLtree() {
    private var root = Leaf()

    object A {
        public fun balance(t: Tree): Int {
            when (t) {
                is Node -> {
                    return t.right.height() - t.left.height()
                }
                else -> return 0
            }
        }
    }

    fun insert() {
        A.balance(Leaf())
    }
}

fun main() {
    AVLtree.A.balance(Leaf())
}
    /*
    fun insert(v: Int): AVLtree? {
        if (this is Leaf) {
           return Node(v, Leaf(), Leaf())
        }
        val a: AVLtree = Node(0, Leaf(), Leaf())
        if (this is Node) {
            when {
                value < v -> return null
                value > v -> return null
                else -> return null
            }
        }
    }
    */

