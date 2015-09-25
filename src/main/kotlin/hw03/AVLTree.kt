package hw03

class Node(value : Int, l : Node?, r: Node?)
{
    val Key : Int = value
    var leftKid : Node? = l
    var rightKid : Node? = r

    var Height : Int = CalcHeight()

    fun CalcHeight() : Int = Math.max(leftKid?.CalcHeight() ?: 0, rightKid?.CalcHeight() ?: 0) + 1

    fun FixHeight ()
    {
        Height  = CalcHeight()
    }

    fun CalcBalance () = (leftKid?.CalcHeight() ?: 0) - (rightKid?.CalcHeight() ?: 0)

    fun Balance () : Node?
    {
        if (CalcBalance() == 2)
        {
            if ((rightKid?.CalcBalance() ?: -1) > 0)
                rightKid = RightTurn(rightKid)
            return LeftTurn(this)
        }
        else if (CalcBalance() == -2)
        {
            if ((leftKid?.CalcBalance() ?: 1) < 0)
                leftKid = LeftTurn(leftKid)
            return RightTurn(this)
        }
        else return this
    }

    fun PrintTree() : String
    {
        fun PrintTree_() : List<String>
        {
            val lText = this.leftKid?.PrintTree()?.map { "| $it" } ?: listOf("")
            val rText = this.rightKid?.PrintTree()?.map { "| $it" } ?: listOf("")
            val vText = listOf("$Key\n")
            return lText + vText + rText
        }

        if (this == null)
            return ""

        val builder = StringBuilder()
        val lines = PrintTree_()
        lines.forEach { builder.append(it) }
        return builder.toString()
    }
}

fun RightTurn(badNode : Node?) : Node?
{
    val node : Node? = badNode?.rightKid
    badNode?.rightKid = node?.leftKid
    node?.leftKid = badNode
    badNode?.CalcHeight()
    node?.CalcHeight()
    return node
}

fun LeftTurn(badNode : Node?) : Node?
{
    val node : Node? = badNode?.leftKid
    badNode?.leftKid = node?.rightKid
    node?.rightKid = badNode
    badNode?.CalcHeight()
    node?.CalcHeight()
    return node
}

fun Add (root : Node?, value : Int) : Node?
{
    if (root == null) return Node(value, null, null)
    if (value < root.Key)
        root.leftKid = Add(root.leftKid, value)
    else
        root.rightKid = Add(root.rightKid, value)
    return root.Balance()
}

fun Remove (root : Node?, key : Int) : Node?
{
    fun findMin (root : Node?) : Node?
    {
        if (root?.leftKid == null)
            return root
        else
            return findMin(root?.leftKid)
    }

    fun removeMin(root : Node?) : Node?
    {
        if (root?.leftKid == null)
            return root?.rightKid
        root?.leftKid = removeMin(root?.leftKid)
        return root?.Balance()
    }

    if (root == null)
        return null
    if (key < root.Key)
        root.leftKid = Remove(root.leftKid, key)
    else if (key > root.Key)
        root.rightKid = Remove(root.rightKid, key)
    else
    {
        val lNode : Node? = root.leftKid
        val rNode : Node? = root.rightKid ?: return lNode

        val minNode : Node? = findMin(rNode)
        minNode?.rightKid = removeMin(rNode)
        minNode?.leftKid = lNode

        return minNode?.Balance()
    }

    return root.Balance()
}

fun  SearchNode (root : Node?, key : Int) : Boolean
{
    if (root == null)
        return false
    if (root.Key < key)
        return SearchNode(root?.rightKid, key)
    if (root.Key > key)
        return SearchNode(root?.leftKid, key)
    else if (root.Key == key)
        return true

    return false
}

fun main(args : Array<String>)
{
    var tree : Node? = null

    tree = Add(tree, 5)
    tree = Add(tree, 4)
    tree = Add(tree, 3)
    tree = Add(tree, 2)

    print(tree?.PrintTree())

    println(SearchNode(tree, 3))
    println(SearchNode(tree, 1))

    println(Remove(tree, 3)?.PrintTree())
}
