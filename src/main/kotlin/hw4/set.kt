package hw4

interface Set<T> {
    fun insert(x: T)
    fun remove(x: T)
    fun find(x: T): Boolean
    fun intersect(s: Set<T>): Set<T>
    fun union(s: Set<T>): Set<T>
    fun forAll(f: (T) -> Unit): Unit
}