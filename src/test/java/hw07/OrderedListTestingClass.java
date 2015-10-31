package hw07;

import static org.junit.Assert.*;

public abstract class OrderedListTestingClass {

    abstract public IOrderedList getOrderedList();

    public void runTests() {
        addGetTest();
        lengthTest();
        toArrayTest();
        equalsOrderedListTest();
        compareToOrderedListTest();
        removeOrderedListTest();
    }

    public void addGetTest() {
        IOrderedList<Integer> array = getOrderedList();
        assertEquals(null, array.getByIndex(-1));
        assertEquals(null, array.getByIndex(0));
        assertEquals(null, array.getByIndex(1));

        array.add(5);
        assertTrue(array.getByIndex(0) == 5);
        assertEquals(null, array.getByIndex(1));

        array.add(7);
        array.add(3);
        Integer[] resultArray1 = {3, 5, 7};
        assertArrayEquals(resultArray1, array.toArray());

        array = getOrderedList();
        Integer[] res = new Integer[101];
        for (int i = 100; i > -1; i--) {
            array.add(i);
            res[i] = i;
        }
        assertArrayEquals(res, array.toArray());
    }

    public void lengthTest() {
        IOrderedList<Integer> array = getOrderedList();
        assertEquals(0, array.getLength());

        array.add(1);
        assertEquals(1, array.getLength());

        for (Integer i = 0; i < 100; i++)
            array.add(i);
        assertEquals(101, array.getLength());
    }

    public void toArrayTest() {
        IOrderedList<Integer> array = getOrderedList();
        Integer[] array1 = {};
        assertArrayEquals(array1, array.toArray());

        Integer[] array2 = {1};
        array.add(1);
        assertArrayEquals(array2, array.toArray());

        Integer[] array3 = {0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (Integer i = 0; i < 10; i++)
            array.add(i);
        assertArrayEquals(array3, array.toArray());
    }

    public void equalsOrderedListTest() {
        IOrderedList<Integer> array1 = getOrderedList();
        IOrderedList<Integer> array2 = new JavaArrayOrderedList();
        IOrderedList<Integer> array3 = new JavaATDOrderedList<Integer>();
        Integer[] arr = {};
        IOrderedList<Integer> array4 = new KotlinArrayOrderedList<Integer>(arr);
        IOrderedList<Integer> array5 = new KotlinATDOrderedList<Integer>(arr);
        for (Integer i = 0; i < 10; i++) {
            array1.add(i);
            array2.add(i);
            array3.add(i);
            array4.add(i);
            array5.add(i);
        }
        IOrderedList<Integer> array6 = getOrderedList();
        for (Integer i = 0; i < 5; i++)
            array6.add(i);
        assertEquals(false, array1.equals(1));
        assertEquals(false, array1.equals("1"));
        assertEquals(true, array1.equals(array2));
        assertEquals(true, array1.equals(array3));
        assertEquals(true, array1.equals(array4));
        assertEquals(true, array1.equals(array5));
        assertEquals(false, array1.equals(array6));

    }

    public void compareToOrderedListTest() {
        IOrderedList<Integer> array1 = getOrderedList();
        IOrderedList<Integer> array2 = new JavaATDOrderedList<Integer>();
        IOrderedList<Integer> array3 = new JavaArrayOrderedList<Integer>();
        Integer[] arr = {};
        IOrderedList<Integer> array4 = new KotlinArrayOrderedList<Integer>(arr);
        IOrderedList<Integer> array5 = new KotlinATDOrderedList<Integer>(arr);
        assertEquals(0, array1.compareTo(array2));
        assertEquals(0, array1.compareTo(array3));

        array1.add(3);
        assertEquals(-1, array1.compareTo(array2));
        assertEquals(-1, array1.compareTo(array3));
        assertEquals(-1, array1.compareTo(array4));
        assertEquals(-1, array1.compareTo(array5));

        array2.add(1);
        array3.add(2);
        array4.add(1);
        array5.add(2);
        assertEquals(1, array1.compareTo(array2));
        assertEquals(1, array1.compareTo(array3));
        assertEquals(1, array1.compareTo(array4));
        assertEquals(1, array1.compareTo(array5));

        for (Integer i = 0; i < 10; i++) {
            array1.add(i);
            array2.add(i);
            array3.add(i);
            array4.add(i);
            array5.add(i);
        }
        assertEquals(1, array1.compareTo(array2));
        assertEquals(1, array1.compareTo(array3));
        assertEquals(1, array1.compareTo(array4));
        assertEquals(1, array1.compareTo(array5));
    }

    public void removeOrderedListTest() {
        IOrderedList<Integer> array = getOrderedList();
        assertEquals(false, array.removeAt(-1));
        assertEquals(false, array.removeAt(0));

        array.add(3);
        array.add(4);
        array.add(1);
        array.add(5);
        array.add(2);
        assertEquals(false, array.removeAt(5));
        assertEquals(true, array.removeAt(3));
        Integer[] res = {1, 2, 3, 5};
        assertArrayEquals(res, array.toArray());
    }
}