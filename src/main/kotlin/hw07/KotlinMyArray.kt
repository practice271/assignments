package hw07

/*class MyArray<A : Comparable<A>> : IOrderedList<A> {
    private val resizeConst = 2
    private var lengthArray = 2
    private var arr = newArray()
    private var index = 0

    fun newArray(): Array<A> {
        return arrayOfNulls<Comparable<Any>>(lengthArray) as Array<A>
    }

    override fun getLength(): Int {
        return index
    }

    override fun push(newElem: A) {
        val newArr: Array<A>
        index++

        if (index > lengthArray) {
            lengthArray *= resizeConst
        }

        newArr = newArray()

        var i: Int
        i = 0
        while (i < index - 1) {
            if (arr[i] == null || newElem.compareTo(arr[i]) > 0) break
            i++
        }

        if (i > 0)
            System.arraycopy(arr, 0, newArr, 0, i)

        newArr[i] = newElem

        System.arraycopy(arr, i, newArr, i + 1, index - 1 - i)

        arr = newArr
    }

    override fun removeAt(index: Int) {
        if (index >= this.index) return

        this.index--
        System.arraycopy(arr, index + 1, arr, index, lengthArray - 1 - index)
    }

    override fun remove(elem: A) {
        for (i in 0..index - 1)
            if (elem.compareTo(arr[i]) == 0) {
                removeAt(i)
                return
            }
    }

    override fun getAt(index: Int): A? {
        if (index >= this.index) return null
        return arr[index]
    }

    override fun hashCode(): Int {
        var hash = 0

        for (i in 0..index - 1) {
            hash += arr[i].hashCode() * 71
        }

        return hash
    }

    override fun equals(obj: Any?): Boolean {
        if (obj !is IOrderedList<*> )
            return false
        else {

            if (obj.length == length) {
                val iter = iterator()
                for (i in 0..index - 1) {
                    if (iter.next() !== obj.getAt(i)) return false
                }

                return true
            } else
                return false
        }
    }

    override fun iterator(): MutableIterator<A> {
        return object : MutableIterator<A> {

            internal var idx = 0

            override fun hasNext(): Boolean {
                return (idx < lengthArray)
            }

            override fun next(): A {
                idx++
                return arr[idx]
            }

            override fun remove() {
            }
        }
    }
}
*/