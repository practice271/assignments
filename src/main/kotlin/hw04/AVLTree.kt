package hw04

import java.util.*

abstract class Tree {}
class Empty() : Tree() {}
class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {
    val height = Math.max(l.getHeight(),r.getHeight())+ 1
}

private fun Tree.getHeight(): Int = if (this is Node) height else 0

public fun Tree.getLeftChild(): Tree = if (this is Node) l else Empty()

public fun Tree.getRightChild(): Tree = if (this is Node) r else Empty()

private fun Tree.getValue(): Int = if (this is Node) value else 0

private fun Tree.isEmpty(): Boolean = if (this is Node) true else false

private fun Tree.bFactor(): Int{
    when (this) {
        is Empty -> return 0
        is Node -> return r.getHeight() - l.getHeight()
    }
    return 0
}

private fun rotateLeft(tree: Tree): Tree {
    when (tree){
        is Empty -> Empty()
        is Node -> {
            val tmpRight = tree.getRightChild()
            val tmpRL = tmpRight.getLeftChild()
            val tmpRR = tmpRight.getRightChild()
            val tmpLeft = tree.getLeftChild()
            val temp = Node(tree.getValue(),tmpLeft,tmpRL)
            return Node(tmpRight.getValue(),temp,tmpRR)
        }
    }
    return Empty()
}

private fun rotateRight(tree: Tree): Tree {
    when (tree){
        is Empty -> Empty()
        is Node -> {
            val tmpRight = tree.getRightChild()
            val tmpLeft = tree.getLeftChild()
            val tmpLL = tmpLeft.getLeftChild()
            val tmpLR = tmpLeft.getRightChild()
            val temp = Node(tree.getValue(),tmpLR,tmpRight)
            return Node(tmpLeft.getValue(),tmpLL,temp)
        }
    }
    return Empty()
}

internal fun balance(tree: Tree): Tree{
    val bal = tree.bFactor()
    val tmpRight = tree.getRightChild()
    val tmpLeft = tree.getLeftChild()

    if (bal == 2){
        if (tmpRight.bFactor() < 0){
            val tmpRot1 = rotateRight(tmpRight)
            return rotateLeft(Node(tree.getValue(),tmpLeft,tmpRot1))
        }
        return rotateLeft(tree)
    }
    if (bal == -2){
        if (tmpLeft.bFactor() > 0){
            val tmpRot2 = rotateLeft(tmpLeft)
            return rotateRight(Node(tree.getValue(),tmpLeft,tmpRot2))
        }
        return rotateRight(tree)
    }
    return tree

}

fun Tree.insertAVL (key: Int): Tree{
    val valTree = this.getValue()
    val tmpLeft = this.getLeftChild()
    val tmpRight = this.getRightChild()
    when (this){
        is Empty -> return Node (key,Empty(),Empty())
        is Node -> {
            if (key < valTree){
                return balance(Node(valTree,tmpLeft.insertAVL(key),tmpRight))
            }
            else {
                return balance(Node(valTree,tmpLeft,tmpRight.insertAVL(key)))
            }
        }
        else -> throw Exception("Error")
    }
}

fun Tree.toList(): List<Int>{
    when (this){
        is Empty -> return listOf()
        is Node ->{
            val l = this.l.toList()
            val r = this.r.toList()
            val list = listOf(this.value).toLinkedList()
            for (i in l) list.add(i)
            for (i in r) list.add(i)
            return list
        }
        else -> throw Exception("Error")
    }
}

fun Tree.searchAVL (key: Int): Boolean {
    val valTree = this.getValue()
    val tmpLeft = this.getLeftChild()
    val tmpRight = this.getRightChild()
    when (this) {
        is Empty -> return false
        is Node ->{
            if (key < valTree){
                return (tmpLeft.searchAVL(key))
            }
            else if (key > valTree){
                return (tmpRight.searchAVL(key))
            }
            else return true
        }
    }
    return false
}


fun Tree.removeAVL (key: Int): Tree{
    val valTree = this.getValue()
    val tmpLeft = this.getLeftChild()
    val tmpRight = this.getRightChild()

    fun Tree.findR(): Int{
        when (this){
            is Empty -> {}
            is Node ->{
                if (tmpLeft.isEmpty()) return valTree
                else return tmpLeft.findR()
            }
        }
        return 0
    }

    fun Tree.findL(): Int{
        when (this){
            is Empty -> {}
            is Node ->{
                if (tmpRight.isEmpty()) return valTree
                else return tmpRight.findL()
            }
        }
        return 0
    }

    when (this){
        is Empty -> Empty()
        is Node ->{
            if (key > valTree){
                return balance(Node(valTree, tmpLeft, tmpRight.removeAVL(key)))
            }
            if (key < valTree){
                return balance(Node(valTree,tmpLeft.removeAVL(key),tmpRight))
            }
            if (tmpRight.isEmpty() && tmpLeft.isEmpty()){return Empty()}
            if (tmpLeft.isEmpty()){return balance(tmpRight)}
            if (tmpRight.isEmpty()){return balance(tmpLeft)}
            else{
                val tmpLR = tmpLeft.getRightChild()
                val tmpLL = tmpLeft.getLeftChild()
                if (tmpLL.isEmpty()){
                    return balance(Node(tmpLR.findR(),tmpLL,tmpLR.removeAVL(tmpLR.findR())))
                }
                else{
                    return balance(Node(tmpLL.findL(),tmpLL.removeAVL(tmpLL.findL()),tmpLR))
                }
            }
        }
    }
    return this
}

fun List<Int>.delRep(): List<Int>{
    val list = listOf(this[0]).toLinkedList()
    for (i in this){
        if (!list.contains(i)) list.add(i)
    }
    return list
}



