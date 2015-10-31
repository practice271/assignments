package hw07

class ArrayOrdList_Kotlin<A : Comparable<A>>(size: Int, element: A) : OrdList<A>() {
    private var size = 0
    private val clusterSize = 10000
    private val array: Array<A>

    init {
        array = arrayOfNulls<Comparable<Any>>(clusterSize) as Array<A>
        for (i in 0..size - 1) {
            array[i] = element
        }
        this.size = size
    }

    override fun size(): Int {
        return size
    }

    override fun get(index: Int): A {
        if (index >= 0 && index < size) return array[index]
        throw ArrayIndexOutOfBoundsException()
    }

    override fun print() {
        for (i in 0..size - 1) {
            System.out.printf("%s ", get(i))
        }
        println()
    }

    override fun add(elem: A) {
        for (i in size - 1 downTo 0) {
            array[i + 1] = elem
            size++
            return
        }
        array[0] = elem
        size++
    }

    override fun remove(index: Int) {
        if (index >= 0 && index < size) {
            for (i in index..size - 1) {
                array[i] = array[i + 1]
            }
            size--
        }
    }
}

fun main(args: Array<String>) {
    val arr = ArrayOrdList(1, "zebra")
    println("Size = " + (arr.size()))
    arr.add("monkey")
    arr.add("panda")
    arr.add("mockingbird")
    arr.print()
    println("Size = " + (arr.size()))
    println("2 is " + (arr.get(2)))
    arr.remove(2)
    arr.print()
    println("hashcode arr:  " + arr.hashcode())
}

