package classroom.c06

import java.util.*

abstract class IOrderedList() {
    public abstract fun get(index: Int): Int
}

class ArrayOrderedList(public val size: Int): IOrderedList() {
    private val array: Array<Int> = Array(size) { 0 }

    override public fun get(index: Int): Int {
        if (index < 0 || index >= size) {
            throw NoSuchElementException()
        }
        return array[index]
    }

    public operator fun getOp(index: Int): Int = get(index)
}

class OrderedLinkedList(public val size: Int): IOrderedList() {
    private val list: LinkedList<Int> = LinkedList()
    init {
        for (i in 0..(size-1)) {
            list.add(5)
        }
    }

    override fun get(index: Int): Int {
        if (index < 0) { throw NoSuchElementException() }
        var curElemIndex: Int = 0
        for (e in list) {
            if (curElemIndex == index) { return e }
            curElemIndex++
        }
        throw NoSuchElementException()
    }
}

operator fun <A> Iterable<A>.in1(f: A.() -> Unit) {
    val it = this.iterator()
    while (it.hasNext()) {
        val e = it.next()
        e.f()
    }
}

fun main(args: Array<String>) {
    listOf<Int>(1, 2, 3) in1 {
        println(this)
    }

    val orderedList: ArrayOrderedList = ArrayOrderedList(5)
    //val orderedList: IOrderedList = OrderedLinkedList(5)
    try {

        /*
        for (i in 1..5) {

        }
        */

        println(orderedList.get(6))
    } catch (e: NoSuchElementException) {
        println("NoSuchElementException")
    }

}
