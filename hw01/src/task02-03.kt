// Задания 01-04 от 08.09.2015
// Автор: Кирилл Смиренко, группа 271

// 02. Поиск максимальной суммы в дереве на путях от корня до листьев

abstract class Tree<B> {}
open class Empty<B>() : Tree<B>() {}
open class Node<B>(val value : B, val l : Tree<B>, val r : Tree<B>) : Tree<B>() {}

fun intLeaf(v : Int) : Tree<Int> = Node(v, Empty<Int>(), Empty<Int>())

fun Tree<out Any>.toText() : String {
    fun Tree<out Any>.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Node  -> {
                val lText = l.toText_().map { "| $it"}
                val rText = r.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun max(a: Int, b: Int) : Int = if (a > b) a else b

fun Tree<Int>.maxPath() : Int {
    fun maxPath_(acc: Int, tree: Tree<Int>): Int {
        when (tree) {
            is Empty -> return acc
            is Node -> {
                val localSum = acc + tree.value
                return max(maxPath_(localSum, tree.l), maxPath_(localSum, tree.r))
            }
            else -> throw Exception("Error")
        }
    }
    return maxPath_(0, this)
}

/* fun <B> Tree<B>.foldUp(fEmpty<B> : B, fLeaf : (Int) -> B, fNode : (Int, B, B) -> B) : B {
    when (this) {
        is Empty<B> -> return fEmpty<B>
        is Leaf  -> return fLeaf(value)
        is Node  -> return fNode(value,
                l.foldUp(fEmpty<B>, fLeaf, fNode),
                r.foldUp(fEmpty<B>, fLeaf, fNode))
        else -> throw Exception("Unknown class")
    }
}

fun Tree<B>.toText() : List<String> {
    return foldUp(listOf("_"), { listOf("$it") }, { value, lRes, rRes ->
        val lText = lRes.map { "| $it"}
        val rText = rRes.map { "| $it"}
        val vText = listOf("$value")
        lText + vText + rText
    })
}*/

// 03. Обобщенный метод для обхода деревьев сверху вниз.

fun <B> fold(f : (B, B) -> B, acc : B, tree : Tree<B>) : B {
    when (tree) {
        is Empty<B> -> return acc
        is Node<B>  -> return fold(f, fold(f, f(acc, tree.value), tree.l), tree.r)
        else -> throw Exception("Unknown class")
    }
}