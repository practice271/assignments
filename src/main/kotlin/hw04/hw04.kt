package hw04

import java.util.*

/**
 * Created by Antropov Igor 04.10.2015
 */


interface  interfaceClass{
    fun insertion(k : Int)
    fun deletion(k: Int)
    fun search(k: Int): Boolean
    fun union(value: interfaceClass): interfaceClass
    fun intersection(value: interfaceClass): interfaceClass
    fun toList(): List<Int>
}

class AVLTree: interfaceClass{

    class Node (val key: Int, var left_param: Node?, var right_param: Node? ) {
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

    var root: Node? = null

    fun bfactor (p: Node?) : Int {
        if (p != null) return ((p.right?.height() ?: 0) - (p.left?.height() ?: 0))
        else return 0
    }

    fun rotateRight(p: Node?) : Node? {
        val tmp: Node? = p?.left
        p?.left = tmp?.right
        tmp?.right = p
        return tmp
    }

    fun rotateLeft (p: Node?) : Node? {
        val tmp: Node? = p?.right
        p?.right = tmp?.left
        tmp?.left = p
        return  tmp
    }

    fun balance (p: Node?) : Node? {
        if (bfactor(p) == 2) {
            if (bfactor(p?.right) < 0 ) p?.right = rotateRight(p?.right)
            return rotateLeft(p)
        }
        if (bfactor(p) == -2 ){
            if (bfactor(p?.left) > 0)   p?.left = rotateLeft(p?.left)
            return rotateRight(p)
        }
        return (p)
    }

    fun insert(p: Node?, k: Int) : Node?
    {
        if( p == null ) return Node(k, null, null)
        when{
            k < p.key -> p.left = insert(p.left,k)
            k > p.key -> p.right = insert(p.right,k)
            else -> 0
        }
        return balance(p)
    }

    fun findMin(p: Node?): Node? {
        if (p?.left != null) return findMin(p?.left)
        else return p
    }

    fun find(p: Node?, x: Int): Boolean =
            if (p is Node)
                when {
                    x < p.key -> find(p.left, x)
                    x > p.key -> find(p.right, x)
                    else -> true
                }
            else
                false

    fun removeMin(p: Node?): Node?
    {
        if (p?.left == null)
            return p?.right
        else {
            p?.left = removeMin(p?.left)
            return balance(p)
        }
    }

    fun remove(p: Node?, k: Int): Node?
    {
        if( p == null ) return null
        when{
            k < p.key -> p.left = remove(p.left, k)
            k > p.key -> p.right = remove(p.right, k)
            else -> {
                var q : Node? = p.left
                var r : Node? = p.right ?: return q
                var min: Node? = findMin(r)
                min?.right = removeMin(r)
                min?.left = q
                return balance(min)
            }
        }
        return balance(p)
    }

    public fun Node?.treeToText(): String{
        if (this == null) return "null"
        var leftStr: String
        var rightStr: String
        if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
        if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
        return leftStr + "," + this.key.toString() + "," + rightStr
    }

    override fun insertion(k : Int){
        root = insert(root, k)
    }
    override fun deletion(k: Int){
        root = remove(root, k)
    }
    override fun search(k: Int): Boolean{
        return find(root,k)
    }
    override fun union(value: interfaceClass): interfaceClass{
        var res = AVLTree()
        for (i in this.toList()) res.insertion(i)
        for (i in value.toList()) res.insertion(i)
        return res
    }
    override fun intersection(value: interfaceClass): interfaceClass{
        val res = AVLTree()
        for (i in this.toList()){
            for (j in value.toList())
                if (i == j) res.insertion(i)
        }
        return res
    }

    override fun toList(): List<Int>{
            fun Node.toList(): List<Int>{
                val tmp1 = left?.toList() ?: listOf()
                val tmp2 = right?.toList() ?: listOf()
                val tmp3 = this.key
                return tmp1 + tmp2 + tmp3
            }
        return root?.toList() ?: listOf()
    }

    override fun toString(): String {
        fun Node?.treeToText(): String{
            if (this == null) return "null"
            var leftStr: String
            var rightStr: String
            if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
            if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
            return leftStr + "," + this.key.toString() + "," + rightStr
        }
        return root?.treeToText() ?: "null"
    }
}

class HashTable: interfaceClass {

    var amap = HashMap<Int, Int>()

    override fun toString(): String{
        var res = ""
        amap.forEach { i, j -> res += j.toString() + " " }
        return res
    }

    override fun toList() : List<Int> {
        var result = listOf<Int>()
        amap.forEach { i, j -> result += j }
        return result
    }
    override fun insertion(k : Int){
        amap.put(k.hashCode(), k)
    }
    override fun deletion(k: Int){
        amap.remove(k.hashCode())
    }
    override fun search(k: Int): Boolean{return amap.containsKey(k.hashCode())}
    override fun union(value: interfaceClass): interfaceClass{
        val res = HashTable()
        amap.forEach { i, j -> res.insertion(j) }
        for (i in value.toList()) res.insertion(i)
        return res
    }
    override fun intersection(value: interfaceClass): interfaceClass{
        val res = HashTable()
        for (i in value.toList()){
            amap.forEach { key, j -> if (j == i) res.insertion(j)  }
        }
        return res
    }
    fun print (){
        amap.forEach { i, j -> print(j.toString() + " ") }
    }
}


fun main(args: Array<String>) {
    val tree1 = AVLTree()
    val a1 = arrayOf(5, 4, 6)
    for (x in a1) {
        tree1.insertion(x)
    }

    val tree2 = AVLTree()
    val a2 = arrayOf(2, 3, 7)
    for (x in a2) {
        tree2.insertion(x)
    }

    val b1 = arrayOf(5, 44, 62, 24, 53, 7)
    val tee1 = HashTable()
    for (x in b1) {
        tee1.insertion(x)
    }
    val b2 = arrayOf(5, 43, 61, 23, 53, 8)
    val tee2 = HashTable()
    for (x in b2) {
        tee2.insertion(x)
    }
    val tee3 = HashTable()
    val tree3 = AVLTree()

    println(tee3.intersection(tree1).toList().toString())
    println(tree1.intersection(tee3).toList().toString())

    println(tree1.union(tee3).toList())
    println(tee3.union(tree1).toList())
}
