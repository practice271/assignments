package hw03

import java.util.LinkedList

class AVLtree<A>() {

    private var root: Node<A>? = null

    public fun find(key : Int) : A?{
        return root?.find(key)
    }

    public fun insert(newKey: Int, newValue : A) {
        root = root?.insert(newKey, newValue) ?: Node(newKey, newValue)
    }

    public fun remove(key : Int){
        if (root != null) root = root!!.remove(key)
    }

    public fun toList() : LinkedList<A> {
        return root?.toList() ?: linkedListOf()
    }

    public fun toText() : String {
        return root?.toText() ?: "null"
    }
}

internal class Node<A>(public var key : Int, public var value : A, private var height : Int = 1,
              private var left: Node<A>? = null, private var right: Node<A>? = null) {

    public fun toList(): LinkedList<A> {
        fun f(node: Node<A>?, list: LinkedList<A>): LinkedList<A> {
            if (node == null) return list
            else {
                val temp = f(node.left, list)
                temp.add(node.value)
                return f(node.right, temp)
            }
        }
        return f(this, linkedListOf<A>())
    }

    public fun toText(): String {
        fun f(node: Node<A>): String {
            val leftToStr: String
            val rightToStr: String
            if (node.left != null) leftToStr = "{" + f(node.left as Node<A>) + "}" else leftToStr = "null"
            if (node.right != null) rightToStr = "{" + f(node.right as Node<A>) + "}" else rightToStr = "null"
            return leftToStr + ",${node.value.toString()}," + rightToStr
        }
        return f(this)
    }

    private fun balanceFactor(): Int {
        return (right?.height ?: 0) - (left?.height ?: 0)
    }

    private fun overHeigh() {
        val hleft = this.left?.height ?: 0
        val hright = this.right?.height ?: 0
        this.height = if (hleft > hright) hleft + 1 else hright + 1
    }

    private fun rightRotate(): Node<A> {
        var temp1 = this
        var temp2 = temp1.left
        temp1.left = temp2!!.right      //it`s a "private" function so it`s guaranteed that it won`t be called
        temp2.right = temp1             //if this.left.right is null
        temp1.overHeigh()
        temp2.overHeigh()
        return temp2
    }

    private fun leftRotate(): Node<A> {
        val temp1 = this
        val temp2 = temp1.right
        temp1.right = temp2!!.left      //it`s a "private" function so it`s guaranteed that it won`t be called
        temp2.left = temp1              //if this.right.left is null
        temp1.overHeigh()
        temp2.overHeigh()
        return temp2
    }

    private fun balance(): Node<A> {
        overHeigh()
        val bf = balanceFactor()
        if (bf == 2) {
            if ((right!!.balanceFactor()) < 0) right = right!!.rightRotate() //it`s guaranteed by 'balanceFactor'
            return leftRotate()                                              //implementation that 'right' is not null
        } else if (bf == -2) {                                               //if 'balanceFactor' returns 2
            if ((left!!.balanceFactor()) > 0) left = left!!.leftRotate()     //and 'left' is not null
            return rightRotate()                                             //if 'balanceFactor' returns -2
        } else return this
    }

    public fun insert(newKey: Int, newValue: A): Node<A> {
        fun f(newKey: Int, newValue: A, node: Node<A>?): Node<A> {
            when {
                node == null -> return Node(newKey, newValue)
                newKey < node.key -> node.left = f(newKey, newValue, node.left)
                newKey > node.key -> node.right = f(newKey, newValue, node.right)
                else -> node.value = newValue
            }
            return node!!.balance()      //node is not null because in this case function
        }                                //would have already returned Node(newKey, newValue)
        return f(newKey, newValue, this)
    }

    public fun find(searchedKey: Int): A? {
        when {
            searchedKey == key -> return value
            searchedKey < key -> return left?.find(searchedKey)
            else -> return right?.find(searchedKey)
        }
    }

    private fun findMin(): Node<A> {
        return left?.findMin() ?: this
    }

    private fun findMax(): Node<A> {
        return right?.findMax() ?: this
    }

    public fun remove(removingKey: Int): Node<A>? {
        var ans: Node<A>
        when {
            this.key == removingKey ->
                when {
                    this.left != null -> {
                        val temp = this.left!!.findMax()
                        ans = Node(temp.key, temp.value, this.height, this.left!!.remove(temp.key), this.right)
                    }
                    this.right != null -> {
                        val temp = this.right!!.findMin()
                        ans = Node(temp.key, temp.value, this.height, this.left, this.right!!.remove(temp.key))
                    }
                    else -> return null
                }
            removingKey < this.key -> {
                if (this.left == null) ans = this
                else ans = Node(this.key, this.value, this.height, this.left!!.remove(removingKey), this.right)
            }
            else -> {
                if (this.right == null) ans = this
                else ans = Node(this.key, this.value, this.height, this.left, this.right!!.remove(removingKey))
            }
        }
        return ans.balance()
    }
}

fun main(args: Array<String>) {
    val tree = AVLtree<Int>()
    tree.insert(5, 500); tree.insert(9, 900); tree.insert(6, 600); tree.insert(4, 400);
    print(tree.toText())
}