package hw07

class KotlinMyArray<A : Comparable<A>> : IOrderedList<A>
{
    private var lengthArray = 2
    private var arr = newArray()
    private var position = 0

    fun newArray() : Array<A>
    {
        return arrayOfNulls<Comparable<Any>>(lengthArray) as Array<A>
    }

    override fun getLength() : Int
    {
        return position
    }

    override fun push (newElem : A)
    {
        val newArr: Array<A>
        position++

        if (position > lengthArray)
        {
            lengthArray += 2
        }

        newArr = newArray()

        var i: Int
        i = 0
        while (i < position - 1)
        {
            if (arr[i] == null || newElem.compareTo(arr[i]) > 0) break
            i++
        }

        if (i > 0)
            System.arraycopy(arr, 0, newArr, 0, i)

        newArr[i] = newElem

        System.arraycopy(arr, i, newArr, i + 1, position - 1 - i)

        arr = newArr
    }

    override fun removeAt (index : Int)
    {
        if (index >= position) return

        position--
        System.arraycopy(arr, index + 1, arr, index, lengthArray - 1 - index)
    }

    override fun remove (elem : A)
    {
        var i: Int

        i = 0
        while (i < position)
        {
            if (elem.compareTo(arr[i]) == 0) break
            i++
        }

        if (i == position) return
        removeAt(i)
    }

    override fun getAt(index : Int) : A?
    {
        if (index >= position) return null
        return arr[index]
    }

    override fun hashCode() : Int
    {
        var hash = 0

        for (i in 0..position - 1)
        {
            hash += arr[i].hashCode() * 71
        }

        return hash
    }

    override fun equals(obj : Any?) : Boolean
    {
        return obj is IOrderedList<*> && (obj as IOrderedList<*>?)?.hashCode() == hashCode() && (obj as IOrderedList<*>?)?.getLength() == getLength()
    }
}
