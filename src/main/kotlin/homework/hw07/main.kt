package homework.hw07

import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val list = ArrayOrderedList<String>()
    assertEquals(0, list.size()) // for clean testing
    val s = arrayOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaab", "ab", "b", "bbb", "kotlin", "test")
    list.add(s[0])
    list.add(s[10])
    list.add(s[1])
    list.add(s[9])
    list.add(s[5])
    list.add(s[7])
    list.add(s[3])
    list.add(s[8])
    list.add(s[4])
    list.add(s[2])
    list.add(s[6])
    assertEquals(11, list.size())
    for (i in 0..10) {
        assertEquals(s[i], list.get(i))
    }
}