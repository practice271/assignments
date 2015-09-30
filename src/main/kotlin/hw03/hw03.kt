package hw03

/**
 * Created by Mikhail on 22.09.2015.
 */
import java.util.*

public class AvlNode(public var key: Int) {
    public var left: AvlNode? = null
    public var right: AvlNode? = null
    public var parent: AvlNode? = null
    public var balance: Int = 0

    public override fun toString(): String {
        return "" + key
    }
}

public class AvlTree {

    private var root: AvlNode? = null

    //insert fun
    public fun insert(k: Int) {
        val n = AvlNode(k)
        insertAVL(this.root, n)
    }

    private fun insertAVL(p: AvlNode?, q: AvlNode) {

        if (p == null) {
            this.root = q
            return
        }
        when (Integer.compare(q.key, p.key)) {
            -1 -> {
                if (p.left == null) {
                    p.left = q
                    q.parent = p

                    rebalance(p)
                } else {
                    insertAVL(p.left, q)
                }
            }
            1 -> {
                if (p.right == null) {
                    p.right = q
                    q.parent = p

                    rebalance(p)
                } else {
                    insertAVL(p.right, q)
                }
            }
        }

    }

    private fun rebalance(cur: AvlNode?) {

        var cur = cur
        setBalance(cur)
        val balance = cur?.balance

        when (balance) {
            -2 -> {
                if (height(cur?.left?.left) >= height(cur?.left?.right)) {
                    cur = rotateRight(cur)
                } else {
                    cur = doubleRotateLeftRight(cur)
                }
            }
            2 -> {
                if (height(cur?.right?.right) >= height(cur?.right?.left)) {
                    cur = rotateLeft(cur)
                } else {
                    cur = doubleRotateRightLeft(cur)
                }
            }

        }

        if (cur?.parent != null) {
            rebalance(cur?.parent)
        } else {
            this.root = cur
        }
    }
    //delete fun
    public fun delete(k: Int) {
        deleteAVL(this.root, k)
    }

    private fun deleteAVL(p: AvlNode?, q: Int) {
        if (p == null) {
            return
        }
        when (Integer.compare(p.key, q)) {
            1    -> deleteAVL(p.left, q)
            -1   -> deleteAVL(p.right, q)
            else -> deleteFoundNode(p)
        }
    }

    private fun deleteFoundNode(q: AvlNode?) {
        var r: AvlNode?
        if (q?.left == null || q?.right == null) {
            if (q?.parent == null) {
                this.root = null
                return
            }
            r = q
        } else {
            r = successor(q)
            q?.key = r?.key
        }

        val p: AvlNode?
        if (r?.left != null) {
            p = r?.left
        } else {
            p = r?.right
        }

        if (p != null) {
            p.parent = r?.parent
        }

        if (r?.parent == null) {
            this.root = p
        } else {
            if (r == r?.parent?.left) {
                r?.parent?.left = p
            } else {
                r?.parent?.right = p
            }
            rebalance(r?.parent)
        }
    }

    private fun rotateLeft(node: AvlNode?): AvlNode? {

        val v = node?.right
        v?.parent = node?.parent

        node?.right = v?.left

        if (node?.right != null) {
            node?.right?.parent = node
        }

        v?.left = node
        node?.parent = v

        if (v?.parent != null) {
            if (v?.parent?.right == node) {
                v?.parent?.right = v
            } else if (v?.parent?.left == node) {
                v?.parent?.left = v
            }
        }

        setBalance(node)
        setBalance(v)

        return v
    }

    private fun rotateRight(node: AvlNode?): AvlNode? {

        val v = node?.left
        v?.parent = node?.parent

        node?.left = v?.right

        if (node?.left != null) {
            node?.left?.parent = node
        }

        v?.right = node
        node?.parent = v
        if (v?.parent != null) {
            if (v?.parent?.right == node) {
                v?.parent?.right = v
            } else if (v?.parent?.left == node) {
                v?.parent?.left = v
            }
        }

        setBalance(node)
        setBalance(v)

        return v
    }

    private fun doubleRotateLeftRight(node: AvlNode?): AvlNode? {
        node?.left = rotateLeft(node?.left)
        return rotateRight(node)
    }

    private fun doubleRotateRightLeft(node: AvlNode?): AvlNode? {
        node?.right = rotateRight(node?.right)
        return rotateLeft(node)
    }

    private fun successor(node: AvlNode?): AvlNode? {
        var q = node
        if (q?.right != null) {
            var r = q?.right
            while (r?.left != null) {
                r = r?.left
            }
            return r
        } else {
            var p = q?.parent
            while (p != null && q == p.right) {
                q = p
                p = q.parent
            }
            return p
        }
    }

    private fun height(node: AvlNode?): Int {
        if (node == null) return -1
        return 1 + Math.max(height(node.left), height(node.right))
    }

    private fun setBalance(cur: AvlNode?) {
        cur?.balance = height(cur?.right) - height(cur?.left)
    }
    //search fun
    public fun search(key: Int): Int {
        return searchNode(root, key)
    }

    private fun searchNode(n: AvlNode?, x: Int): Int {
        if (n == null) {
            return -1
        }
        when (Integer.compare(n.key, x)) {
            1    -> return searchNode(n.left, x)
            -1   -> return searchNode(n.right, x)
            else -> return n.key
        }
    }

    // print fun
    public fun show() {
        print("\n====AvlTree====\n\n")
        if (root == null) {print("Empty tree\n")}
        else {print(print(root))}
    }

    private fun print(root: AvlNode?): String {
        var sb = StringBuilder { }
        printTree(root, "", sb)
        return sb.toString()
    }

    private fun printTree(node: AvlNode?, indent: String, sb: StringBuilder){
        sb.appendln(node.toString())
        if (node?.left != null) {
            if (node?.right  == null) {
                printChild(node?.left, indent, sb)
            }
            else {
                printChild(node?.left, indent,sb)
                printChild(node?.right, indent, sb)
            }
        }
    }

    private fun printChild(node: AvlNode?, indent: String, sb: StringBuilder) {
        sb.append(indent)
        sb.append("|---")
        printTree(node, indent + "  ", sb)
    }

    public fun inorder(): ArrayList<AvlNode> {
        val ret = ArrayList<AvlNode>()
        inorder(root, ret)
        return ret
    }

    private fun inorder(node: AvlNode?, io: ArrayList<AvlNode>) {
        if (node == null) {
            return
        }
        inorder(node.left, io)
        io.add(node)
        inorder(node.right, io)
    }
}

public fun main(args: Array<String>) {

}