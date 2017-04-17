package hw07;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class hw07Tests {
    OrdList<Integer> list1 = new OrdList<Integer>();
    @Test
    public void sizeCheck() {
        list1.set(100);
        list1.set(300);
        list1.set(200);
        assertEquals((long) list1.size(), 3);
    }
    OrdList<Integer> list2 = new OrdList<Integer>();
    @Test
    public void getSecond() {
        list2.set(1);
        list2.set(3);
        list2.set(2);
        assertEquals((long) list2.get(1), 2);
    }

    OrdList<Integer> listToCompare1 = new OrdList<Integer>();
    OrdList<Integer> listToCompare2 = new OrdList<Integer>();
    @Test
    public void equalsCheck() {
        listToCompare1.set(1);
        listToCompare1.set(2);
        listToCompare1.set(3);
        listToCompare2.set(1);
        listToCompare2.set(3);
        listToCompare2.set(2);
        assertEquals(listToCompare1.equals(listToCompare2), true);
    }

    OrdArray<Integer> array1 = new OrdArray<Integer>();
    @Test
    public void sizeCheckArray() {
        array1.set(100);
        array1.set(300);
        array1.set(200);
        assertEquals((long) array1.size(), 3);
    }
    OrdArray<Integer> array2 = new OrdArray<Integer>();
    @Test
    public void getSecondArray() {
        array2.set(1);
        array2.set(3);
        array2.set(2);
        assertEquals((long) array2.get(1), 2);
    }

    OrdArray<Integer> arrayToCompare1 = new OrdArray<Integer>();
    OrdArray<Integer> arrayToCompare2 = new OrdArray<Integer>();
    @Test
    public void equalsCheckArrays() {
        arrayToCompare1.set(1);
        arrayToCompare1.set(2);
        arrayToCompare1.set(3);
        arrayToCompare2.set(3);
        arrayToCompare2.set(1);
        arrayToCompare2.set(2);
        assertEquals(arrayToCompare1.equals(arrayToCompare2), true);
    }
}
