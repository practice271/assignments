package hw4

/* return container itself after modification
   for consistence with functional style */

interface Set<T> {
    fun insert(x: T)
    fun remove(x: T)
    fun find(x: T): Boolean
}