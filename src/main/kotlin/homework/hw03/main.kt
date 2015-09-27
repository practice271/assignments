package homework.hw03

open class AVLTree() {}
class Node(var value:Int, var height:Int, var left:AVLTree, var right:AVLTree):AVLTree() {}
class Leaf(var value:Int, var height:Int = 1):AVLTree() {}
class Empty(var  height: Int = 0):AVLTree() {}

fun AVLTree.getHeight():Int{
    when(this){
        is Node  -> return this.height
        is Leaf  -> return this.height
        is Empty -> return this.height
        else     -> throw Exception("Error")
    }
}

fun AVLTree.getValue():Int{
    when(this){
        is Node  -> return this.value
        is Leaf  -> return this.value
        is Empty -> return 0
        else     -> throw Exception("Error")
    }
}

fun AVLTree.getLeftTree():AVLTree{
    when(this){
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> return left
        else     -> throw Exception("Error")
    }
}

fun AVLTree.getRightTree():AVLTree {
    when (this){
        is Empty -> return this
        is Leaf  -> return this
        is Node  -> return right
        else -> throw Exception("Error")
    }
}

fun AVLTree.setRightTree(tree:AVLTree){
    when(this){
        is Node -> this.right = tree
    }
}

fun AVLTree.setLeftTree(tree:AVLTree){
    when(this){
        is Node -> this.left = tree
        else    -> throw Exception("Error")
    }
}

fun AVLTree.getBalanceFactor():Int{
    when(this){
        is Node  -> return this.right.getHeight() - this.left.getHeight()
        is Leaf  -> return 0
        is Empty -> return 0
        else     -> throw Exception("Error")
    }
}

fun AVLTree.fixHeight(){
    val tmp =  Math.max(this.getRightTree().getHeight(), this.getLeftTree().getHeight()) + 1
    when(this){
        is Node ->  this.height = tmp
        is Leaf ->  this.height = tmp
        is Empty -> this.height = tmp
    }
}

fun AVLTree.turnRight():AVLTree{
    var tmp = this.getLeftTree()
    this.setLeftTree(tmp.getRightTree())
    tmp.setRightTree(this)
    this.fixHeight()
    tmp.fixHeight()
    return tmp
}

fun AVLTree.turnLeft():AVLTree{
    var tmp = this.getRightTree()
    this.setRightTree(tmp.getLeftTree())
    tmp.setLeftTree(this)
    this.fixHeight()
    tmp.fixHeight()
    return tmp
}

fun AVLTree.balance():AVLTree{
    this.fixHeight()

    if (this.getBalanceFactor() == 2){
        if (this.getRightTree().getBalanceFactor() < 0){
            this.setRightTree(this.getRightTree().turnRight())
        }
        return this.turnLeft()
    }
    if (this.getBalanceFactor() == -2){
        if (this.getLeftTree().getBalanceFactor() > 0){
            this.setLeftTree(this.getLeftTree().turnLeft())
        }
        return this.turnRight()
    }
    return this
}

fun AVLTree.insert(key:Int):AVLTree{
    when(this){
        is Empty -> return Leaf(key)
        is Leaf  -> {
                if (key < this.getValue()){
                    return Node(this.value, this.height+1, Leaf(key), Empty())
                }
                else{
                    return Node(this.value, this.height+1, Empty(), Leaf(key))
                }
        }
        is Node -> {
            if (key < this.getValue()){
                var tmp = this.getLeftTree()
                this.setLeftTree(tmp.insert(key))
            }
            else{
                var tmp = this.getRightTree()
                this.setRightTree(tmp.insert(key))
            }
        }
        else   -> throw Exception("Error")
    }
    return this.balance()
}

fun AVLTree.findMin():AVLTree{
    if (this is Node) return this.getLeftTree().findMin()
    else return this
}

fun AVLTree.removeMin():AVLTree{
    var tmp = this.getLeftTree()
    if (tmp is Empty) return this.getRightTree()
    this.setLeftTree(this.getLeftTree().removeMin())
    return this.balance()
}

/*
 * Not working remove method
 */

fun AVLTree.remove(key:Int):AVLTree{
    if (key < this.getValue()){
        this.setLeftTree(this.getLeftTree().insert(key))
    }
    else if (key > this.getValue()){
        this.setRightTree(this.getRightTree().insert(key))
    }
    else{
        var tmpLeft = this.getLeftTree()
        var tmpRight = this.getRightTree()

        if (tmpRight is Empty) return tmpLeft
        var nodeMin = this.findMin()
        nodeMin.setRightTree(tmpRight.removeMin())
        nodeMin.setLeftTree(this)

    }
    return this.balance()
}

fun main(argv:Array<String>){

}