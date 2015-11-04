package patterns.iterator_ks

import java.util.ArrayList

/**
 * Concrete Aggregate : list implementation of IBox.
 */
class ListBox<T> : IBox<T> {
    private val _values = ArrayList<T>()

    override fun get(index : Int) = _values.getOrNull(index)

    override fun add(value : T) {
        _values.add(value)
    }

    // ITERATOR PATTERN
    override fun iterator() : Iterator<T> = ListBoxIterator(this)

    fun size() = _values.size
}