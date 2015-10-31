package hw07;

import org.junit.Test;

public class JavaArrayOrderedListTest extends OrderedListTestingClass {

    public JavaOrderedList getOrderedList() {
        return new JavaArrayOrderedList<Integer>();
    }

    @Test public void TestArrayOrderedList() {
        runTests();
    }
}