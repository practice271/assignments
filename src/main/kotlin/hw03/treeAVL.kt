package hw03

/*  AVL tree: search, add, remove element  made by Guzel Garifullina
   Estimated time 3 hour
   real time      4 hours
*/

open class Keys (var value : Int){
    var height = 0;
    var diff = 0;
}

abstract class AVL{}
open class Empty() : AVL() {}
open class Node (var keys : Keys, var left : AVL, var right : AVL) : AVL(){}

public fun AVL.setHeightsAll (): Int {
    return this.fold<Int>(-1, {keys,left, right ->
        keys.diff = right - left
        keys.height = Math.max(left, right) + 1
        keys.height
    })
}

fun generateTree(lValue : Int, rValue : Int): AVL{
    fun generate(lValue : Int, rValue : Int): AVL{
        if (lValue > rValue) return Empty()
        if (lValue == rValue ) return Node(Keys(lValue), Empty(), Empty())
        val middle =  lValue + (rValue - lValue) / 2
        return Node(Keys(middle), generate(lValue,middle - 1), generate(middle + 1, rValue) )
    }
    val tree = generate(lValue, rValue)
    tree.setHeightsAll()
    return tree
}

public fun AVL.toText(): String {
    when (this) {
        is Empty -> return "Empty"
        is Node -> {
            val lText = left.toText()
            val rText = right.toText()
            val vText = keys.value
            return "Node($lText, $vText, $rText )"
        }
        else -> throw Exception("Unknown class")
    }
}

private fun <B>AVL.fold(fEmpty : B, fNode : (Keys, B, B) -> B) : B {
    when (this) {
        is Empty -> return fEmpty
        is Node  -> return fNode(keys,
                left.fold(fEmpty, fNode),
                right.fold(fEmpty, fNode))
        else -> throw Exception("Unknown class")
    }
}
/////delete
private fun AVL.toTextHeights(): String {
    when (this) {
        is Empty -> return "Empty"
        is Node -> {
            val lText = left.toTextHeights()
            val rText = right.toTextHeights()
            val vText = keys.height
            return "Node($lText, $vText, $rText )"
        }
        else -> throw Exception("Unknown class")
    }
}
private fun AVL.toTextDiff(): String {
    when (this) {
        is Empty -> return "Empty"
        is Node -> {
            val lText = left.toTextDiff()
            val rText = right.toTextDiff()
            val vText = keys.diff
            return "Node($lText, $vText, $rText )"
        }
        else -> throw Exception("Unknown class")
    }
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

private fun AVL.find2 (elem : Int): Boolean {
    return this.fold<Boolean>(false, { keys, left, right ->
        (keys.value == elem) ||
                left || right
    })
}

private fun getHeight (tree : AVL): Int {
    when (tree){
        is Empty -> return -1
        is Node ->  return tree.keys.height
        else -> throw Exception("getHeight function is broken")
    }
}
private  fun getHeight2 (tree: AVL, tree2 : AVL) : Int{
    return Math.max(getHeight(tree),getHeight(tree2)) + 1
}
private  fun getDiff (leftTree: AVL, rightTree : AVL) : Int{
    return getHeight(rightTree) - getHeight(leftTree)
}
fun addToAVL(tree :AVL,elem : Int) :AVL {
    when (tree){
        is Empty ->  return Node (Keys(elem), Empty(), Empty())
        is Node  -> {
            if (tree.keys.value > elem){
                val left = addToAVL(tree.left, elem)
                tree.keys.height = getHeight2(left, tree.right)
                tree.keys.diff   = getDiff(left, tree.right)
                val newTree = Node(tree.keys, left, tree.right)
                return  balance(newTree)
            }
            else {
                val right = addToAVL(tree.right, elem)
                tree.keys.height = getHeight2( tree.left, right)
                tree.keys.diff   = getDiff(tree.left, right)
                val newTree = Node(tree.keys, tree.left, right)
                return  balance(newTree)
            }
        }
        else -> throw  Exception("Unknown class")
    }
}
fun unWrap (tree: AVL) : Node{
    when (tree){
        is Empty -> throw Exception("Can't unWrap")
        is Node  -> return tree
        else     -> throw Exception("Unknown class")
    }
}
fun balance(tree : AVL): AVL{

    fun rightRotation(tree : AVL):AVL{
        when (tree){
            is Empty -> throw Exception("rightRotation is broken")
            is Node ->{
                val  a = tree.keys
                val left = unWrap(tree.left)
                val r    = tree.right
                val b = left.keys
                val l = left.left
                val c = left.right
                if (b.diff <= 0) {
                    //small rotation
                    a.height = getHeight2(c, r)
                    a.diff   = getDiff(c, r)
                    b.height = Math.max(getHeight(l), a.height) + 1
                    b.diff   = a.height - getHeight(l)
                    return Node(b, l , Node(a, c, r))
                }
                // big rotation
                val cNode = unWrap(c)
                val central = cNode.keys
                val m = cNode.left
                val n = cNode.right
                a.height = getHeight2(n, r)
                a.diff   = getDiff(n, r)
                b.height = getHeight2(l, m)
                b.diff   = getDiff(l, m)
                central.height = Math.max(b.height, a.height) + 1
                central.diff   = a.height - b.height
                return Node(central,Node(b, l, m), Node(a, n, r))

            }
            else -> throw Exception("Unknown class")
        }
    }
    fun leftRotation(tree : AVL):AVL{
        when (tree){
            is Empty -> throw Exception("leftRotation is broken")
            is Node ->{
                val  a = tree.keys
                val right = unWrap(tree.right)
                val l    = tree.left
                val b = right.keys
                val c = right.left
                val r = right.right
                if (b.diff >= 0) {
                    //small rotation
                    a.height = getHeight2(l, c)
                    a.diff   = getDiff(l, c)
                    b.height = Math.max( a.height, getHeight(r)) + 1
                    b.diff   = getHeight(r) - a.height
                    return Node(b, Node(a, l, c), r)
                }
                // big rotation
                val cNode = unWrap(c)
                val central = cNode.keys
                val m = cNode.left
                val n = cNode.right
                a.height = getHeight2(l, m)
                a.diff   = getDiff(l, m)
                b.height = getHeight2(n, r)
                b.diff   = getDiff(n, r)
                central.height = Math.max(b.height, a.height) + 1
                central.diff   = b.height - a.height
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
fun getKeys (tree:AVL, left:AVL, right: AVL) : Keys{
    var key = (unWrap(tree)).keys
    key.height = getHeight2(left, right)
    key.diff   = getDiff(left, right)
    return (key)
}
fun makeNewTree (tree:AVL, left:AVL, right: AVL) : AVL{
    var key =  getKeys(tree, left, right)
    val newTree = Node(key, left, right )
    return (balance(newTree))
}
fun makeNewTree (tree:AVL, left:AVL, right: AVL, value : Int) : AVL{
    var key =  getKeys(tree, left, right)
    key.value = value
    val newTree = Node(key, left, right )
    return (balance(newTree))
}
fun removeInAVL(tree :AVL,elem : Int) :AVL {
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
                    return Pair(makeNewTree(tree, tree.left, pair.first), pair.second)
                }
                else{
                    pair = findSubstitute(tree.left, false)
                    return Pair(makeNewTree(tree, pair.first, tree.right), pair.second)
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
                return  makeNewTree(tree, left, tree.right)
            }
            if (tree.keys.value < elem) {
                val right = removeInAVL(tree.right, elem)
                return  makeNewTree(tree, tree.left, right)
            }
            // case when tree.value == elem
            if (tree.keys.value == 0){ // isLeaf
                return Empty()
            }
            val pair : Pair<AVL,Int>
            if (tree.keys.diff < 0){
                pair = findSubstitute (tree.left, true)
                return makeNewTree(tree, pair.first, tree.right, pair.second)
            }
            else {
                pair = findSubstitute (tree.right, false)
                return makeNewTree(tree, tree.left, pair.first, pair.second)
            }
        }
        else -> throw  Exception("Unknown class")
    }
}


fun main(args: Array<String>) {
    val tree = generateTree(1, 4)

    var t = addToAVL(tree, -1)

    t = addToAVL(t, -2)

    t = addToAVL(t, 5)

    t = addToAVL(t, 8)

    t = addToAVL(t, 7)

    t = addToAVL(t, 6)

    t = removeInAVL(t, 2)
    println( t.toText())

    println( t.toTextHeights())

}

