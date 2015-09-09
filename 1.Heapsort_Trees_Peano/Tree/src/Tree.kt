/* Maximum way in tree and LCR, CLR, LRC of tree
   made by Guzel Garifullina
   Estimated time 2 hours
   real time      1 hour +
*/

abstract  class  Tree {}
open class Empty() : Tree(){}
open class Leaf ( val value:Int) : Tree(){}
open class Node (val value : Int, val left : Tree, val right : Tree) : Tree(){}

fun generateTree(lValue : Int, rValue : Int): Tree{
    if (lValue > rValue) return Empty()
    if (lValue == rValue ) return Leaf(lValue)

    val middle =  lValue + (rValue - lValue) / 2
    return Node(middle, generateTree(lValue,middle - 1), generateTree(middle + 1, rValue) )
}

fun Tree.toText() : String{
    fun Tree.toText_(): List<String>{
        when (this){
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = left.toText_().map {"| $it"}
                val rText = right.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    fun Tree.toTextNew() : List<String> {
        return fold(listOf("_\n"), { listOf("$it\n") }, { (value, lRes, rRes) ->
            val lText = lRes.map { "| $it"}
            val rText = rRes.map { "| $it"}
            val vText = listOf("$value\n")
            lText + vText + rText
        })
    }
    val builder = StringBuilder()
    val lines = this.toTextNew()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun <B>Tree.fold(fEmpty : B, fLeaf : (Int) -> B, fNode : (Int, B, B) -> B) : B {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return fLeaf(value)
        is Node  -> return fNode(value,
                left.fold(fEmpty, fLeaf, fNode),
                right.fold(fEmpty, fLeaf, fNode))
        else -> throw Exception("Unknown class")
    }
}

fun Tree.maxWay(min : Int) : Int{
    var max  = min
    fun maximum (a : Int, b : Int) : Int{
        if (a > b) return a
        else return b
    }
    fun Tree.maxWay_ (sum : Int) {
        when (this){
            is Leaf  -> max = maximum (max, sum + value)
            is Node  -> {
                left.maxWay_(sum + value)
                right.maxWay_(sum + value)
            }
        }
    }
    this.maxWay_(0)
    return max
}

fun main(args: Array<String>) {

    val tree = generateTree(0, 4)
    println("Initial tree")
    println(tree.toText())

    println("Maximal sum ${tree.maxWay(0)}\n")

    val tree1 = generateTree(-7, 0)
    println("Initial tree")
    println(tree1.toText())

    println("Maximal sum ${tree1.maxWay(-100)}\n")
}
