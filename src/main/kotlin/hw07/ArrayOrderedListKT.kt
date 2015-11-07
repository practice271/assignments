package hw07

/**
 * Created by Mikhail on 01.11.2015.
 */
class ArrayOrderedListKT<A : Comparable<A>> : AOrderedListKT<A>() {
    private val SIZE = 10
    private var items: Array<A> = arrayOfNulls<Comparable<Any>>(SIZE) as Array<A>
    private var size: Int = 0

    override fun add(item: A) {
        if (size + 1 >= items.size) {
            val newItems = arrayOfNulls<Comparable<Any>>(items.size + SIZE) as Array<A>
            System.arraycopy(items, 0, newItems, 0, size)
            items = newItems
        }
        for (i in size - 1 downTo 0) {
            if (items[i].compareTo(item) > 0) {
                items[i + 1] = items[i]
            } else {
                items[i + 1] = item
                size++
                return
            }
        }
        items[0] = item
        size++
        return
    }

    override fun get(index: Int): A {
        return items[index]
    }

    override fun size(): Int {
        return size
    }

    override fun contains(item: A): Boolean {
        for (i in size - 1 downTo 0) {
            if (items[i].compareTo(item) == 0) {
                return true
            }
        }
        return false
    }

    override fun iterator(): Iterator<A>{
        return object: Iterator<A> {
            internal var index = 0
            override fun hasNext(): Boolean {
                return index < size
            }

            override fun next(): A {
                index++
                return items[index - 1]
            }
        }
    }
}
