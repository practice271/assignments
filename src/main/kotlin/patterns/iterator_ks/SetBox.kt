package patterns.iterator_ks

import java.util.ArrayList
import java.util.Collections

/**
 * Concrete Aggregate : set implementation of IBox.
 */
class SetBox<T> : IBox<T> where T : Comparable<T>{
    private var _values = ArrayList<T>()

    override fun get(index : Int) = _values.getOrNull(index)

    override fun add(value : T) {
        if (!_values.contains(value))
            _values.add(value)
        Collections.sort(_values)
    }

    // ITERATOR PATTERN
    override fun iterator() : Iterator<T> = SetBoxIterator(this)

    fun size() = _values.size()
}