package hw08

import java.util.NoSuchElementException

public class AVLTree(): ISet() {

    private var root: Node? = null

    private fun ins(k: Int, nd: Node?): Node {
        nd ?: return Node(k, null, null)
        when {
            k == nd.key -> return nd

            k > nd.key -> {
                nd.right = ins(k, nd.right)
                nd.rebalance()
                return nd
            }

            else -> {
                nd.left = ins(k, nd.left)
                nd.rebalance()
                return nd
            }
        }
    }

    override public fun insert(k: Int) { root = ins(k, root) }


    private fun del(k: Int, nd: Node?): Node? {
        fun Node.minnode(): Node = left?.minnode() ?: this
        fun Node.delmin(): Node? {
            left ?: return right
            left = left?.delmin()
            rebalance()
            return this
        }

        nd ?: return null
        when {
            k < nd.key -> nd.left = del(k, nd.left)

            k > nd.key -> nd.right = del(k, nd.right)
            else -> {
                nd.right ?: return nd.left
                val min: Node? = nd.right?.minnode()
                min?.right = nd.right?.delmin()
                min?.left = nd.left
                min?.rebalance()
                return min
            }
        }
        nd.rebalance()
        return nd
    }

    override public fun delete(k: Int) { root = del(k, root) }


    override public fun intersection(s: ISet): ISet {
        val t = AVLTree()
        if (isEmpty() || s.isEmpty()) return t
        toList().forEach {
            if (s.contains(it)) t.insert(it)
        }
        return t
    }

    override public fun union(s: ISet): ISet {
        val t = this
        if (t.isEmpty()) return s
        if (s.isEmpty()) return t
        s.toList().forEach { if (!t.contains(it)) t.insert(it) }
        return t
    }

    override public fun iterator(): Iterator<Int> = root?.iterator() ?: EmptyIterator()

    override public fun isEmpty(): Boolean = root == null

    override public fun toList(): List<Int> {
        fun Node.toList(): List<Int> {
            val lText = left?.toList() ?: listOf()
            val rText = right?.toList() ?: listOf()
            val vText = listOf(key)
            return vText + lText + rText
        }
        return root?.toList() ?: listOf()
    }

    override fun toString(): String =
            StringBuilder().let { sb ->
                this.forEach { sb.append("$it ") }
                sb.append("\n")
            }.toString()

    public fun fromList(ls: List<Int>) {
        ls.forEach { this.insert(it) }
    }


    //------------------------------NODE CLASS-----------------------------------
    private class Node(public var key: Int,
                       left_param: Node?,
                       right_param: Node?
    ): Iterable<Int> {

        public var left: Node? = left_param
            set(newLeft: Node?) {
                field = newLeft
                height_f = calcHeight()
            }
        public var right: Node? = right_param
            set(newR: Node?) {
                field = newR
                height_f = calcHeight()
            }

        private var height_f: Int = calcHeight()
        private fun calcHeight(): Int = 1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
        private fun height(): Int = height_f
        private fun balance(): Int = (right?.height() ?: 0) - (left?.height() ?: 0)

        private fun ltrotate() {
            if (right != null) {
                left = Node(key, left, right?.left)
                key = right?.key ?: 0
                right = right?.right
            }
        }

        private fun rgrotate() {
            if (left != null) {
                right = Node(key, left?.right, right)
                key = left?.key ?: 0
                left = left?.left
            }
        }

        private fun rotateRL() {
            right?.rgrotate()
            ltrotate()
        }

        private fun rotateLR() {
            left?.ltrotate()
            rgrotate()
        }


        public fun rebalance() {
            when (balance()) {
                -2 -> {
                    if (left?.balance() ?: 0 <= 0)
                        rgrotate()
                    else
                        rotateLR()
                }
                2 -> {
                    if (right?.balance() ?: 0 > 0)
                        ltrotate()
                    else
                        rotateRL()
                }
            }
        }

        override fun iterator(): Iterator<Int> = CLRNodeIterator(this)

    }

    //-------------------------------END OF NODE CLASS-------------------------

    //-------------------------------ITERATORS---------------------------------
    private class EmptyIterator(): Iterator<Int> {
        override fun hasNext(): Boolean = false
        override fun next(): Int { throw NoSuchElementException() }
    }

    private abstract class NodeIterator(
            protected val node: Node
    ): Iterator<Int> {
        protected val lIterator: Iterator<Int> =
                node.left?.iterator() ?: EmptyIterator()
        protected val rIterator: Iterator<Int> =
                node.right?.iterator() ?: EmptyIterator()
        protected var observed: Boolean = false
        protected var lHasNext: Boolean = true
            get() = if (field) {
                field = lIterator.hasNext()
                field
            } else false
        protected var rHasNext: Boolean = true
            get() = if (field) {
                field = rIterator.hasNext()
                field
            } else false

        override fun hasNext(): Boolean {
            if (!observed) return true
            if (lHasNext ) return true
            if (rHasNext ) return true
            return false
        }
    }

    private class LCRNodeIterator(
            node: Node
    ): NodeIterator(node) {
        override fun next(): Int {
            if (lHasNext) return lIterator.next()
            if (!observed) {
                observed = true
                return node.key
            }
            if (rHasNext) return rIterator.next()
            throw NoSuchElementException()
        }
    }

    private class CLRNodeIterator(
            node: Node
    ): NodeIterator(node) {
        override fun next(): Int {
            if (!observed) {
                observed = true
                return node.key
            }
            if (lHasNext) return lIterator.next()
            if (rHasNext) return rIterator.next()
            throw NoSuchElementException()
        }
    }

    private class LRCNodeIterator(
            node: Node
    ): NodeIterator(node) {
        override fun next(): Int {
            if (lHasNext ) return lIterator.next()
            if (rHasNext ) return rIterator.next()
            if (!observed) {
                observed = true
                return node.key
            }
            throw NoSuchElementException()
        }
    }

    //------------------------------END OF ITERATORS---------------------------
}
