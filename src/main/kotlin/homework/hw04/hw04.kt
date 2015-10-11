package homework.hw04

import java.util.*
import kotlin.test.assertEquals

interface Set{
    fun insertion(value:Int)
    fun deletion(value:Int)
    fun search(value:Int):Boolean
    fun union(set:Set):Set
    fun intersection(set:Set):Set
    fun toList():List<Int>
}

class AVLTree : Set{

    private class Node(val value: Int, var left_param: Node?, var right_param: Node?){
        public var left: Node? = left_param
            get() = $left
            set(newLeft: Node?) {
                $left = newLeft
                height_f = calcHeight()
            }

        public var right: Node? = right_param
            get() = $right
            set(newRight: Node?) {
                $right = newRight
                height_f = calcHeight()
            }
        private fun calcHeight() : Int =
                1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
        private var height_f : Int = calcHeight()
        fun height(): Int = height_f
    }

    private fun getBalanceFactor(node : Node?):Int{
        return (node?.right?.height() ?:0) - (node?.left?.height() ?:0)
    }

    private fun turnRight(node : Node?):Node?{
        var tmp     = node?.left
        node?.left  = tmp?.right
        tmp?.right  = node
        return tmp
    }

    private fun turnLeft(node : Node?):Node?{
        var tmp     = node?.right
        node?.right = tmp?.left
        tmp?.left   = node
        return tmp
    }

    private fun balance(node : Node?):Node?{
        when {
            getBalanceFactor(node)  == 2 -> {
                if (getBalanceFactor(node?.right) < 0) node?.right = turnRight(node?.right)
                return turnLeft(node)
            }

            getBalanceFactor(node) == -2 -> {
                if (getBalanceFactor(node?.left) > 0) node?.left = turnLeft(node?.left)
                return turnRight(node)
            }
        }
        return node
    }

    fun insert(key:Int, node : Node?):Node?{
        when {
            node == null     -> return Node(key, null, null)
            key < node.value -> node.left = insert(key , node.left)
            else             -> node.right = insert(key , node.right)
        }
        return balance(node)
    }

    private fun findMin(node : Node?):Node?{
        if (node?.left == null)
            return node
        else
            return findMin(node?.left)
    }

    private fun removeMin(node : Node?):Node?{
        if (node?.left==null) return node?.right
        else{
            node?.left = removeMin(node?.left)
            return balance(node)
        }
    }

    private fun remove(key:Int, node : Node?):Node?{
        when{
            node == null     -> return null
            key < node.value -> {
                node.left = remove(key, node.left)
            }
            key > node.value -> {
                node.right = remove(key, node.right)
            }
            else             -> {
                var tmpLeft  = node.left
                var tmpRight = node.right ?: return tmpLeft
                var nodeMin   = findMin(tmpRight)
                nodeMin?.right = removeMin(tmpRight)
                nodeMin?.left  = tmpLeft
                return balance(nodeMin)
            }
        }
        return balance(node)
    }

    private fun find(key:Int,node: Node?):Boolean{
        when{
            node == null      -> return false
            key < node.value  -> return find(key, node.left)
            key > node.value  -> return find(key, node.right)
            else              -> return true
        }
    }

    private fun Node?.treeToText():String{
        if (this == null) return "null"
        var leftStr: String
        var rightStr: String
        if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
        if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
        return leftStr + "," + value.toString() + "," + rightStr
    }

    //AVLTree overriding

    private var elem:Node? = null
    //overriding AVLTree methods
    override fun insertion(value:Int){
        elem = insert(value,elem)
    }

    override fun deletion(value:Int){
        elem = remove(value,elem)
    }

    override fun search(value:Int):Boolean{
        return find(value, elem)
    }

    override fun toList(): List<Int> {
        fun Node.toList() : List<Int> {
            val leftList  = left?.toList() ?: listOf()
            val rightList = right?.toList() ?: listOf()
            val nodeList  = listOf(value)
            return leftList + rightList + nodeList
            }
        return elem?.toList() ?: listOf()
    }

    override fun union(set:Set):AVLTree{
        var result = this
        set.toList().forEach { if (!search(it)) result.insertion(it) }
        return result
    }

    override fun intersection(set:Set):AVLTree{
        var result = AVLTree()
        set.toList().forEach { if (search(it)) result.insertion(it)}
        return result
    }
}

//HashTable overriding

public class HashTable(val size:Int) : Set{
    //Creating HashTable
    public val HashTable = Array(size, { LinkedList<Int>() })

    override fun toList(): List<Int> {
        var result = listOf<Int>()
        HashTable.forEach { result+=it }
        return result
    }

    override fun deletion(value: Int) {
        HashTable.forEach { it.removeFirstOccurrence(value) }
    }

    override fun insertion(value: Int) {
        HashTable[value % size].add(value)
    }

    override fun search(value: Int): Boolean {
        if (HashTable[value % size].indexOf(value)!= -1) return true
        return false
    }

    override fun union(set: Set): Set {
        val result = set
        toList().forEach { if (!set.search(it)) result.insertion(it) }
        return result
    }

    override fun intersection(set: Set): Set {
        var result = HashTable(size)
        toList().forEach { if (set.search(it)) result.insertion(it) }
        return result
    }
}
