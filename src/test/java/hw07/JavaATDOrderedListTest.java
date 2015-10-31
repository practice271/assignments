package hw07;

import org.junit.Test;

public class JavaATDOrderedListTest extends OrderedListTestingClass {

    public JavaOrderedList getOrderedList() {
        return new JavaATDOrderedList<Integer>();
    }

    @Test
    public void TestArrayOrderedList() {
        runTests();
    }
}