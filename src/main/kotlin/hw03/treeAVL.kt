package hw03

/*  AVL tree: search, add, remove element  made by Guzel Garifullina
   Estimated time 3 hour
   real time      4 hours
*/

open class Keys (var value : Int){
    var height = 0
    var diff = 0
}

abstract class AVL{}
open class Empty() : AVL() {}
open class Node (var keys : Keys, var left : AVL, var right : AVL) : AVL(){}

fun generateTree(lValue : Int, rValue : Int): AVL{
    if (lValue > rValue) return Empty()
    if (lValue == rValue ) return Node(Keys(lValue), Empty(), Empty())
    val middle =  lValue + (rValue - lValue) / 2
    val key = Keys(middle)
    val left = generateTree(lValue,middle - 1)
    var right = generateTree(middle + 1, rValue)
    key.height = getHeight2(left, right)
    key.diff   = getDiff(left, right)
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
                if (keys.height == 0){
                    println("$spaces|${keys.value}")
                }
                else {
                    println("$spaces|${keys.value}")
                    right.print_(maxLevel + 1)
                    left.print_(maxLevel + 1)
                }
            }
        }
    }
    this.print_(0)
}

public fun AVL.find (elem : Int): Boolean {
    when (this){
        is Empty -> return false
        is Node  ->
                if (keys.value == elem){
                    return true
                }
                else{
                    if (keys.value < elem){
                        return right.find(elem)
                    }
                    else return  left.find(elem)
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

private fun getHeight (tree : AVL): Int {
    when (tree){
        is Empty -> return -1
        is Node ->  return tree.keys.height
        else -> throw Exception("getHeight function is broken")
    }
}
private fun getHeight (key : Keys): Int {
    return key.height
}

private fun getHeight2 (tree : AVL, tree2 : AVL) : Int{
    return Math.max(getHeight(tree),getHeight(tree2)) + 1
}
private fun getHeight2 (key : Keys, tree2 : AVL) : Int{
    return Math.max(getHeight(key),getHeight(tree2)) + 1
}
private fun getHeight2 (tree1 : AVL, key : Keys) : Int{
    return Math.max(getHeight(tree1),getHeight(key)) + 1
}
private fun getHeight2 (key1 : Keys, key2 : Keys) : Int{
    return Math.max(getHeight(key1),getHeight(key2)) + 1
}

private  fun getDiff (leftTree : AVL, rightTree : AVL) : Int{
    return getHeight(rightTree) - getHeight(leftTree)
}
private  fun getDiff (leftTree : AVL, rightKey : Keys) : Int{
    return getHeight(rightKey) - getHeight(leftTree)
}
private  fun getDiff (leftKey : Keys, rightTree : AVL) : Int{
    return getHeight(rightTree) - getHeight(leftKey)
}
private  fun getDiff (leftKey : Keys, rightKey : Keys) : Int{
    return getHeight(rightKey) - getHeight(leftKey)
}

private fun getKeys (value : Int, left : AVL, right : AVL) : Keys{
    val key = Keys(value)
    key.height = getHeight2(left, right)
    key.diff   = getDiff(left, right)
    return (key)
}

private fun makeNewTree (value : Int, left : AVL, right : AVL) : AVL{
    var key =  getKeys(value, left, right)
    val newTree = Node(key, left, right )
    return (balance(newTree))
}

fun addToAVL(tree : AVL, elem : Int) : AVL {
    when (tree){
        is Empty ->  return Node (Keys(elem), Empty(), Empty())
        is Node  -> {
            if (tree.keys.value > elem){
                val left = addToAVL(tree.left, elem)
                return  makeNewTree (tree.keys.value, left, tree.right)
            }
            else {
                val right = addToAVL(tree.right, elem)
                return   makeNewTree (tree.keys.value, tree.left, right)
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
                val left = unWrap(tree.left)
                val r    = tree.right
                var b = left.keys
                val l = left.left
                val c = left.right
                if (b.diff <= 0) {
                    //small rotation
                    val a = getKeys(tree.keys.value, c, r)
                    b.height = getHeight2(l, a)
                    b.diff   = getDiff(l, a)
                    return Node(b, l , Node(a, c, r))
                }
                // big rotation
                val cNode = unWrap(c)
                val central = cNode.keys
                val m = cNode.left
                val n = cNode.right
                val a = getKeys(tree.keys.value, n, r)
                b  = getKeys(left.keys.value, l, m)
                central.height = getHeight2(b, a)
                central.diff   = getDiff(b, a)
                return Node(central, Node(b, l, m), Node(a, n, r))
            }
            else -> throw Exception("Unknown class")
        }
    }
    fun leftRotation(tree : AVL): AVL{
        when (tree){
            is Empty -> throw Exception("leftRotation is broken")
            is Node ->{
                val right = unWrap(tree.right)
                val l = tree.left
                var b = right.keys
                val c = right.left
                val r = right.right
                if (b.diff >= 0) {
                    //small rotation
                    val a = getKeys(tree.keys.value, l, c)
                    b.height = getHeight2(a, r)
                    b.diff   = getDiff(a, r)
                    return Node(b, Node(a, l, c), r)
                }
                // big rotation
                val cNode = unWrap(c)
                val central = cNode.keys
                val m = cNode.left
                val n = cNode.right
                val a = getKeys(tree.keys.value, l, m)
                b = getKeys(b.value,n, r)
                central.height = getHeight2(a, b)
                central.diff   = getDiff(a, b)
                return Node(central,Node(a, l, m), Node(b, n, r))
            }
            else -> throw Exception("Unknown class")
        }
    }
    when (tree){
        is Empty -> return Empty()
        is Node  -> {
            if (Math.abs(tree.keys.diff) != 2){
                return tree
            }
            if (tree.keys.diff < 0){
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
                if (tree.keys.height == 0){
                    return Pair(Empty(), tree.keys.value)
                }
                // case when substitute element has a child
                if ((tree.keys.height == 1) &&(Math.abs(tree.keys.diff) == 1)){
                    if (inRight && (tree.keys.diff < 0)){
                        return Pair(tree.left, tree.keys.value)
                    }
                    else if (!inRight && (tree.keys.diff > 0)){
                        return Pair(tree.right, tree.keys.value)
                    }
                }
                val pair : Pair <AVL, Int>
                if (inRight){
                    pair = findSubstitute(tree.right, true)
                    return Pair(makeNewTree(tree.keys.value, tree.left, pair.first), pair.second)
                }
                else{
                    pair = findSubstitute(tree.left, false)
                    return Pair(makeNewTree(tree.keys.value, pair.first, tree.right), pair.second)
                }
            }
            else -> throw  Exception ("Unknown class")
        }
    }
    when (tree){
        is Empty ->  return Empty()
        is Node  -> {
            if (tree.keys.value > elem){
                val left = removeInAVL(tree.left, elem)
                return  makeNewTree(tree.keys.value, left, tree.right)
            }
            if (tree.keys.value < elem) {
                val right = removeInAVL(tree.right, elem)
                return makeNewTree(tree.keys.value, tree.left, right)
            }
            // case when tree.value == elem
            if (tree.keys.height == 0){ // isLeaf
                return Empty()
            }
            val pair : Pair<AVL,Int>
            if (tree.keys.diff < 0){
                pair = findSubstitute (tree.left, true)
                return makeNewTree(pair.second, pair.first, tree.right)
            }
            else {
                pair = findSubstitute (tree.right, false)
                return makeNewTree(pair.second, tree.left, pair.first)
            }
        }
        else -> throw  Exception("Unknown class")
    }
}

private fun AVL.toText(): String {
    when (this) {
        is Empty -> return "Empty()"
        is Node -> {
            val lText = left.toText()
            val rText = right.toText()
            val vText = keys.value
            return "Node(Keys($vText),$lText, $rText )"
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

