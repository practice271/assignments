package patterns.iterator_ks

/**
 * Concrete Iterator for SetBox.
 */
class SetBoxIterator<T : Comparable<T>>(setBox : SetBox<T>) : Iterator<T> {
    private val _setBox = setBox
    private var _curIndex = 0

    override fun hasNext() : Boolean = _curIndex < _setBox.size()

    override fun next() : T = _setBox[_curIndex++]!! // workaround
}