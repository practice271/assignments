package hw07;

import org.junit.Test;

public class KotlinArrayOrderedListTest : OrderedListTestingClass() {

    override public fun getOrderedList() : KotlinArrayOrderedList<Int> {
        return KotlinArrayOrderedList(arrayOf())
    }

    @Test public fun TestArrayOrderedList() {
        runTests()
    }
}