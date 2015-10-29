package patterns.iterator_ks

@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
/**
 * Abstract Aggregate : some collection.
 */
interface IBox<T> {
    operator fun get(index : Int) : T?

    fun add(value : T)

    /**
     * Factory method from Iterator pattern.
     */
    operator fun iterator() : Iterator<T>
}