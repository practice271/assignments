package hw08

/**
 * Created by Antropov Igor on 07.11.2015.
 */

import java.util.*

public interface interfaceClass<A : Comparable<A>> {
    fun insertion(k: A)
    fun deletion(k: A)
    fun search(k: A): Boolean
    fun union(value: interfaceClass<A>): interfaceClass<A>
    fun intersection(value: interfaceClass<A>): interfaceClass<A>
    fun toList(): List<A>
}

public class AVLTree<A : Comparable<A>>() : interfaceClass<A>, Iterable<A> {

    class Node<A : Comparable<A>>(var key: A, left_param: Node<A>?, right_param: Node<A>?) : Iterable<A> {
        public var left: Node<A>? = left_param
            get() = field
            set(newLeft: Node<A>?) {
                field = newLeft
                height_f = calcHeight()
            }

        public var right: Node<A>? = right_param
            get() = field
            set(newRight: Node<A>?) {
                field = newRight
                height_f = calcHeight()
            }

        override fun iterator(): Iterator<A> = LCRNodeIterator(this)
        private fun calcHeight(): Int =
                1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)

        private var height_f: Int = calcHeight()
        fun height(): Int = height_f
    }


    private class EmptyIterator<A>() : Iterator<A> {
        override fun hasNext(): Boolean = false
        override fun next(): A {
            throw NoSuchElementException()
        }
    }

    private abstract class NodeIterator<A : Comparable<A>>(
            protected val node: Node<A>
    ) : Iterator<A> {
        protected val lIterator: Iterator<A> =
                node.left?.iterator() ?: EmptyIterator()
        protected val rIterator: Iterator<A> =
                node.right?.iterator() ?: EmptyIterator()
        protected var observed: Boolean = false
        protected var lHasNext: Boolean = true
            get() =
            if (field) {
                field = lIterator.hasNext(); field
            } else false
        protected var rHasNext: Boolean = true
            get() =
            if (field) {
                field = rIterator.hasNext(); field
            } else false

        override fun hasNext(): Boolean {
            if (!observed) {
                return true
            }
            if (lHasNext ) {
                return true
            }
            if (rHasNext ) {
                return true
            }
            return false
        }
    }

    private class LCRNodeIterator<A : Comparable<A>>(
            node: Node<A>
    ) : NodeIterator<A>(node) {
        override fun next(): A {
            if (lHasNext ) {
                return lIterator.next()
            }
            if (!observed) {
                observed = true; return node.key
            }
            if (rHasNext ) {
                return rIterator.next()
            }
            throw NoSuchElementException()
        }
    }

    override fun iterator(): Iterator<A> =
            root?.iterator() ?: EmptyIterator()

    var root: Node<A>? = null

    fun bfactor(p: Node<A>?): Int {
        if (p != null) return ((p.right?.height() ?: 0) - (p.left?.height() ?: 0))
        else return 0
    }

    fun rotateRight(p: Node<A>?): Node<A>? {
        val tmp: Node<A>? = p?.left
        p?.left = tmp?.right
        tmp?.right = p
        return tmp
    }

    fun rotateLeft(p: Node<A>?): Node<A>? {
        val tmp: Node<A>? = p?.right
        p?.right = tmp?.left
        tmp?.left = p
        return tmp
    }

    fun balance(p: Node<A>?): Node<A>? {
        if (bfactor(p) == 2) {
            if (bfactor(p?.right) < 0 ) p?.right = rotateRight(p?.right)
            return rotateLeft(p)
        }
        if (bfactor(p) == -2 ) {
            if (bfactor(p?.left) > 0) p?.left = rotateLeft(p?.left)
            return rotateRight(p)
        }
        return (p)
    }

    fun insert(p: Node<A>?, k: A): Node<A>? {
        if ( p == null ) return Node(k, null, null)
        when {
            k < p.key -> p.left = insert(p.left, k)
            k > p.key -> p.right = insert(p.right, k)
            else -> 0
        }
        return balance(p)
    }

    fun findMin(p: Node<A>?): Node<A>? {
        if (p?.left != null) return findMin(p?.left)
        else return p
    }

    fun find(p: Node<A>?, x: A): Boolean =
            if (p is Node<A>)
                when {
                    x < p.key -> find(p.left, x)
                    x > p.key -> find(p.right, x)
                    else -> true
                }
            else
                false

    fun removeMin(p: Node<A>?): Node<A>? {
        if (p?.left == null)
            return p?.right
        else {
            p?.left = removeMin(p?.left)
            return balance(p)
        }
    }

    fun remove(p: Node<A>?, k: A): Node<A>? {
        if ( p == null ) return null
        when {
            k < p.key -> p.left = remove(p.left, k)
            k > p.key -> p.right = remove(p.right, k)
            else -> {
                var q: Node<A>? = p.left
                var r: Node<A>? = p.right ?: return q
                var min: Node<A>? = findMin(r)
                min?.right = removeMin(r)
                min?.left = q
                return balance(min)
            }
        }
        return balance(p)
    }

    public fun Node<A>?.treeToText(): String {
        if (this == null) return "null"
        var leftStr: String
        var rightStr: String
        if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
        if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
        return leftStr + "," + this.key.toString() + "," + rightStr
    }

    override fun insertion(k: A) {
        root = insert(root, k)
    }

    override fun deletion(k: A) {
        root = remove(root, k)
    }

    override fun search(k: A): Boolean {
        return find(root, k)
    }

    override fun union(value: interfaceClass<A>): interfaceClass<A> {
        var res = AVLTree<A>()
        for (i in this.toList()) res.insertion(i)
        for (i in value.toList()) res.insertion(i)
        return res
    }

    override fun intersection(value: interfaceClass<A>): interfaceClass<A> {
        val res = AVLTree<A>()
        for (i in this.toList()) {
            for (j in value.toList())
                if (i == j) res.insertion(i)
        }
        return res
    }

    override fun toList(): List<A> {
        fun Node<A>.toList(): List<A> {
            val tmp1 = left?.toList() ?: listOf()
            val tmp2 = right?.toList() ?: listOf()
            val tmp3 = this.key
            return tmp1 + tmp2 + tmp3
        }
        return root?.toList() ?: listOf()
    }

    override fun toString(): String {
        fun Node<A>?.treeToText(): String {
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

public class HashTable<A : Comparable<A>> : interfaceClass<A> {
    private val SIZE_OF_HASH_TABLE = 100
    private var hashTable = Array<Node<A>?>(SIZE_OF_HASH_TABLE, { null })

    private class Node<A>(key_val: A, next_param: Node<A>?, prev_param: Node<A>?) : Iterable<Node<A>> {

        private var hash_val = key_val?.hashCode()

        public var key: A = key_val
            get() = field
            set(newKey: A) {
                field = newKey
                hash_val = newKey?.hashCode()
            }

        public var next: Node<A>? = next_param
            get() = field
            set(newNext: Node<A>?) {
                field = newNext
            }
        public var prev: Node<A>? = prev_param
            get() = field
            set(newPrev: Node<A>?) {
                field = newPrev
            }

        public fun hash(): Int? = hash_val

        override public fun iterator(): Iterator<Node<A>> {
            return MyIterator<A>(this)
        }
    }

    private class MyIterator<A>(var myNode: Node<A>?) : Iterator<Node<A>> {

        override public fun hasNext(): Boolean {
            if (myNode != null) return true
            else return false
        }

        override public fun next(): Node<A> {
            val tempNode = myNode
            myNode = myNode?.next
            if (tempNode != null) return tempNode
            else throw NoSuchElementException()
        }
    }

    override fun toString(): String {
        var res = ""
        for (elemOfArray in hashTable) {
            if (elemOfArray != null) {
                for (elemOfList in elemOfArray) res += elemOfList.key.toString() + " "
            }
        }
        return res
    }

    override fun toList(): List<A> {
        var result = listOf<A>()
        for (elemOfArray in hashTable) {
            if (elemOfArray != null) {
                for (elemOfList in elemOfArray) {
                    result += elemOfList.key
                }
            }
        }
        return result
    }

    override fun insertion(k: A) {
        val hashValue = k.hashCode()
        var contains = false
        var flag = false
        for (elemOfArray in hashTable) {
            if (elemOfArray != null)
                if (elemOfArray.hash() == hashValue) {
                    for (elemOfList in elemOfArray) {
                        if (elemOfList.key == k) {
                            flag = true
                            contains = true
                        }
                    }
                    if (!contains) {
                        var tempNode = Node(k, elemOfArray.next, elemOfArray)
                        elemOfArray.next = tempNode
                        flag = true
                        break
                    }
                }
        }
        if (!flag) {
            for (i in 0..99) {
                if (hashTable[i] == null) {
                    hashTable[i] = Node(k, null, null)
                    break
                }
            }
        }
    }

    override fun deletion(k: A) {
        var inc = 0
        val hashValue = k.hashCode()
        for (elemOfArray in hashTable) {
            if (elemOfArray != null)
                if (elemOfArray.hash() == hashValue) {
                    for (elemOfList in elemOfArray) {
                        inc++
                        if (elemOfList.key == k)
                            when {
                                elemOfList.next == null || elemOfList.prev == null -> {
                                    hashTable[inc] = null
                                }
                                elemOfList.next == null -> {
                                    elemOfList.prev?.next = null
                                    elemOfList.prev = null
                                }
                                elemOfList.prev == null -> {
                                    elemOfList.next?.prev = null
                                    elemOfList.next = null
                                }
                                else -> {
                                    elemOfList.next?.prev = elemOfList.prev
                                    elemOfList.prev?.next = elemOfList.next
                                    elemOfList.prev = null
                                    elemOfList.next = null
                                }
                            }
                    }
                }
        }
    }

    override fun search(k: A): Boolean {
        val hashValue = k.hashCode()
        for (elemOfArray in hashTable) {
            if (elemOfArray != null)
                if (elemOfArray.hash() == hashValue) {
                    for (elemOfList in elemOfArray) {
                        if (elemOfList.key == k) {
                            return true
                        }
                    }
                }
        }
        return false
    }

    override fun union(value: interfaceClass<A>): interfaceClass<A> {
        val res = HashTable<A>()
        for (elemOfArray in hashTable) {
            if (elemOfArray != null)
                for (elemOfList in elemOfArray) {
                    res.insertion(elemOfList.key)
                }
        }
        for (i in value.toList()) res.insertion(i)
        return res
    }

    override fun intersection(value: interfaceClass<A>): interfaceClass<A> {
        var temp: A? = null
        val res = HashTable<A>()
        for (elemOfOtherClass in value.toList()) {
            for (elemOfArray in hashTable) {
                if (elemOfArray != null)
                    for (elemOfList in elemOfArray) {
                        temp = elemOfList.key
                        if (temp == elemOfOtherClass)
                            res.insertion(temp)
                    }
            }
        }
        return res
    }
}