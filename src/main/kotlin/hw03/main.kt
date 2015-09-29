/* AVL trees
(expectation: 3 h; reality: 11 h)
by Sokolova Polina */

package hw03

abstract class Tree {}
class Empty() : Tree() {}
class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}

fun Tree.height() : Int = if (this is Node) (1 + Math.max(l.height(), r.height())) else 0

fun Tree.balanceFactor() : Int = if (this is Node) (l.height() - r.height()) else 0

fun Tree.treeToString() : String {
    when (this) {
        is Node  -> {
            val lRes = l.treeToString()
            val rRes = r.treeToString()
            return "Node($value, $lRes, $rRes)"
        }
        else -> { return "Empty" }
    }
}

fun Tree.smallLeftRotation() : Tree {
    var L : Tree = Empty()
    var cValue : Int = 0
    var rValue : Int = 0
    var lR : Tree = Empty()
    var rR : Tree = Empty()
    if (this is Node) {
        cValue = value
        L = l
        if (r is Node) {
            rValue = r.value
            lR = r.l
            rR = r.r
        }
    }
    return Node(rValue, Node(cValue, L, lR), rR)
}

fun Tree.smallRightRotation() : Tree {
    var R : Tree = Empty()
    var cValue : Int = 0
    var lValue : Int = 0
    var lL : Tree = Empty()
    var rL : Tree = Empty()
    if (this is Node) {
        R = r
        cValue = value
        if (l is Node) {
            lValue = l.value
            lL = l.l
            rL = l.r
        }
    }
    return Node(lValue, lL, Node(cValue, rL, R))
}

fun Tree.balance() : Tree {
    if (this is Node) {
        val balance = this.balanceFactor()
        if (balance == -2) {
            when {
                this.r.balanceFactor() > 0 -> {
                    val right = r.smallRightRotation()
                    val tree = Node(value, l, right)
                    return tree.smallLeftRotation()
                }
                else -> { return this.smallLeftRotation() }
            }
        }
        if (balance == 2) {
            when {
                this.l.balanceFactor() < 0 -> {
                    val left = l.smallLeftRotation()
                    val tree = Node(value, left, r)
                    return tree.smallRightRotation()
                }
                else -> { return this.smallRightRotation() }
            }
        }
    }
    return this
}

fun Tree.insert(a : Int) : Tree {
    fun Tree.add() : Tree {
        when (this) {
            is Empty -> { return Node(a, Empty(), Empty()) }
            is Node -> {
                when {
                    a < value -> { return Node(value, l.add(), r) }
                    a > value -> { return Node(value, l, r.add()) }
                    else -> { return this }
                }
            }
        }
        return Empty()
    }
    val res = this.add()
    return res.balance()
}

fun Tree.minInRight() : Int {
    if (this is Node) {
        when (l) {
            is Empty -> { return value }
            is Node  -> { return l.minInRight() }
        }
    }
    return 0
}

fun Tree.replace(a : Int, b : Int) : Tree {
    if (this is Node) {
        when {
            a < value -> { return Node(value, l.replace(a, b), r) }
            a > value -> { return Node(value, l, r.replace(a, b)) }
            else -> { return Node(b, l, r) }
        }
    }
    return Empty()
}

fun Tree.remove(a : Int) : Tree {
    fun Tree.delete() : Tree {
        if (this is Node) {
            when {
                a < value -> { return Node(value, l.delete(), r).balance() }
                a > value -> { return Node(value, l, r.delete()).balance() }
                else -> {
                    when (r) {
                        is Empty -> { return l }
                        is Node -> {
                            val minValue = r.minInRight()
                            val resTree = Node(value, l, r.remove(minValue))
                            return resTree.replace(a, minValue).balance()
                        }
                    }
                }
            }
        }
        return Empty()
    }
    return this.delete()
}

fun Tree.search(a : Int) : Boolean {
    if (this is Node) {
        return (a == value) || l.search(a) || r.search(a)
    }
    return false
}

fun <B> Tree.fold(fEmpty: B, fNode: (Int, B, B) -> B) : B {
    when (this) {
        is Empty -> { return fEmpty }
        is Node  -> { return fNode(value, l.fold(fEmpty, fNode),
                r.fold(fEmpty, fNode)) }
    }
    return fEmpty
}

fun Tree.toText(): List<String> {
    return fold(listOf("-\n")) {(value, lRes, rRes) ->
        val lText = lRes.map { "| $it" }
        val rText = rRes.map { "| $it" }
        val vText = listOf("$value\n")
        lText + vText + rText
    }
}