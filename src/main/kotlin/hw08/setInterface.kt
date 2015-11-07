package hw08

interface SetInterface: Iterable<Int>{
    fun insert(key: Int): SetInterface
    fun toList(): List<Int>
    fun search(key: Int): Boolean
    fun remove(key: Int): SetInterface
    fun union(set: SetInterface): SetInterface
    fun intersection(set: SetInterface): SetInterface
}