package homeworks.hw07

import java.util.*

public class KotlinOrderedListATD<T : Comparable<T>> : KotlinOrderedList<T>() {
    private var size = 0
    private var first: T? = null
    private var tail: KotlinOrderedListATD<*>? = null

    public override fun size(): Int {
        return size
    }

    public override fun get(index: Int): T {
        if (index == 0) {
            return first as T
        }
        return getPosition(tail, 1, index)
    }

    private fun getPosition(l: KotlinOrderedListATD<*>?, i: Int, index: Int): T {
        if (i < index) {
            return getPosition(l?.tail, i + 1, index)
        }
        return l?.first as T
    }

    public override fun add(obj: T) {
        if (size == 0) {
            first = obj
            size++
            return
        }
        if (obj.compareTo(first as T) < 0) {
            val temp = KotlinOrderedListATD<T>()
            temp.first = first
            temp.tail = tail
            first = obj
            tail = temp
            size++
            return
        }
        tail = addPosition(tail, obj)
        size++
    }

    private fun addPosition(l: KotlinOrderedListATD<*>?, obj: T): KotlinOrderedListATD<T> {
        val res = KotlinOrderedListATD<T>()
        if (l == null) {
            res.first = obj
            return res
        }
        if ((l.first as T).compareTo(obj) >= 0) {
            res.first = obj
            res.tail = l
            return res
        }
        res.first = l.first as T
        res.tail = addPosition(l.tail, obj)
        return res
    }

    public override fun removeAt(index: Int) {
        if ((index <= 0 || index >= size)) {
            return
        }
        if (index == 0) {
            first = tail?.first as T
            tail = tail?.tail
            size--
            return
        }
        tail = removePosition(tail, 1, index)
        size--
    }

    private fun removePosition(l: KotlinOrderedListATD<*>?, i: Int, index: Int): KotlinOrderedListATD<*>? {
        if (i < index) {
            val res = KotlinOrderedListATD<T>()
            res.first = l?.first as T
            res.tail = removePosition(l?.tail, i + 1, index)
            return res
        }
        if (i == size - 1) {
            return null
        }
        return l?.tail
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var list: KotlinOrderedListATD<T>? = null

            override fun hasNext(): Boolean {
                return (list != null)
            }

            override fun next(): T {
                val temp = list?.first as T
                list = list?.tail as KotlinOrderedListATD<T>
                return temp
            }
        }
    }
}