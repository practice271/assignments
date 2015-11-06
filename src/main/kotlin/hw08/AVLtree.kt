package hw08

import java.util.LinkedList
import java.util.NoSuchElementException

class AVLtree<Key, Value> : AbstractMap<Key, Value>() where Key : Comparable<Key> {

    private var root: Node<Key, Value>? = null

    override fun iterator() = root?.iterator() ?: EmptyIterator()

    override public fun search(key: Key): Value? {
        return root?.search(key)
    }

    override public fun insert(newKey: Key, newValue: Value) {
        root = root?.insert(newKey, newValue) ?: Node(newKey, newValue)
    }

    override public fun delete(key: Key) {
        if (root != null) root = root?.delete(key)
    }

    override public fun toList(): LinkedList<Pair<Key, Value>> {
        return root?.toList() ?: linkedListOf()
    }

    override public fun union(map: AbstractMap<Key, Value>): AVLtree<Key, Value> {
        val res = AVLtree<Key, Value>()
        val treeList = this.toList()
        val mapList = map.toList()
        for (elem in treeList) res.insert(elem.first, elem.second)
        for (elem in mapList) if (res.search(elem.first) == null) res.insert(elem.first, elem.second)
        return res
    }

    override public fun intersection(map: AbstractMap<Key, Value>): AVLtree<Key, Value> {
        val res = AVLtree<Key, Value>()
        val treeList = this.toList()
        val mapList = map.toList()
        for (elem in treeList) if (mapList.contains(elem)) res.insert(elem.first, elem.second)
        return res
    }

    public fun toText(): String {
        return root?.toText() ?: "null"
    }
}

internal class Node<Key, Value>(public var key : Key, public var value : Value,
                       left_param: Node<Key, Value>? = null, right_param: Node<Key, Value>? = null)
where Key : Comparable<Key> {

    private var height: Int = 1

    public var left: Node<Key, Value>? = left_param
        private set(newLeft) {
            field = newLeft
            height = Math.max(left?.height ?: 0, right?.height ?: 0) + 1
        }

    public var right: Node<Key, Value>? = right_param
        private set(newRight) {
            field = newRight
            height = Math.max(left?.height ?: 0, right?.height ?: 0) + 1
        }

    public fun iterator() : Iterator<Pair<Key, Value>> = NodeIterator(this)

    public fun toList(): LinkedList<Pair<Key, Value>> {
        fun f(node: Node<Key, Value>?, list: LinkedList<Pair<Key, Value>>): LinkedList<Pair<Key, Value>> {
            if (node == null) return list
            else {
                val temp = f(node.left, list)
                temp.add(Pair(node.key, node.value))
                return f(node.right, temp)
            }
        }
        return f(this, linkedListOf<Pair<Key, Value>>())
    }

    public fun toText(): String {
        fun f(node: Node<Key, Value>): String {
            val leftToStr: String
            val rightToStr: String
            if (node.left != null) leftToStr = "{" + f(node.left as Node<Key, Value>) + "}" else leftToStr = "null"
            if (node.right != null) rightToStr = "{" + f(node.right as Node<Key, Value>) + "}" else rightToStr = "null"
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
        this.height = Math.max(hleft, hright) + 1
    }

    private fun rightRotate(): Node<Key, Value> {
        var temp1 = this
        var temp2 = temp1.left as Node<Key, Value>
        temp1.left = temp2.right      //it`s a "private" function so it`s guaranteed that it won`t be called
        temp2.right = temp1             //if this.left.right is null
        temp1.overHeigh()
        temp2.overHeigh()
        return temp2
    }

    private fun leftRotate(): Node<Key, Value> {
        val temp1 = this
        val temp2 = temp1.right as Node<Key, Value>
        temp1.right = temp2.left        //it`s a "private" function so it`s guaranteed that it won`t be called
        temp2.left = temp1              //if this.right.left is null
        temp1.overHeigh()
        temp2.overHeigh()
        return temp2
    }

    private fun balance(): Node<Key, Value> {
        overHeigh()
        val bf = balanceFactor()
        if (bf == 2) {
            if (((right as Node<Key, Value>).balanceFactor()) < 0) right = right?.rightRotate()
            return leftRotate()  //it`s guaranteed by 'balanceFactor' implementation that 'right' is not null
        } else if (bf == -2) {   //if 'balanceFactor' returns 2
            if (((left as Node<Key, Value>).balanceFactor()) > 0) left = left?.leftRotate()
            return rightRotate() //'left' is not null if 'balanceFactor' returns -2
        } else return this
    }

    public fun insert(newKey: Key, newValue: Value): Node<Key, Value> {
        fun f(newKey: Key, newValue: Value, node: Node<Key, Value>?): Node<Key, Value> {
            when {
                node == null -> return Node(newKey, newValue)
                newKey < node.key -> node.left = f(newKey, newValue, node.left)
                newKey > node.key -> node.right = f(newKey, newValue, node.right)
                else -> node.value = newValue
            }
            return (node as Node<Key, Value>).balance()      //node is not null because in this case function
        }                                                   //would have already returned Node(newKey, newValue)
        return f(newKey, newValue, this)
    }

    public fun search(searchedKey: Key): Value? {
        when {
            searchedKey == key -> return value
            searchedKey < key -> return left?.search(searchedKey)
            else -> return right?.search(searchedKey)
        }
    }

    private fun findMin(): Node<Key, Value> {
        return left?.findMin() ?: this
    }

    private fun findMax(): Node<Key, Value> {
        return right?.findMax() ?: this
    }

    public fun delete(removingKey: Key): Node<Key, Value>? {
        var ans: Node<Key, Value>
        val leftSubtree = this.left
        val rightSubtree = this.right
        when {
            this.key == removingKey ->
                when {
                    leftSubtree != null -> {
                        val temp = leftSubtree.findMax()
                        ans = Node(temp.key, temp.value, leftSubtree.delete(temp.key), this.right)
                    }
                    rightSubtree != null -> {
                        val temp = rightSubtree.findMin()
                        ans = Node(temp.key, temp.value, this.left, rightSubtree.delete(temp.key))
                    }
                    else -> return null
                }
            removingKey < this.key -> {
                if (this.left == null) ans = this
                else ans = Node(this.key, this.value, leftSubtree?.delete(removingKey), this.right)
            }
            else -> {
                if (this.right == null) ans = this
                else ans = Node(this.key, this.value, this.left, rightSubtree?.delete(removingKey))
            }
        }
        return ans.balance()
    }
}

internal class EmptyIterator<A>(): Iterator<A> {
    override fun hasNext(): Boolean = false
    override fun next(): A { throw NoSuchElementException() }
}

private class NodeIterator<Key : Comparable<Key>, Value>(private val node : Node<Key, Value>)
: Iterator<Pair<Key, Value>> {
    protected val lIterator = node.left?.iterator() ?: EmptyIterator<Pair<Key, Value>>()
    protected val rIterator = node.right?.iterator() ?: EmptyIterator<Pair<Key, Value>>()
    protected var wasObserved: Boolean = false
    protected var leftHasNext: Boolean = true
        get() =
        if (field) {
            field = lIterator.hasNext()
            field
        } else false
    protected var rightHasNext: Boolean = true
        get() =
        if (field) {
            field = rIterator.hasNext()
            field
        } else false

    override fun hasNext(): Boolean = !wasObserved || leftHasNext || rightHasNext

    override fun next(): Pair<Key, Value> {
        if (leftHasNext) return lIterator.next()
        if (!wasObserved) {
            wasObserved = true;
            return Pair(node.key, node.value)
        }
        if (rightHasNext) return rIterator.next()
        throw NoSuchElementException()

    }
}