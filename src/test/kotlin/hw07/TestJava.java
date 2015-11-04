package hw07;

/* OrderedLists tests made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

import hw07.javaClasses.OrderedArray;
import hw07.javaClasses.OrderedList;
import hw07.javaClasses.OutOfBoundException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestJava {
    private OrderedArray<String> arr = new OrderedArray<String>(1, "500");
    private OrderedList<String>  list= new OrderedList<String>( "500");
    @Test
    public void isEmpty1() {
        assertFalse(arr.isEmpty());
        assertFalse(list.isEmpty());
    }
    @Test
    public void size1() {
        assertEquals(1, arr.size());
        assertEquals(1, list.size());
    }
    private void addFirstElem(){
        arr.add("600");
        list.add("600");
    }
    @Test
    public void addSize() {
        addFirstElem();
        assertEquals(2, arr.size());
        assertEquals(2, list.size());
    }
    @Test
    public void addGetArray() throws OutOfBoundException {
        addFirstElem();
        arr.add("900");
        assertEquals("500", arr.get(0));
        assertEquals("600", arr.get(1));
        assertEquals("900", arr.get(2));
    }
    @Test
    public void addGetArray2() throws OutOfBoundException {
        addFirstElem();
        arr.add("4");
        assertEquals("4", arr.get(0));
        assertEquals("500", arr.get(1));
        assertEquals("600", arr.get(2));
    }
    @Test
    public void addGetArray3() throws OutOfBoundException {
        addFirstElem();
        arr.add("55");
        assertEquals("500", arr.get(0));
        assertEquals("55", arr.get(1));
        assertEquals("600", arr.get(2));
    }
    @Test
    public void addGetList() throws OutOfBoundException {
        addFirstElem();
        list.add("900");
        assertEquals("500", list.get(0));
        assertEquals("600", list.get(1));
        assertEquals("900", list.get(2));
    }
    @Test
    public void addGetList2() throws OutOfBoundException {
        addFirstElem();
        list.add("4");
        assertEquals("4", list.get(0));
        assertEquals("500", list.get(1));
        assertEquals("600", list.get(2));
    }
    @Test
    public void addGetList3() throws OutOfBoundException {
        addFirstElem();
        list.add("55");
        assertEquals("500", list.get(0));
        assertEquals("55", list.get(1));
        assertEquals("600", list.get(2));
    }
    // 500 600 9 10
    private void init(){
        addFirstElem();
        arr.add("9");
        list.add("9");
        arr.add("99");
        list.add("99");
    }
    @Test
    public void removeArrayFirst () throws OutOfBoundException {
        init();
        arr.removeAt(0);
        assertEquals("600", arr.get(0));
        assertEquals("9", arr.get(1));
        assertEquals("99", arr.get(2));
        assertEquals(3, arr.size());
    }
    @Test
    public void removeArrayMiddle () throws OutOfBoundException {
        init();
        arr.removeAt(1);
        assertEquals("500", arr.get(0));
        assertEquals("9", arr.get(1));
        assertEquals("99", arr.get(2));
        assertEquals(3, arr.size());
    }
    @Test
    public void removeArrayLast () throws OutOfBoundException {
        init();
        arr.removeAt(3);
        assertEquals("500", arr.get(0));
        assertEquals("600", arr.get(1));
        assertEquals("9", arr.get(2));
        assertEquals(3, arr.size());
    }
    @Test
    public void removeListFirst () throws OutOfBoundException {
        init();
        list.removeAt(0);
        assertEquals("600", list.get(0));
        assertEquals("9", list.get(1));
        assertEquals("99", list.get(2));
        assertEquals(3, list.size());
    }
    @Test
    public void removeListMiddle () throws OutOfBoundException {
        init();
        list.removeAt(1);
        assertEquals("500", list.get(0));
        assertEquals("9", list.get(1));
        assertEquals("99", list.get(2));
        assertEquals(3, list.size());
    }
    @Test
    public void removeListLast () throws OutOfBoundException {
        init();
        list.removeAt(3);
        assertEquals("500", list.get(0));
        assertEquals("600", list.get(1));
        assertEquals("9", list.get(2));
        assertEquals(3, list.size());
    }
    @Test
    public void clearArray () throws OutOfBoundException {
        init();
        arr.clear();
        assertTrue(arr.isEmpty());
        arr.add ("0");
        assertEquals("0", arr.get(0));
        assertEquals(1, arr.size());
    }
    @Test
    public void clearList () throws OutOfBoundException {
        init();
        list.clear();
        assertTrue(list.isEmpty());
        list.add ("0");
        assertEquals("0", list.get(0));
        assertEquals(1, list.size());
    }
    @Test
    public void equal () {
        init();
        assertTrue(list.equals(arr));
    }
    public void compare0 () {
        init();
        assertEquals(0, list.compareTo(arr));
    }
    public void compare1 () {
        init();
        list.add("99");
        assertEquals(1, list.compareTo(arr));
    }
    public void compare2 () {
        init();
        arr.add("99");
        assertEquals(-1, list.compareTo(arr));
    }
    public void compare3 () {
        init();
        arr.add("0");
        assertEquals(1, list.compareTo(arr));
    }
    public void hashCode1 () {
        init();
        assertTrue(arr.hashCode() == list.hashCode());
    }
}
