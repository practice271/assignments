package hw07;

import org.junit.Assert;
import org.junit.Test;

public class OrderedListArTest {

    @Test
    public void testGetValFromEmpty() throws Exception {
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (null, true);
        Assert.assertNull(a.getVal(0));
        Assert.assertNull(a.getVal(12));
    }

    @Test
    public void testGetValFromNotEmptyRigthIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        Assert.assertEquals(1, (int) a.getVal(0));
        Assert.assertEquals(4, (int) a.getVal(2));
    }

    @Test
    public void testGetValFromNotEmptyWrongIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        Assert.assertNull(a.getVal(-1));
        Assert.assertNull(a.getVal(3));
    }

    @Test
    public void testAddValToEmpty() throws Exception {
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (null, true);
        a.addVal(1);
        int size = a.getMinArraySize();
        Integer[] res = {1};
        OrderedListAr<Integer> resList = new OrderedListAr<Integer> (res, true);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testAddValToNotEmpty() throws Exception {
        Integer[] b= {1, 2, 4};
        Integer[] res = {1, 2, 3, 4};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        OrderedListAr<Integer> resList = new OrderedListAr<Integer> (res, true);
        a.addVal(3);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testAddValToFullArray() throws Exception {
        Integer[] b= {1, 4};
        Integer[] res = {-1, 1, 3, 4, 6};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        OrderedListAr<Integer> resList = new OrderedListAr<Integer> (res, true);
        a.addVal(3);
        a.addVal(6);
        //actual testing is in the line below.
        a.addVal(-1);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testDelValFromEmpty() throws Exception {
        //we basically test that we don't crash with some exception
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (null, true);
        a.delVal(0);
        a.delVal(-1);
        a.delVal(4);
    }

    @Test
    public void testDelValFromNotEmptyRightIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        Integer[] res = {1};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        OrderedListAr<Integer> resList = new OrderedListAr<Integer> (res, true);
        a.delVal(1);
        a.delVal(1);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testDelValFromNotEmptyWrongIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        Integer[] res = {1, 2, 4};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        OrderedListAr<Integer> resList = new OrderedListAr<Integer> (res, true);
        a.delVal(-1);
        a.delVal(4);
        a.delVal(17);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void testEquals1() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        OrderedListAr<Integer> c = new OrderedListAr<Integer> (b, true);
        Assert.assertTrue(a.equals(c));
        a.addVal(12);
        Assert.assertFalse(a.equals(c));
        c.addVal(12);
        Assert.assertTrue(a.equals(c));
    }

    @Test
    public void testEqualsDifferentImplementations() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListAr<Integer> a = new OrderedListAr<Integer> (b, true);
        OrderedListATD<Integer> c = new OrderedListATD<Integer> (b, true);
        Assert.assertTrue(a.equals(c));
        a.addVal(12);
        Assert.assertFalse(a.equals(c));
        c.addVal(12);
        Assert.assertTrue(a.equals(c));
    }

    @Test
    public void testEqualsSameValsDifferentOrders() throws Exception {
        Integer[] a = {1, 2, 4};
        Integer[] b = {4, 2, 1};
        OrderedListAr<Integer> c = new OrderedListAr<Integer> (a, true);
        OrderedListAr<Integer> descending = new OrderedListAr<Integer> (b, false);
        OrderedListAr<Integer> ascending  = new OrderedListAr<Integer> (b, true);
        Assert.assertFalse(c.equals(descending));
        Assert.assertTrue(c.equals(ascending));
    }

    @Test
    public void testEqualsDifferentVals() throws Exception {
        Integer[] a = {16, 2, -18};
        Integer[] b = {4, -1, 1};
        OrderedListAr<Integer> c = new OrderedListAr<Integer> (a, true);
        OrderedListAr<Integer> descending = new OrderedListAr<Integer> (b, false);
        OrderedListAr<Integer> ascending  = new OrderedListAr<Integer> (b, true);
        Assert.assertFalse(c.equals(descending));
        Assert.assertFalse(c.equals(ascending));
    }

    @Test
    public void testCompareSameSizeNotEqual() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1, 4};
        OrderedListAr<Integer> aList = new OrderedListAr<Integer> (a, true);
        OrderedListAr<Integer> bList = new OrderedListAr<Integer> (b, true);
        Assert.assertEquals(-1, aList.compareTo(bList));
    }

    @Test
    public void testCompareSameSizeEqual() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1, 2};
        OrderedListAr<Integer> aList = new OrderedListAr<Integer> (a, true);
        OrderedListAr<Integer> bList = new OrderedListAr<Integer> (b, true);
        Assert.assertEquals(0, aList.compareTo(bList));
    }

    @Test
    public void testCompareDiffSize() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1};
        OrderedListAr<Integer> aList = new OrderedListAr<Integer> (a, true);
        OrderedListAr<Integer> bList = new OrderedListAr<Integer> (b, true);
        Assert.assertEquals(1, aList.compareTo(bList));
    }
}
