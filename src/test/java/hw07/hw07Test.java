package hw07;

/**
 * Created by Antropov Igor on 31.10.2015.
 */

import org.junit.*;

public class hw07Test extends Assert {
    private OrderedArray<Character> testArray = new OrderedArray<Character>(1, 'z');
    private OrderedList<Character> testList = new OrderedList<Character>('z');
    private OrderedList<Character> wrongList = new OrderedList<Character>('X');
    private OrderedArray<Character> wrongArray = new OrderedArray<Character>(1, 'X');

    @Test
    public void orderedArrayTest() {
        testArray.add('a');
        testArray.add('c');
        testArray.add('w');
        testArray.add('f');
        testArray.add('s');
        testArray.add('d');
        assertEquals('a', (Object) testArray.get(0));
        assertEquals('d', (Object) testArray.get(2));
        assertEquals('z', (Object) testArray.get(6));
        assertEquals(7, testArray.size());
    }
    @Test
    public void orderedListTest() {
        testList.add('a');
        testList.add('c');
        testList.add('w');
        testList.add('f');
        testList.add('s');
        testList.add('d');
        assertEquals((Object) testList.get(0), 'a');
        assertEquals((Object) testList.get(2), 'd');
        assertEquals((Object) testList.get(6), 'z');
        assertEquals(testList.size(), 7);
    }
    @Test
    public void hashTest(){
        assertEquals(testArray.hashCode(), testList.hashCode());
        assertNotEquals(testArray.hashCode(), wrongArray.hashCode());
        assertNotEquals(testArray.hashCode(), wrongList.hashCode());
        assertNotEquals(testList.hashCode(), wrongArray.hashCode());
        assertNotEquals(testList.hashCode(), wrongList.hashCode());
    }
    @Test
    public void equalsTest(){
        boolean answer1 = testArray.equals(testList);
        boolean answer2 = testList.equals(testArray);
        boolean answer3 = testArray.equals(wrongArray);
        boolean answer4 = testArray.equals(wrongList);
        boolean answer5 = testList.equals(wrongArray);
        boolean answer6 = testList.equals(wrongList);
        assertEquals(true, answer1);
        assertEquals(true, answer2);
        assertEquals(false,answer3);
        assertEquals(false,answer4);
        assertEquals(false,answer5);
        assertEquals(false,answer6);
    }
    @Test
    public void compareToTest(){
        int answer1 = testArray.compareTo(testList);
        int answer2 = testList.compareTo(testArray);
        int answer3 = testArray.compareTo(wrongArray);
        int answer4 = testArray.compareTo(wrongList);
        int answer5 = testList.compareTo(wrongArray);
        int answer6 = testList.compareTo(wrongList);
        int answer7 = wrongArray.compareTo(testArray);
        int answer8 = wrongList.compareTo(testArray);
        assertEquals(0, answer1);
        assertEquals(0, answer2);
        assertEquals(1, answer3);
        assertEquals(1, answer4);
        assertEquals(1, answer5);
        assertEquals(1, answer6);
        assertEquals(-1, answer7);
        assertEquals(-1, answer8);
    }
}