// Homework 3
// Alekseev Aleksei, group 271.

package homework.hw03

class Node (val value : Int, var left_param : Node?, var right_param : Node? ) {
    public var left : Node? = left_param
        get() = $left
        set(newLeft : Node?) {
            $left = newLeft
            height_f = calcHeight()
        }
    public var right : Node? = right_param
        get() = $right
        set(newRight : Node?) {
            $right = newRight
            height_f = calcHeight()
        }
    private fun calcHeight() : Int =
            1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
    private var height_f : Int = calcHeight()
    fun height() : Int = height_f
}

fun balanceFactor(node : Node?) : Int {
    return (node?.right?.height() ?: 0) - (node?.left?.height() ?: 0)
}

fun rotateLeft(node : Node?) : Node? {
    var temp = node?.right
    node?.right = temp?.left
    temp?.left = node
    return temp
}

fun rotateRight(node : Node?) : Node? {
    var temp = node?.left
    node?.left = temp?.right
    temp?.right = node
    return temp
}

fun balance(node : Node?) : Node? {
    when {
        balanceFactor(node)  == 2 -> {
            if (balanceFactor(node?.right) < 0) node?.right = rotateRight(node?.right)
            return rotateLeft(node)
        }
        balanceFactor(node) == -2 -> {
            if (balanceFactor(node?.left) > 0) node?.left = rotateLeft(node?.left)
            return rotateRight(node)
        }
    }
    return node
}

fun insert(key : Int, node : Node?) : Node? {
    when {
        node == null     -> return Node(key, null, null)
        key < node.value -> node.left = insert(key , node.left)
        else             -> node.right = insert(key , node.right)
    }
    return balance(node)
}

fun findMin(node : Node?) : Node? {
    if (node != null) return findMin(node.left)
    else return node
}

fun removeMin(node : Node?) : Node? {
    if (node?.left == null) return node?.right
    else {
        node?.left = removeMin(node?.left)
        return balance(node)
    }
}

fun remove(key : Int, node : Node?) : Node? {
    when {
        node == null     -> return null
        key < node.value -> node.left = remove(key, node.left)
        key > node.value -> node.right = remove(key, node.right)
        else             -> {
            var tempLeft = node.left
            var tempRight = node.right ?: return tempLeft
            var nodeMin = findMin(node)
            nodeMin?.right = removeMin(tempRight)
            nodeMin?.left = node
        }
    }
    return balance(node)
}

fun find(key : Int, node : Node?) : Boolean {
    when {
        node == null      -> return false
        key < node.value  -> return find(key, node.left)
        key > node.value  -> return find(key, node.right)
        else              -> return true
    }
}

fun Node?.treeToText() : String {
    if (this == null) return "null"
    var tempLeft : String
    var tempRight : String
    if (left == null) tempLeft = "null" else tempLeft = "(" + left.treeToText() + ")"
    if (right == null) tempRight = "null" else tempRight = "(" + right.treeToText() + ")"
    return tempLeft + "," + value.toString() + "," + tempRight
}