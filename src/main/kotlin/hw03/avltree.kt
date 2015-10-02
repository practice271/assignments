package hw03

internal class Node(internal var key: Int, internal var left: Node?, internal var right: Node?) {

    internal constructor(k: Int): this(k, null, null) { }

    private fun height(): Int = 1 + Math.max((right?.height() ?: 0), (left?.height() ?: 0))

    private fun balance(): Int = (right?.height() ?: 0) - (left?.height() ?: 0)

    private fun ltrotate() {
        if(right != null) {
        left = Node(key, left, right?.left)
        key = right?.key ?: 0
        right = right?.right
        }
    }
    private fun rgrotate() {
        if(left != null) {
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
        when {
            k < nd.key -> nd.left = del(k, nd.left)

            k > nd.key -> nd.right = del(k, nd.right)
            else -> {
                nd.right ?: return nd.left
                val min = nd.right!!.minnode()
                min.right = nd.right!!.delmin()
                min.left = nd.left
                min.rebalance()
                return min
            }
        }
        nd.rebalance()
        return nd
    }
    internal fun delete(k: Int) {
        root = del(k, root)
    }


    private fun search(k: Int, nd: Node?): Int {
        nd ?: return -1
        return when {
            k == nd.key -> nd.key
            k > nd.key -> search(k, nd.right)
            else -> search(k, nd.left)
        }
    }
    internal fun search(k: Int): Int = search(k, root)


   /* internal fun toText() : String {
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
    } */
}
