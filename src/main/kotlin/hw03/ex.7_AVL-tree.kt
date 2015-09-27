package hw03

abstract class Tree {}
open class Empty() : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree, val height: Int) : Tree() {}

fun Tree.getHeight(): Int{
    when (this){
        is Empty -> return 0
        is Node -> return height
    }
    return 0
}

fun Tree.bFactor(): Int{
    when (this) {
        is Empty -> return 0
        is Node -> return r.getHeight() - l.getHeight()
    }
    return 0
}

fun Tree.getLeftChild(): Tree{
    when (this){
        is Empty -> return Empty()
        is Node -> return l
    }
    return Empty()
}

fun Tree.getRightChild(): Tree{
    when (this){
        is Empty -> return Empty()
        is Node -> return r
    }
    return Empty()
}

fun Tree.getValue(): Int{
    when (this){
        is Empty -> return 0
        is Node -> return value
    }
    return 0


}
fun fixHeight(tree1: Tree, tree2: Tree): Int {
    val h1 = tree1.getHeight()
    val h2 = tree2.getHeight()
    if (h1 > h2) {
        return (h1 + 1)
    } else {
        return (h2 + 1)
    }
}

fun rotateLeft(tree: Tree): Tree {
    when (tree){
        is Empty -> Empty()
        is Node -> {
            val tmpRight = tree.getRightChild()
            val tmpRL = tmpRight.getLeftChild()
            val tmpRR = tmpRight.getRightChild()
            val tmpLeft = tree.getLeftChild()
            val height1 = fixHeight(tmpLeft,tmpRL)
            val temp = Node(tree.getValue(),tmpLeft,tmpRL,height1)
            val height2 = fixHeight(temp,tmpRR)
            return Node(tmpRight.getValue(),temp,tmpRR,height2)
        }
    }
    return Empty()
}

fun rotateRight(tree: Tree): Tree {
    when (tree){
        is Empty -> Empty()
        is Node -> {
            val tmpRight = tree.getRightChild()
            val tmpLeft = tree.getLeftChild()
            val tmpLL = tmpLeft.getLeftChild()
            val tmpLR = tmpLeft.getRightChild()
            val height1 = fixHeight(tmpRight,tmpLR)
            val temp = Node(tree.getValue(),tmpLR,tmpRight,height1)
            val height2 = fixHeight(temp,tmpLL)
            return Node(tmpLeft.getValue(),tmpLL,temp,height2)
        }
    }
    return Empty()
}

fun balance(tree: Tree): Tree{
    val bal = tree.bFactor()
    val tmpRight = tree.getRightChild()
    val tmpLeft = tree.getLeftChild()

    if (bal == 2){
        if (tmpRight.bFactor() < 0){
            val tmpRot1 = rotateRight(tmpRight)
            val hR = tmpRot1.getHeight() - tmpLeft.getHeight()
            return rotateLeft(Node(tree.getValue(),tmpLeft,tmpRot1,hR))
        }
        return rotateLeft(tree)
    }
    if (bal == -2){
        if (tmpLeft.bFactor() > 0){
            val tmpRot2 = rotateLeft(tmpLeft)
            val hL = tmpRot2.getHeight() - tmpRight.getHeight()
            return rotateRight(Node(tree.getValue(),tmpLeft,tmpRot2,hL))
        }
        return rotateRight(tree)
    }
    return tree

}

fun insertAVL (tree: Tree, key: Int): Tree{
    val valTree = tree.getValue()
    val tmpLeft = tree.getLeftChild()
    val tmpRight = tree.getRightChild()
    when (tree){
        is Empty -> return Node (key,Empty(),Empty(),1)
        is Node -> {
            if (key < valTree){
                return balance(Node(valTree,insertAVL(tmpLeft,key),tmpRight,fixHeight(tmpLeft,tmpRight)))
            }
            else {
                return balance(Node(valTree,tmpLeft,insertAVL(tmpRight,key),fixHeight(tmpLeft,tmpRight)))
            }
        }
    }
    return tree

}

fun searchAVL (tree: Tree, key: Int): Boolean {
    val valTree = tree.getValue()
    val tmpLeft = tree.getLeftChild()
    val tmpRight = tree.getRightChild()
    when (tree) {
        is Empty -> return false
        is Node ->{
            if (key < valTree){
                return (searchAVL(tmpLeft, key))
            }
            else if (key > valTree){
                return (searchAVL(tmpRight,key))
            }
            else return true
        }
    }
    return false
}


fun removeAVL (tree: Tree, key: Int): Tree{
    val valTree = tree.getValue()
    val tmpLeft = tree.getLeftChild()
    val tmpRight = tree.getRightChild()

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

    when (tree){
        is Empty -> Empty()
        is Node ->{
            if (key > valTree){
                return balance(Node(valTree, tmpLeft, removeAVL(tmpRight,key),fixHeight(tmpLeft,tmpRight)))
            }
            else if (key < valTree){
                return balance(Node(valTree,removeAVL(tmpLeft,key),tmpRight,fixHeight(tmpLeft,tmpRight)))
            }
            else{
                if ((tmpRight.isEmpty()) && (tmpLeft.isEmpty())){
                    return Empty()
                }
                else if (tmpLeft.isEmpty()){
                    return balance(tmpRight)
                }
                else if (tmpRight.isEmpty()){
                    return balance(tmpLeft)
                }
                else{
                    val tmpLR = tmpLeft.getRightChild()
                    val tmpLL = tmpLeft.getLeftChild()
                    if (tmpLL.isEmpty()){
                        return balance(Node(tmpLR.findR(),tmpLL,removeAVL(tmpLR,tmpLR.findR()),fixHeight(tmpLR,tmpLL)))
                    }
                    else{
                        return balance(Node(tmpLL.findL(),removeAVL(tmpLL,tmpLL.findL()),tmpLR,fixHeight(tmpLR,tmpLL)))
                    }
                }

            }

        }
    }
    return tree
}


fun Tree.isEmpty(): Boolean{
    when (this){
        is Empty -> return true
        else -> return false
    }
}

fun printAVL (tree: Tree){
    when (tree){
        is Empty -> println("|_|")
        is Node -> {
            if ((tree.getRightChild().isEmpty()) && (tree.getLeftChild().isEmpty())){
                print("-| ${tree.getValue()} |-")
            }
            else if (tree.getRightChild().isEmpty()) {
                println("*| ${tree.getValue()} |")
                println("| ")
                println("V ")
                printAVL(tree.getLeftChild())
                printAVL(tree.getRightChild())

            }
            else if (tree.getLeftChild().isEmpty()){
                println("| ${tree.getValue()} |*")
                println("    | ")
                println("    V ")
                printAVL(tree.getLeftChild())
                printAVL(tree.getRightChild())


            }

            else {
                println("*| ${tree.getValue()} |*")
                println("|          |")
                println("V          V")
                printAVL(tree.getLeftChild())
                printAVL(tree.getRightChild())

            }
        }

    }
}


fun main (args : Array<String>) {
    val tree2 = Node (6,Empty(),Node(7,Node(3,Empty(),Empty(),1),Node(10,Empty(),Empty(),1),2),3)
    printAVL(tree2)
    println()
    println("+++++++++++++++++++")
    val res1 = balance(tree2)
    printAVL(res1)

}