package hw03

/*  AVL tree: search, add, remove element  made by Guzel Garifullina
   Estimated time 3 hour
   real time      4 hours
*/
abstract class AVL{
    abstract internal fun calcHeightAndDiff() : Pair<Int, Int>
    abstract internal fun height(): Int
    abstract internal fun diff() : Int
}
open class Empty() : AVL(){
    override internal fun calcHeightAndDiff() : Pair<Int, Int>
    { return Pair(-1, 0)}
    override internal fun height(): Int{
        return -1
    }
    override internal fun diff() : Int{
        return 0
    }
}
open class Node (val key : Int,
                 left : AVL,
                 right : AVL) : AVL(){
    public var leftChild: AVL = left
        get() = $leftChild
        set(newLeft: AVL) {
            $leftChild = newLeft
            heightAndDiff = calcHeightAndDiff()
        }
    public var rightChild: AVL = right
        get() = $rightChild
        set(newRight: AVL) {
            $rightChild = newRight
            heightAndDiff = calcHeightAndDiff()
        }
    //suppose that children has right heights
    override internal fun calcHeightAndDiff() : Pair<Int, Int>{
        val height =  Math.max(rightChild.height(), leftChild.height()) + 1
        val diff  = rightChild.height() -  leftChild.height()
        return Pair(height, diff)
    }
    private var heightAndDiff : Pair<Int, Int> = calcHeightAndDiff()
    override fun height(): Int = heightAndDiff.first
    override fun diff() : Int  =  heightAndDiff.second
}

fun generateTree(lValue : Int, rValue : Int): AVL{
    if (lValue > rValue) return Empty()
    if (lValue == rValue ) return Node(lValue, Empty(), Empty())
    val middle =  lValue + (rValue - lValue) / 2
    val key = middle
    val left = generateTree(lValue,middle - 1)
    var right = generateTree(middle + 1, rValue)
    return Node(key, left, right )
}

public fun  AVL.print() {
    fun  AVL.print_ (maxLevel : Int) {
        fun makeSpaces(n : Int) : String{
            val arr = Array(n, {' '}).toCharArray()
            return String(arr)
        }
        val num = Math.pow(2.0, (maxLevel).toDouble()).toInt()
        val spaces = makeSpaces(num - 1)
        when (this){
            is Empty -> println("$spaces|empty")
            is Node -> {
                if (height() == 0){
                    println("$spaces|$key")
                }
                else {
                    println("$spaces|$key")
                    rightChild.print_(maxLevel + 1)
                    leftChild.print_(maxLevel + 1)
                }
            }
        }
    }
    this.print_(0)
}

public fun AVL.find (elem : Int): Boolean {
    when (this){
        is Empty -> return false
        is Node ->
                if (key == elem){
                    return true
                }
                else{
                    if (key < elem){
                        return rightChild.find(elem)
                    }
                    else return  leftChild.find(elem)
                }
        else -> throw  Exception("Unknown class")
    }
}

internal fun unWrap (tree: AVL) : Node{
    when (tree){
        is Empty -> throw Exception("Can't unWrap")
        is Node  -> return tree
        else     -> throw Exception("Unknown class")
    }
}

private fun makeNewTree (key : Int, left : AVL, right : AVL) : AVL{
    val newTree = Node(key, left, right )
    return (balance(newTree))
}

fun addToAVL(tree : AVL, elem : Int) : AVL {
    when (tree){
        is Empty ->  return Node (elem, Empty(), Empty())
        is Node  -> {
            when {
                tree.key > elem -> {
                    val left = addToAVL(tree.leftChild, elem)
                    return  makeNewTree (tree.key, left, tree.rightChild)
                }
                tree.key == elem -> {
                    return tree
                }
                else ->  {
                    val right = addToAVL(tree.rightChild, elem)
                    return   makeNewTree (tree.key, tree.leftChild, right)
                }
            }
        }
        else -> throw  Exception("Unknown class")
    }
}

fun balance(tree : AVL): AVL{
    fun rightRotation(tree : AVL): AVL{
        when (tree){
            is Empty -> throw Exception("rightRotation is broken")
            is Node ->{
                val left = unWrap(tree.leftChild)
                val r    = tree.rightChild
                var b = left.key
                val l = left.leftChild
                val c = left.rightChild
                if (left.diff() <= 0) {
                    //small rotation
                    return Node(b, l , Node(tree.key, c, r))
                }
                // big rotation
                val cNode = unWrap(c)
                val central = cNode.key
                val m = cNode.leftChild
                val n = cNode.rightChild
                return Node(central, Node(b, l, m), Node(tree.key, n, r))
            }
            else -> throw Exception("Unknown class")
        }
    }
    fun leftRotation(tree : AVL): AVL{
        when (tree){
            is Empty -> throw Exception("leftRotation is broken")
            is Node ->{
                val right = unWrap(tree.rightChild)
                val l = tree.leftChild
                var b = right.key
                val c = right.leftChild
                val r = right.rightChild
                if (right.diff() >= 0) {
                    //small rotation
                    return Node(b, Node(tree.key, l, c), r)
                }
                // big rotation
                val cNode = unWrap(c)
                val central = cNode.key
                val m = cNode.leftChild
                val n = cNode.rightChild
                return Node(central,Node(tree.key, l, m), Node(b, n, r))
            }
            else -> throw Exception("Unknown class")
        }
    }
    when (tree){
        is Empty -> return Empty()
        is Node  -> {
            if (Math.abs(tree.diff()) != 2){
                return tree
            }
            if (tree.diff() < 0){
                return rightRotation(tree)
            }
            else return leftRotation(tree)
        }
        else -> throw Exception("Unknown class")
    }
}

fun removeInAVL(tree : AVL,elem : Int): AVL {
    fun findSubstitute (tree : AVL, inRight : Boolean) : Pair <AVL, Int>{
        when (tree){
            is Empty -> throw Exception("Can't find substitute")
            is Node  -> {
                if (tree.height() == 0){
                    return Pair(Empty(), tree.key)
                }
                // case when substitute element has a child
                if ((tree.height() == 1) &&(Math.abs(tree.diff()) == 1)){
                    if (inRight && (tree.diff() < 0)){
                        return Pair(tree.leftChild, tree.key)
                    }
                    else if (!inRight && (tree.diff() > 0)){
                        return Pair(tree.rightChild, tree.key)
                    }
                }
                val pair : Pair <AVL, Int>
                if (inRight){
                    pair = findSubstitute(tree.rightChild, true)
                    return Pair(makeNewTree(tree.key, tree.leftChild, pair.first), pair.second)
                }
                else{
                    pair = findSubstitute(tree.leftChild, false)
                    return Pair(makeNewTree(tree.key, pair.first, tree.rightChild), pair.second)
                }
            }
            else -> throw  Exception ("Unknown class")
        }
    }
    when (tree) {
        is Empty -> return Empty()
        is Node ->
            when {
                (tree.key > elem) -> {
                    val left = removeInAVL(tree.leftChild, elem)
                    return makeNewTree(tree.key, left, tree.rightChild)
                }

                (tree.key < elem) -> {
                    val right = removeInAVL(tree.rightChild, elem)
                    return makeNewTree(tree.key, tree.leftChild, right)
                }
            // case when tree.value == elem
                else -> {
                    when {
                        (tree.height() == 0) -> {
                            // isLeaf
                            return Empty()
                        }
                        (tree.diff() < 0) -> {
                            val pair = findSubstitute (tree.leftChild, true)
                            return makeNewTree(pair.second, pair.first, tree.rightChild)
                        }
                        else -> {
                            val pair = findSubstitute (tree.rightChild, false)
                            return makeNewTree(pair.second, tree.leftChild, pair.first)
                        }
                    }
                }
            }
        else -> throw  Exception("Unknown class")
    }
}

private fun AVL.toText(): String {
    when (this) {
        is Empty -> return "Empty()"
        is Node -> {
            val lText = leftChild.toText()
            val rText = rightChild.toText()
            val vText = key
            return "Node($vText,$lText, $rText )"
        }
        else -> throw Exception("Unknown class")
    }
}
fun main(args: Array<String>) {
    var t : AVL = Empty()
    t = addToAVL(t, 3)
    t = addToAVL(t, -1)
    t = addToAVL(t, 6)
    t = addToAVL(t, 8)
    t = addToAVL(t, -2)
    t = addToAVL(t, 4)
    t = addToAVL(t, 5)
    t= removeInAVL(t, 6)
    t.print()
}

