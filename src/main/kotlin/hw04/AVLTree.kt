package hw04

public class AVLtree: SetInterface() {

    //------------------------------NODE CLASS-----------------------------------

    private inner class Node(public var key: Int,
                             left_param: Node?,
                             right_param: Node?) {


        public var left: Node? = left_param
            get() = $left
            set(newLeft: Node?) {
                $left = newLeft
                height_f = calcHeight()
            }
        public var right: Node? = right_param
            get() = $right
            set(newR: Node?) {
                $right = newR
                height_f = calcHeight()
            }

        private var height_f : Int = calcHeight()


        private fun calcHeight() : Int = 1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)

        private fun height(): Int = height_f

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


        public fun rebalance() {
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

    //-------------------------------END OF NODE CLASS-------------------------


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
    override public fun insert(k: Int) {
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
    override public fun delete(k: Int) {
        root = del(k, root)
    }


    private fun search(k: Int, nd: Node?): Boolean {
        nd ?: return false
        return when {
            k == nd.key -> true
            k > nd.key -> search(k, nd.right)
            else -> search(k, nd.left)
        }
    }
    override public fun search(k: Int): Boolean = search(k, root)

    override public fun isEmpty(): Boolean = root == null

    override public fun toList(): List<Int> {
        fun Node.toList() : List<Int> {
            val lText = left?.toList() ?: listOf()
            val rText = right?.toList() ?: listOf()
            val vText = listOf(key)
            return vText + lText + rText
        }

        return root?.toList() ?: listOf()
    }

    override public fun union(s: SetInterface): SetInterface {
        val t: SetInterface = AVLtree()
        if(isEmpty() || (s as AVLtree).isEmpty()) return t
        toList().forEach {
            if(s.search(it)) t.insert(it.toInt())
        }
        return t
    }

    override public fun intersection(s: SetInterface): SetInterface {
        val t: SetInterface = this
        if((t as AVLtree).isEmpty()) return s
        if((s as AVLtree).isEmpty()) return t
        s.toList().forEach {
            if(!t.search(it.toInt())) t.insert(it.toInt())
        }
        return t
    }

    /*
        public fun toText() : String {
            fun Node.toText(): List<String> {
                val lText = left?.toText()?.map { "|  $it" } ?: listOf("|  _\n")
                val rText = right?.toText()?.map { "|  $it" } ?: listOf("|  _\n")
                val vText = listOf("($key)\n")
                return rText + vText + lText
            }
            val builder = StringBuilder()
            val lines = root?.toText() ?: listOf("_\n")
            lines.forEach { builder.append(it) }
            return builder.toString()
        }*/

    /*override public fun toString() : String {
        fun Node.print() : String {
            val lText = left?.print() ?: ""
            val rText = right?.print() ?: ""
            val vText = key.toString() + " "
            return vText + lText + rText
        }

        return root?.print() ?: "TIE"
    }*/

}

/*
fun toList(t: Array<LinkedList<Int>>): List<Int> {
    var res: List<Int> = emptyList()
    t.forEach { res += it }
    return res
}

fun main(args: Array<String>) {
    val r = Array(4, { LinkedList<Int>() })
    r.forEach { it.add(2) }
    println(toList(r))
}*/

