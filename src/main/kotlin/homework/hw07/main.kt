package homework.hw07

import homework.hw07.javaimp.ArrayOrderedList
import homework.hw07.javaimp.LinkedOrderedList
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    //val list = LinkedOrderedList<String>()
    val list = LinkedOrderedList<Int>()
    assertEquals(0, list.size()) // for clean testing
    val s = arrayOf(0, 1, 2)
    list.add(s[1]) // to empty
    list.add(s[2]) // to tail
    list.add(s[0]) // to head
    assertEquals(3, list.size())
    for (i in 0..2) {
        assertEquals(s[i], list.get(i))
    }

    /*assertEquals(0, list.size()) // for clean testing
    val s = arrayOf("a", "aa", "aaa", "aaa", "aaaaa", "aaab", "ab", "b", "bbb", "kotlin", "test")
    list.add(s[1])
    list.add(s[9])
    list.add(s[5])
    list.add(s[7])
    list.add(s[3])
    list.add(s[8])
    list.add(s[4])
    list.add(s[2])
    list.add(s[6])
    list.add(s[0]) // to head
    list.add(s[10]) // to tail
    assertEquals(11, list.size())
    for (i in 0..10) {
        assertEquals(s[i], list.get(i))
    }*/

    // remove
    /*assertEquals(0, list.size())
    val s = arrayOf("a", "aa", "aaa")
    list.add(s[1])
    list.add(s[0])
    list.add(s[2])
    assertEquals(3, list.size())
    list.removeAt(5)
    assertEquals(3, list.size())
    list.removeAt(1)
    assertEquals(s[0], list.get(0))
    assertEquals(s[2], list.get(1))*/
}