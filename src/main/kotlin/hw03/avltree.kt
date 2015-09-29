package hw03

internal class Node(internal var key: Int, internal var left: Node?, internal var right: Node?) {

    internal constructor(k: Int): this(k, null, null) { }

    private fun height(): Int = 1 + Math.max((right?.height() ?: 0), (left?.height() ?: 0))

    internal fun balance(): Int = right?.height() ?: 0 - (left?.height() ?: 0)

    internal fun ltrotate() {
        if(right != null) {
        left = Node(key, left, right!!.left)
        key = right!!.key
        right = right!!.right
        }
    }
    internal fun rgrotate() {
        if(left != null) {
            right = Node(key, left!!.right, right)
            key = left!!.key
            left = left!!.left
        }
    }

    internal fun rotateRL() {
        right?.rgrotate()
        ltrotate()
    }
    internal fun rotateLR() {
        left?.ltrotate()
        rgrotate()
    }


    internal fun rebalance() {
        when(balance()) {
            -2 -> {
                if(left?.balance()?:0 <= 0)
                    rgrotate()
                else
                    rotateLR()
            }
            2 -> {
                if(right?.balance()?:0 > 0)
                    ltrotate()
                else
                    rotateRL()
            }
        }
    }


}


internal open class AVLtree() {

    internal var root: Node? = null


    private fun ins(k: Int, nd: Node?): Node {
        nd ?: return Node(k, null, null)
        if(k == nd.key)
            return nd
        else if(k > nd.key) {
            nd.right = ins(k, nd.right)
            nd.rebalance()
            return nd
        }
        else {
            nd.left = ins(k, nd.left)
            nd.rebalance()
            return nd
        }
    }
    internal fun insert(k: Int) {
        root = ins(k, root)
    }


    private fun del(k: Int, nd: Node?): Node? {
        fun Node.minnode(): Node = left?.minnode() ?: this
        fun Node.delmin(): Node? {
            left ?: return right
            left = left?.delmin()
            rebalance()
            return this
        }

        nd ?: return null
        if(k < nd.key)
            nd.left = del(k, nd.left)
        else if(k > nd.key)
            nd.right = del(k, nd.right)
        else {
            nd.right ?: return nd.left
            val min = nd.right!!.minnode()
            min.right = nd.right!!.delmin()
            min.left = nd.left
            min.rebalance()
            return min
        }
        nd.balance()
        return nd
    }
    internal fun delete(k: Int) {
        root = del(k, root)
    }


    private fun search(k: Int, nd: Node?): Int {
        nd ?: return -1
        return if(k == nd.key)
            nd.key
        else if(k > nd.key)
            search(k, nd.right)
        else
            search(k, nd.left)
    }
    internal fun search(k: Int): Int = search(k, root)


    internal fun toText() : String {
        fun Node.toText_(): List<String> {
            val lText = left?.toText_()?.map { "|  $it" } ?: listOf("|  _\n")
            val rText = right?.toText_()?.map { "|  $it" } ?: listOf("|  _\n")
            val vText = listOf("($key)\n")
            return rText + vText + lText
        }
        val builder = StringBuilder()
        val lines = root?.toText_() ?: listOf("_\n")
        lines.forEach { builder.append(it) }
        return builder.toString()
    }
}


fun main(args: Array<String>) {
    fun Node.print(): String {
        return key.toString() + "  " + (right?.print()?:"") + "  " + (left?.print()?:"")
    }

    val t = AVLtree()
    t.insert(2)
    t.insert(10)
    t.insert(14)
    println(t.toText())
    t.insert(6)
    t.insert(12)
    t.insert(100)
    println(t.toText())
    t.insert(15)
    t.insert(1)
    t.root?.rebalance()
    println(t.toText())
}