package homework.hw03

class Node(val value: Int, var left_param: Node?, var right_param: Node?){
    public var left: Node? = left_param
        get() = $left
        set(newLeft: Node?) {
            $left = newLeft
            height_f = calcHeight()
        }

    public var right: Node? = right_param
        get() = $right
        set(newRight: Node?) {
            $right = newRight
            height_f = calcHeight()
        }
    private fun calcHeight() : Int =
            1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
    private var height_f : Int = calcHeight()
    fun height(): Int = height_f
}

fun getBalanceFactor(node : Node?):Int{
    return (node?.right?.height() ?:0) - (node?.left?.height() ?:0)
}

fun turnRight(node : Node?):Node?{
    var tmp     = node?.left
    node?.left  = tmp?.right
    tmp?.right  = node
    return tmp
}

fun turnLeft(node : Node?):Node?{
    var tmp     = node?.right
    node?.right = tmp?.left
    tmp?.left   = node
    return tmp
}

fun balance(node : Node?):Node?{
    when {
        getBalanceFactor(node)  == 2 -> {
            if (getBalanceFactor(node?.right) < 0) node?.right = turnRight(node?.right)
            return turnLeft(node)
        }

        getBalanceFactor(node) == -2 -> {
            if (getBalanceFactor(node?.left) > 0) node?.left = turnLeft(node?.left)
            return turnRight(node)
        }
    }
    return node
}

fun insert(key:Int, node : Node?):Node?{
    when {
        node == null     -> return Node(key, null, null)
        key < node.value -> node.left = insert(key , node.left)
        else             -> node.right = insert(key , node.right)
    }
    return balance(node)
}

fun findMin(node : Node?):Node?{
    if (node!=null) return findMin(node.left)
    else return null
}

fun removeMin(node : Node?):Node?{
    if (node?.left==null) return node?.right
    else{
        node?.left = removeMin(node?.left)
        return balance(node)
    }
}

fun remove(key:Int, node : Node?):Node?{
    when{
        node == null     -> return null
        key < node.value -> {
            node.left = remove(key, node.left)
        }
        key > node.value -> {
            node.right = remove(key, node.right)
        }
        else             -> {
            var tmpLeft  = node.left
            var tmpRight = node.right ?: return tmpLeft
            var nodeMin   = findMin(node)
            nodeMin?.right = removeMin(tmpRight)
            nodeMin?.left  = node
        }
    }
    return balance(node)
}

fun find(key:Int,node: Node?):Boolean{
    when{
        node == null      -> return false
        key < node.value  -> return find(key, node.left)
        key > node.value  -> return find(key, node.right)
        else              -> return true
    }
}

fun Node?.treeToText():String{
    if (this == null) return "null"
    var leftStr: String
    var rightStr: String
    if (left == null) leftStr = "null" else leftStr = "(" + left!!.treeToText() + ")"
    if (right == null) rightStr = "null" else rightStr = "(" + right!!.treeToText() + ")"
    return leftStr + "," + value.toString() + "," + rightStr
}
