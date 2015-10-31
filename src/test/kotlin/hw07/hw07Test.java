package hw07;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class hw07Test {
    private ArrayOrdList<String> arr = new ArrayOrdList<>(1, "zebra");
    private ArrayOrdList<String> arr1 = new ArrayOrdList<>(1, "zebra");

    @Test
    public void get(){
        arr.add("lion");
        arr.add("monkey");
        arr.add("panda");
        assertEquals("lion", arr.get(1));
    }
    @Test
    public void size(){
        arr.add("lion");
        arr.add("monkey");
        arr.add("panda");
        assertEquals(4, arr.size());
    }
    @Test
    public void removeBegin(){
        arr.add("cat");
        arr.print();
        arr.remove(0);
        System.out.println("remove from begin");
        arr.print();
    }
    @Test
    public void removeEnd(){
        arr.add("lion");
        arr.add("monkey");
        arr.add("panda");
        arr.print();
        arr.remove(3);
        System.out.println("remove from end");
        arr.print();
    }
    @Test
    public void compareTo1(){
        assertEquals(0, arr.compareTo(arr1));
    }
    @Test
    public void compareTo2(){
        arr1.add("cat");
        arr1.add("elephant");
        arr1.add("cow");
        arr1.add("horse");
        arr1.add("lion");
        arr1.add("monkey");
        assertEquals(-1, arr.compareTo(arr1));
    }
    @Test
    public void equals1(){
        assertEquals(true, arr.equals(arr1));
    }
    @Test
    public void equals2(){
        arr1.add("elephant");
        assertEquals(false, arr.equals(arr1));
    }
    @Test
    public void hashcode(){
        arr.add("monkey");
        arr.add("panda");
        arr.add("mockingbird");
        assertEquals(1170318980, arr.hashcode());
    }
}
