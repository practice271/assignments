package homework.hw03

import java.util.*


abstract class Tree() {}

class Empty : Tree()
class Node(val value : Int, var height : Int, val l : Tree, val r: Tree) : Tree() {}


fun height(tree : Tree) : Int {
    when (tree) {
        is Empty -> return 0
        is Node -> return tree.height
        else -> return throw Exception("Error")
    }
}

fun value(tree : Tree):Int {
    when (tree) {
        is Node -> return tree.value
        is Empty -> return 0
        else -> throw Exception ("Error")
    }
}

fun bfactor(tree : Tree) : Int {
    when (tree) {
        is Node -> return height(tree.l) - height(tree.r)
        is Empty -> return 0
        else -> throw Exception("Error")
    }
}

fun fixHeight(tree : Tree) {
    when (tree) {
        is Node -> tree.height = Math.max(height(tree.l), height(tree.r)) + 1
        is Empty -> 0
        else -> throw Exception("Error")
    }
}



fun Tree.toText() : String {
    fun Tree.toText_(): List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Node -> {
                val lText = l.toText_().map { "  $it" }
                val rText = r.toText_().map { "  $it" }
                val vText = listOf("$value\n")
                return rText + vText + lText
            }
            else -> throw Exception("Unknown class")
        }
    }

    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}


fun Tree.l() : Tree {
    when (this) {
        is Node -> return this.l
        is Empty -> return Empty()
        else -> throw Exception("Error")
    }
}

fun Tree.r() : Tree {
    when (this) {
        is Node -> return this.r
        is Empty -> return Empty()
        else -> throw Exception("Error")

    }
}

fun Tree.leftRotation() : Tree {

    val temp = Node(value(this), height(this), this.l().r(), this.r())
    val temp2 = Node(value(this.l()), height(this.l()), this.l().l(), temp)
    fixHeight(temp)
    fixHeight(temp2)
    return temp2
}

fun Tree.rightRotation() : Tree {
    val temp = Node(value(this), height(this), this.l(), this.r().l())
    val temp2 = Node(value(this.r()), height(this.r()), temp, this.r().r())
    return temp2
}

fun Tree.balance() : Tree {
    fixHeight(this)
    if (bfactor(this) == 2) {
        if (bfactor(this.r()) < 0) {
            val temp = this.r().rightRotation()
            return temp.leftRotation()
        }
        return this.leftRotation()
    }
    if (bfactor(this) == -2) {
        if (bfactor(this.l()) > 0) {
            val temp = this.l().leftRotation()
            return temp.rightRotation()

        }
        return this.rightRotation()
    }
    return this
}


fun Tree.Insert(v : Int) : Tree {
    when (this) {
        is Empty -> return Node(v, 1, Empty(), Empty())
        is Node -> {
            if (v < this.value) {
                val temp = this.l.Insert(v)
                return Node(this.value, this.height, temp, this.r).balance()
            } else if (v > this.value) {

                val temp = this.r.Insert(v)
                return Node(this.value, this.height, this.l, temp).balance()
            } else return this
        }
        else -> throw Exception("Error")
    }
}



fun Tree.lastLeft() :Int {
    when (this) {
        is Node ->
            when (this.l) {
                is Empty -> return this.value
                is Node -> return this.l.lastLeft()
                else -> throw Exception("Error")
            }
        is Empty -> return 0
        else -> throw Exception ("Error")
    }
}


fun Tree.Delete(v : Int) : Tree {
    when (this) {
        is Empty -> return Empty()
        is Node -> {
            if (this.value < v) {
                return Node(this.value, this.height, this.l, this.r.Delete(v)).balance()
            } else if (this.value > v) {
                return Node(this.value, this.height, this.l.Delete(v), this.r).balance()
            } else {
                when (this.l) {
                    is Empty -> return this.r
                    is Node -> {
                        when (this.r) {
                            is Empty -> return this.l
                            is Node -> {
                                when (this.r.l) {
                                    is Empty -> return Node(this.r.value, this.r.height, this.l, this.r.r).balance()
                                    is Node -> {
                                        val temp = this.r.l.lastLeft()
                                        return Node(temp, this.r.height, this.l, this.r.Delete(temp)).balance()
                                    }
                                    else -> throw Exception("Error")
                                }
                            }

                            else -> throw Exception("Error")
                        }
                    }
                    else -> throw Exception("Error")

                }
            }
        }
        else -> throw Exception("Error")
    }
}

fun Tree.Search(v : Int) : Boolean {
    when (this) {
        is Empty -> return false
        is Node -> {
            if (this.value == v) return true
            if (this.value > v) return this.l.Search(v)
            else return this.r.Search(v)
        }
        else -> throw Exception("Error")
    }
}



fun Tree.toArray() : Array<Int> {
    val temp = ArrayList<Int>()
    val res = arrayOf(Int)
    fun CLR(tree: Tree) {
        when (tree) {
            is Empty -> 0
            is Node -> {
                temp.add(0, tree.value)
                CLR(tree.l)
                CLR(tree.r)
            }
            else -> throw Exception("Errro")


        }
    }
    CLR(this)
    return temp.toTypedArray()
}


fun main(args : Array<String>) {}
