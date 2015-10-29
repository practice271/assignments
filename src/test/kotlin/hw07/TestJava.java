package hw07;

/* OrderedLists tests made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

import hw07.javaClasses.OrderedArray;
import hw07.javaClasses.OrderedList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestJava {
    private OrderedArray<String> arr = new OrderedArray<String>(1, "500");
    private OrderedList<String>  list= new OrderedList<String>( "500", null);
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
    public void addGetArray() {
        addFirstElem();
        assertEquals("500", arr.get(0));
        assertEquals("600", arr.get(1));
    }
    @Test
    public void addGetList() {
        addFirstElem();
        list.add("9");
        assertEquals("500", list.get(0));
        assertEquals("600", list.get(1));
        assertEquals("9", list.get(2));
    }
    @Test
    public void setArray () {
        addFirstElem();
        arr.set(1,"00");
        assertEquals("500", arr.get(0));
        assertEquals("00", arr.get(1));
    }
    @Test
    public void setList1 () {
        // 500 600
        addFirstElem();

        list.set(1,"0");
        assertEquals("500", list.get(0));
        assertEquals("0", list.get(1));
    }
    @Test
    public void setList2 () {
        // 500 600 9
        addFirstElem();
        list.add("9");
        list.set(1,"0");
        assertEquals("500", list.get(0));
        assertEquals("0", list.get(1));
        assertEquals("9", list.get(2));
    }
    // 500 600 9 10
    private void init(){
        addFirstElem();
        arr.add("9");
        list.add("9");
        arr.add("10");
        list.add("10");
    }
    @Test
    public void removeArrayFirst () {
        init();
        arr.removeAt(0);
        assertEquals("600", arr.get(0));
        assertEquals("9", arr.get(1));
        assertEquals("10", arr.get(2));
        assertEquals(3, arr.size());
    }
    @Test
    public void removeArrayMiddle () {
        init();
        arr.removeAt(1);
        assertEquals("500", arr.get(0));
        assertEquals("9", arr.get(1));
        assertEquals("10", arr.get(2));
        assertEquals(3, arr.size());
    }
    @Test
    public void removeArrayLast () {
        init();
        arr.removeAt(3);
        assertEquals("500", arr.get(0));
        assertEquals("600", arr.get(1));
        assertEquals("9", arr.get(2));
        assertEquals(3, arr.size());
    }
    @Test
    public void removeListFirst () {
        init();
        list.removeAt(0);
        assertEquals("600", list.get(0));
        assertEquals("9", list.get(1));
        assertEquals("10", list.get(2));
        assertEquals(3, list.size());
    }
    @Test
    public void removeListMiddle () {
        init();
        list.removeAt(1);
        assertEquals("500", list.get(0));
        assertEquals("9", list.get(1));
        assertEquals("10", list.get(2));
        assertEquals(3, list.size());
    }
    @Test
    public void removeListLast () {
        init();
        list.removeAt(3);
        assertEquals("500", list.get(0));
        assertEquals("600", list.get(1));
        assertEquals("9", list.get(2));
        assertEquals(3, list.size());
    }
    @Test
    public void clearArray () {
        init();
        arr.clear();
        assertTrue(arr.isEmpty());
        arr.add ("0");
        assertEquals("0", arr.get(0));
        assertEquals(1, arr.size());
    }
    @Test
    public void clearList () {
        init();
        list.clear();
        assertTrue(list.isEmpty());
        list.add ("0");
        assertEquals("0", list.get(0));
        assertEquals(1, list.size());
    }
    @Test
    public void reverseArray () {
        init();
        arr.reverse();
        assertEquals("10", arr.get(0));
        assertEquals("9", arr.get(1));
        assertEquals("600", arr.get(2));
        assertEquals("500", arr.get(3));
        assertEquals(4, arr.size());
    }
    @Test
    public void reverseList () {
        init();
        list.reverse();
        assertEquals("10", list.get(0));
        assertEquals("9", list.get(1));
        assertEquals("600", list.get(2));
        assertEquals("500", list.get(3));
        assertEquals(4, list.size());
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
        list.set(3, "99");
        assertEquals(1, list.compareTo(arr));
    }
    public void compare2 () {
        init();
        arr.set(3, "99");
        assertEquals(1, list.compareTo(arr));
    }
    public void compare3 () {
        init();
        arr.add("99");
        assertEquals(-1, list.compareTo(arr));
    }
    public void hashCode1 () {
        init();
        assertTrue(arr.hashCode() == list.hashCode());
    }
}
