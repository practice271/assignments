package hw07

import java.util.SortedMap

/*class MyList<A : Comparable<A>> : IOrderedList<A> {
    private inner class CList(var head: A, var tail: CList?)

    private var list: CList? = null
    private var lengthList = 0

    private fun add(cList: CList?, elem: A): CList {
        if (cList != null && (elem.compareTo(cList.head) < 0))
            return CList(cList.head, add(cList.tail, elem))

        return CList(elem, cList)
    }

    override fun push(newElem: A) {
        lengthList++
        list = add(list, newElem)
    }

    private fun deleteAt(cList: CList?, idx: Int): CList? {
        if (idx == 0)
            if (cList == null)
                return null
            else
                return cList.tail

        return CList(cList!!.head, deleteAt(cList.tail, idx - 1))
    }

    private fun delete(cList: CList?, elem: A): CList? {
        if (cList == null)
            return null
        else if (cList.head.compareTo(elem) == 0) {
            lengthList--
            return cList.tail
        }

        return CList(cList.head, delete(cList.tail, elem))
    }

    override fun removeAt(index: Int) {
        if (index < lengthList)
            list = deleteAt(list, index)
    }

    override fun remove(elem: A) {
        list = delete(list, elem)
    }

    private fun getElem(cList: CList?, idx: Int): A? {
        if (idx == 0)
            if (cList == null)
                return null
            else
                return cList.head
        return getElem(cList!!.tail, idx - 1)
    }

    override fun getAt(index: Int): A? {
        if (index >= lengthList) return null
        return getElem(list, index)
    }

    override fun getLength(): Int {
        return lengthList
    }

    override fun hashCode(): Int {
        var hash = 0
        var cList = list

        while (cList != null) {
            hash += cList.head.hashCode() * 71
            cList = cList.tail
        }

        return hash
    }

    override fun equals(obj: Any?): Boolean {
        if (obj !is IOrderedList<*>)
            return false
        else {

            if (obj.length == length) {
                val iter = iterator()

                var someList = list

                while (someList != null) {
                    if (iter.next() !== someList.head) return false
                    someList = someList.tail
                }

                return true
            } else
                return false
        }
    }

    override fun iterator(): MutableIterator<A> {
        return object : MutableIterator<A> {

            internal var someList = list

            override fun hasNext(): Boolean {
                return (someList != null)
            }

            override fun next(): A {
                val elem = someList!!.head
                someList = someList!!.tail
                return elem
            }

            override fun remove() {
            }
        }
    }
}
*/