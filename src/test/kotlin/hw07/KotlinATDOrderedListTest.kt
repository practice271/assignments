package hw07;

import org.junit.Test;

public class KotlinATDOrderedListTest : OrderedListTestingClass() {

    override public fun getOrderedList() : KotlinATDOrderedList<Int> {
        return KotlinATDOrderedList(arrayOf())
    }

    @Test public fun TestArrayOrderedList() {
        runTests()
    }
}