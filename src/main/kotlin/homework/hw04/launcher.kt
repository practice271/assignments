package homework.hw04

fun main(args : Array<String>) {
    val map1 = TreeMap<Int, String>()
    val map2 = TreeMap<Int, String>()
    map1.insert(0, "00")
    map1.insert(1, "11")
    map1.insert(3, "31")
    map1.insert(2, "21")
    // case when one is empty - intersection is always empty
    val list = map1.intersectWith(map2).toSortedList()
    println(list)
}