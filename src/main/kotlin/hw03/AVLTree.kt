package hw03

class Node (value : Int, l : Node?, r: Node?)
{
    val Key : Int = value
    var leftChild : Node? = l
    var rightChild : Node? = r

    var Height : Int = calcHeight()

    private fun calcHeight() : Int = Math.max(leftChild?.calcHeight() ?: 0, rightChild?.calcHeight() ?: 0) + 1

    private fun FixHeight ()
    {
        Height  = calcHeight()
    }

    private fun calcBalance() = (leftChild?.calcHeight() ?: 0) - (rightChild?.calcHeight() ?: 0)

    public  fun balance() : Node?
    {
        val checkBalance = calcBalance()

        when (checkBalance)
        {
            2   ->  {
                    if ((rightChild?.calcBalance() ?: -1) > 0)
                        rightChild = rightChild?.rightTurn()
                    return this.leftTurn()
                    }
            -2  ->  {
                    if ((leftChild?.calcBalance() ?: 1) < 0)
                        leftChild = leftChild?.leftTurn()
                    return this.rightTurn()
                    }
            else -> return this
        }
    }

    public  fun printTree() : String
    {
        fun printTree_() : List<String>
        {
            val lText = this.leftChild?.printTree()?.map { "| $it" } ?: listOf("")
            val rText = this.rightChild?.printTree()?.map { "| $it" } ?: listOf("")
            val vText = listOf("$Key\n")
            return lText + vText + rText
        }

        if (this == null)
            return ""

        val builder = StringBuilder()
        val lines = printTree_()
        lines.forEach { builder.append(it) }
        return builder.toString()
    }

    private fun rightTurn() : Node?
    {
        val node : Node? = this.rightChild
        this.rightChild = node?.leftChild
        node?.leftChild = this
        this.calcHeight()
        node?.calcHeight()
        return node
    }

    private fun leftTurn() : Node?
    {
        val node : Node? = this.leftChild
        this.leftChild = node?.rightChild
        node?.rightChild = this
        this.calcHeight()
        node?.calcHeight()
        return node
    }
}



fun Add (root : Node?, value : Int) : Node?
{
    if (root == null) return Node(value, null, null)
    if (value < root.Key)
        root.leftChild = Add(root.leftChild, value)
    else
        root.rightChild = Add(root.rightChild, value)
    return root.balance()
}

fun Remove (root : Node?, key : Int) : Node?
{
    fun findMin (root : Node?) : Node?
    {
        if (root?.leftChild == null)
            return root
        else
            return findMin(root?.leftChild)
    }

    fun removeMin (root : Node?) : Node?
    {
        if (root?.leftChild == null)
            return root?.rightChild
        root?.leftChild = removeMin(root?.leftChild)
        return root?.balance()
    }

    if (root == null)
        return null

    when (Integer.compare(key, root.Key))
    {
        -1      -> root.leftChild = Remove(root.leftChild, key)
        1       -> root.rightChild = Remove(root.rightChild, key)
        else    ->
                {
                    val lNode : Node? = root.leftChild
                    val rNode : Node? = root.rightChild ?: return lNode

                    val minNode : Node? = findMin(rNode)
                    minNode?.rightChild = removeMin(rNode)
                    minNode?.leftChild = lNode

                    return minNode?.balance()
                }
    }

    return root.balance()
}

fun SearchNode (root : Node?, key : Int) : Boolean
{
    if (root == null)
        return false

    when (Integer.compare(key, root.Key))
    {
        -1      -> return SearchNode(root?.rightChild, key)
        1       -> return SearchNode(root?.leftChild, key)
        else    -> return true
    }
}

fun main (args : Array<String>)
{
    var tree : Node? = null

    tree = Add(tree, 5)
    tree = Add(tree, 4)
    tree = Add(tree, 3)
    tree = Add(tree, 2)

    print(tree?.printTree())

    println(SearchNode(tree, 3))
    println(SearchNode(tree, 1))

    println(Remove(tree, 3)?.printTree())
}
