package homework3

class avlTree(left_param : avlTree?, val key: Int, right_param: avlTree?) {
    public var lTree: avlTree? = left_param
        get() = $lTree
        set(newLeft: avlTree?) {
            $lTree = newLeft
            height_f = calcHeight()
        }

    public var rTree: avlTree? = right_param
        get() = $rTree
        set(newRight: avlTree?) {
            $rTree = newRight
            height_f = calcHeight()
        }
    private fun calcHeight() : Int =
            1 + Math.max(lTree?.calcHeight() ?: 0, rTree?.calcHeight() ?: 0)
    private var height_f : Int = calcHeight()
    fun height(): Int = height_f
}

fun bfactor(tree: avlTree?):Int = ((tree?.rTree?.height() ?: 0) - (tree?.lTree?.height() ?: 0))

fun rotateRight(tr:avlTree?):avlTree?{
    var tmp = tr
    var tree = tmp?.lTree
    tmp?.lTree = tree?.rTree
    tree?.rTree = tmp
    return tree
}

fun rotateLeft(tr:avlTree?):avlTree?{
    var tmp = tr
    var tree = tmp?.rTree
    tmp?.rTree = tree?.lTree
    tree?.lTree = tmp
    return tree
}

fun balance(tr: avlTree?):avlTree?{
    if (bfactor(tr) == 2) {
        if (bfactor(tr?.rTree) < 0) tr?.rTree = rotateRight(tr?.rTree)
        return rotateLeft(tr)
    }
    if (bfactor(tr) == -2) {
        if (bfactor(tr?.lTree) > 0) tr?.lTree = rotateLeft(tr?.lTree)
        return rotateRight(tr)
    }
    return tr
}

fun insert(tr:avlTree?, numb:Int):avlTree?{
    when{
        (tr == null) -> return avlTree(null, numb, null)
        (tr.key < numb) -> tr.rTree = insert(tr.rTree, numb)
        (tr.key > numb) -> tr.lTree = insert(tr.lTree, numb)
    }
    return balance(tr)
}

fun delete(tr:avlTree?, numb:Int):avlTree?{
    fun findMin(tree: avlTree?):avlTree? {
        if (tree?.lTree != null) return findMin(tree?.lTree)
        else return tree
    }
    fun removeMin(tree: avlTree?):avlTree?{
        when{
            tree == null -> {}
            tree.lTree == null -> return tree.rTree
            else -> tree.lTree = removeMin(tree.lTree)
        }
        return balance(tree)
    }
    when {
        (tr == null) -> return null
        (tr.key < numb) -> tr.rTree = delete(tr.rTree, numb)
        (tr.key > numb) -> tr.lTree = delete(tr.lTree, numb)
        else -> {
            var l = tr.lTree
            var r = tr.rTree
            if (r == null) return l
            var min = findMin(r)
            min?.rTree = removeMin(r)
            min?.lTree = l
            return balance(min)
        }
    }
    return balance(tr)
}

fun avlTree?.toText() : String {
    fun avlTree?.toText_() : List<String> {
            when (this) {
                null -> return listOf("_\n")
                avlTree(null, this.key , null ) -> return listOf("${key}\n")
                else -> {
                    val rText = rTree.toText_().map { "| $it"}
                    val lText = lTree.toText_().map { "| $it"}
                    val vText = listOf("${key}\n")
                    return rText + vText + lText
                }
            }
    }
    val builder = StringBuilder()
    val lines = toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun search(numb: Int,tr:avlTree?): Boolean {
    if (tr != null){
        if (tr.key == numb) return true
        else if (tr.key > numb) return search(numb, tr.lTree)
        else return search(numb, tr.rTree)
    }
    else return false
}

fun main(args: Array<String>) {
}

