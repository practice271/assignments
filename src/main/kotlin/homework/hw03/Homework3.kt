/*
Homework 3 (22.09.2015)

Author: Mikhail Kita, group 271
*/

package homework.hw03

open class AVLTree() {}

class Empty(val height : Int = 0) : AVLTree() {}
class Leaf(val value : Int, val height : Int = 1) : AVLTree() {}
class Node(val value : Int, val height : Int, val left : AVLTree, val right : AVLTree) : AVLTree() {}

//gets height of tree
fun AVLTree.getHeight() : Int {
    when(this) {
        is Empty -> return height
        is Leaf  -> return height
        is Node  -> return height
        else     -> throw Exception("Error")
    }
}

//gets left subtree
fun AVLTree.getLeftTree() : AVLTree {
    when(this) {
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> return left
        else     -> throw Exception("Error")
    }
}

//gets right subtree
fun AVLTree.getRightTree() : AVLTree {
    when(this) {
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> return right
        else     -> throw Exception("Error")
    }
}

//gets tree value
fun AVLTree.getTreeValue() : Int {
    when(this) {
        is Empty -> return 0
        is Leaf  -> return value
        is Node  -> return value
        else     -> throw Exception("Error")
    }
}

//implements small left rotation of tree
fun AVLTree.leftRotation() : AVLTree {
    when(this) {
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> {
            val center  = this.right.getLeftTree()
            val right   = this.right.getRightTree()
            val height1 = Math.max(left.getHeight(), center.getHeight()) + 1
            val temp    = Node(value, height1, left, center)
            val height2 = Math.max(temp.height, right.getHeight()) + 1

            return Node(this.right.getTreeValue(), height2, temp, right)
        }
        else     -> throw Exception("Error")
    }
}

//implements small right rotation of tree
fun AVLTree.rightRotation() : AVLTree {
    when(this) {
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> {
            val left    = this.left.getLeftTree()
            val center  = this.left.getRightTree()
            val height1 = Math.max(center.getHeight(), right.getHeight()) + 1
            val temp    = Node(value, height1, center, right)
            val height2 = Math.max(left.getHeight(), temp.height) + 1

            return Node(this.left.getTreeValue(), height2, left, temp)
        }
        else     -> throw Exception("Error")
    }
}

//restores balance of tree
fun AVLTree.balance() : AVLTree {
    when(this) {
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> {
            val balance    = right.getHeight() - left.getHeight()
            val leftLeft   = left.getLeftTree()
            val leftRight  = left.getRightTree()
            val rightLeft  = right.getLeftTree()
            val rightRight = right.getRightTree()

            // small left rotation
            if (balance == 2 && rightLeft.getHeight() <= rightRight.getHeight())
                return this.leftRotation()

            // small right rotation
            else if (balance == -2 && leftRight.getHeight() <= leftLeft.getHeight())
                return this.rightRotation()

            // big left rotation
            else if (balance == 2 && rightLeft.getHeight() > rightRight.getHeight()) {
                val temp   = right.rightRotation()
                val height = temp.getHeight() - left.getHeight()

                return (Node(value, height, left, temp)).leftRotation()
            }

            // big right rotation
            else if (balance == -2 && leftRight.getHeight() > leftLeft.getHeight()) {
                val temp   = left.leftRotation()
                val height = right.getHeight() - temp.getHeight()

                return (Node(value, height, temp, right)).rightRotation()
            }

            else
                return Node(value, height, left.balance(), right.balance())
        }
        else    -> throw Exception("Error")
    }
}

//inserts element in tree
fun AVLTree.insert(elem : Int) : AVLTree {
    when(this) {
        is Empty -> return Leaf(elem)
        is Leaf  -> {
            if (elem < value)
                return Node(value, height + 1, Leaf(elem), Empty())
            else
                return Node(value, height + 1, Empty(), Leaf(elem))
        }
        is Node  -> {
            if (elem < value) {
                val left = left.insert(elem)
                val height = Math.max(left.getHeight(), right.getHeight()) + 1

                return Node(value, height, left, right).balance()
            }
            else {
                val right = right.insert(elem)
                val height = Math.max(left.getHeight(), right.getHeight()) + 1

                return Node(value, height, left, right).balance()
            }
        }
        else     -> throw Exception("Error")
    }
}

//fold for tree
fun AVLTree.fold(f : (Int, Int) -> Int, acc : Int) : Int {
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

//removes element from tree
fun AVLTree.remove(elem : Int) : AVLTree {
    when(this) {
        is Empty -> return this
        is Leaf  -> {
            if (value == elem)
                return Empty()
            else
                return this
        }
        is Node  -> {
            if (value == elem) {
                if (left.getHeight() < right.getHeight()) {

                    //finds minimum element in right subtree
                    val temp   = right.fold({a, b -> Math.min(a, b)}, value)
                    val height = Math.max(left.getHeight(), right.getHeight()) + 1

                    return Node(temp, height, left, right.remove(temp)).balance()
                }
                else {

                    //finds maximum element in left subtree
                    val temp   = left.fold({a, b -> Math.max(a, b)}, 0)
                    val height = Math.max(left.getHeight(), right.getHeight()) + 1

                    return Node(temp, height, left.remove(temp), right).balance()
                }
            }
            else if (elem < value) {
                val temp   = left.remove(elem)
                val height = Math.max(right.getHeight(), temp.getHeight()) + 1

                return Node(value, height, temp, right).balance()
            }
            else {
                val temp   = right.remove(elem)
                val height = Math.max(temp.getHeight(), left.getHeight()) + 1

                return Node(value, height, left, temp).balance()
            }
        }
        else     -> throw Exception("Error")
    }
}

//returns true if tree contains given element
fun AVLTree.search(elem : Int) : Boolean {
    when(this) {
        is Empty -> return false
        is Leaf  -> return (value == elem)
        is Node  -> {
            if (value == elem)
                return true
            else if (elem < value)
                return (left.search(elem))
            else
                return (right.search(elem))
        }
        else     -> throw Exception("Error")
    }
}

//prints AVL tree
fun AVLTree.toText() {

    var leaf = 0
    var correction = -1

    //returns number of digits in value
    fun digits(value : Int) : Int {
        if (value == 0) return 0
        else return 1 + digits(value / 10)
    }

    //prints n spaces
    fun nSpaces(n : Int) : String {
        if (n == 0) return ""
        else return " " + nSpaces(n - 1)
    }

    //makes list of levels of tree
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

fun main (args : Array<String>) {
    val tree = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))
    tree.toText()
}