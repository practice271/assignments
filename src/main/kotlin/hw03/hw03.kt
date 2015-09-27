package hw03

/**
 * Antropov Igor hw03
 */


open class Node (val key: Int, var height: Int, var left: Node?, var right: Node? ) {}



fun height (p : Node?) :Int {
    if (p != null) return p.height else return 0
}

fun bfactor (p: Node?) : Int {
    if (p != null) return height(p.right) - height(p.left)
        else return 0
}

fun fixheight (p: Node?) {
        val hl = height(p?.left)
        val hr = height(p?.right)
        if (hl > hr) p?.height = hl + 1 else p?.height = hr + 1
}


fun rotateRight(p: Node?) : Node? {
    val tmp: Node? = p?.left
    p?.left = tmp?.right
    tmp?.right = p
    fixheight(p)
    fixheight(tmp)
    return tmp
}

fun  rotateLeft (p: Node?) : Node? {
    val tmp: Node? = p?.right
    p?.right = tmp?.left
    tmp?.left = p
    fixheight(p)
    fixheight(tmp)
    return  tmp
}

fun balance (p: Node?) : Node? {
    fixheight(p)
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
    if( p == null ) return Node(k, 0, null, null)
    if( k < p.key)
        p.left = insert(p.left,k)
    else
        p.right = insert(p.right,k)
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
    if( p?.left == null )
        return p?.right
    p!!.left = removeMin(p!!.left)
    return balance(p)
}

fun remove(p: Node?, k: Int): Node?
{
    if( p == null ) return null
    if( k < p.key )
        p.left = remove(p.left, k)
        else
            if( k > p.key )
                p.right = remove(p.right, k)
                else
                    {
                        var q : Node? = p.left
                        var r : Node? = p.right ?: return q
                        var min: Node? = findMin(r)
                        min?.right = removeMin(r)
                        min?.left = q
                        return balance(min)
                    }
    return balance(p)
}

fun Node?.treeToText(): String{
    if (this == null) return "null"
    var leftStr: String
    var rightStr: String
    if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
    if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
    return leftStr + "," + this.key.toString() + "," + rightStr
}

fun main(args: Array<String>) {
        var t: Node? = null
        val a = arrayOf(5, 4, 6, 2, 1, 3, 7)
        for (x in a) {
                t = insert(t,x)
                println(t?.treeToText())
            }
        t = remove(t, 4)
        println(t?.treeToText())
        t = remove(t, 3)
        println(t?.treeToText())
        t = remove(t, 2)
        println(t?.treeToText())
        println(find(t,6))
    println(find(t,1))
    println(find(t,5))
    println(find(t,7))
}