package homework.hw07

import homework.hw07.javaimp.AbstractOrderedList
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Hw07Test {
    companion object {
        fun testAdd(list : AbstractOrderedList<String>) {
            assertEquals(0, list.size()) // for clean testing
            val s = arrayOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaab", "ab", "b", "bbb", "kotlin", "test")
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
            }
        }

        fun testRemove(list : AbstractOrderedList<String>) {
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

        fun testCompare(list1 : AbstractOrderedList<String>, list2 : AbstractOrderedList<String>) {
            assertEquals(0, list1.size())
            assertEquals(0, list2.size())
            assertEquals(0, list1.compareTo(list2))
            list1.add("a")
            list2.add("a")
            list2.add("a")
            assertTrue { list1.compareTo(list2) < 0 }
            list1.add("b")
            assertTrue { list1.compareTo(list2) > 0 }
        }

        fun testEquals(list1 : AbstractOrderedList<String>, list2 : AbstractOrderedList<String>) {
            assertEquals(0, list1.size())
            assertEquals(0, list2.size())
            list1.add("aa")
            list1.add("a")
            list2.add("a")
            list2.add("aa")
            assertTrue { list1.equals(list2) }
            assertTrue { list1.hashCode() == list2.hashCode() }
        }
    }
}