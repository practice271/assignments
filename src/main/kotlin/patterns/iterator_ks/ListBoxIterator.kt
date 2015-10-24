package patterns.iterator_ks

/**
 * Concrete Iterator for ListBox.
 * Iterates 'backwards' so that items are iterated in the order they have been added.
 */
class ListBoxIterator<T>(listBox : ListBox<T>) : Iterator<T> {
    private val _listBox = listBox
    private var _curIndex = _listBox.size() - 1

    override fun hasNext() : Boolean = _curIndex > -1

    override fun next() : T = _listBox[_curIndex--]!! // workaround


}