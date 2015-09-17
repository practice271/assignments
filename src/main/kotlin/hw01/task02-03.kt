// Задания 01-04 от 08.09.2015
// Автор: Кирилл Смиренко, группа 271
package hw01

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

// 02. Поиск максимальной суммы в дереве на путях от корня до листьев

fun Tree<Int>.maxPath() : Int = foldDown( { a, b -> a + b }, { a, b -> Math.max(a, b) }, 0, this)

// 03. Обобщенный метод для обхода деревьев сверху вниз.

fun <B> foldDown(fDown : (B, B) -> B, fUp : (B, B) -> B, acc : B, tree : Tree<B>) : B {
    when (tree) {
        is Empty<B> -> return acc
        is Node<B>  -> {
            val acc1 = fDown(acc, tree.value)
            val resLeft  = foldDown(fDown, fUp, acc1, tree.l)
            val resRight = foldDown(fDown, fUp, acc1, tree.r)
            return fUp(resLeft, resRight)
        }
        else -> throw Exception("Unknown class")
    }
}