package homework.hw07

import homework.hw07.javaimp.ArrayOrderedList
import homework.hw07.javaimp.LinkedOrderedList
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val list = LinkedOrderedList<String>()
    assertEquals(0, list.size())
    val s = arrayOf("a", "aa", "aaa")
    list.add(s[1])
    list.add(s[0])
    list.add(s[2])
    assertEquals(3, list.size())
    list.removeAt(5)
    assertEquals(3, list.size())
    list.removeAt(1)
    assertEquals(s[0], list.get(0))
    assertEquals(s[2], list.get(1))
}