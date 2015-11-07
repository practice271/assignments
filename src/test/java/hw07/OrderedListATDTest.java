package hw07;

import org.junit.Assert;
import org.junit.Test;

public class OrderedListATDTest {

    @Test
    public void testGetValFromEmpty() throws Exception {
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (null, true);
        Assert.assertNull(a.getVal(0));
        Assert.assertNull(a.getVal(12));
    }

    @Test
    public void testGetValFromNotEmptyRigthIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        Assert.assertEquals(1, (int) a.getVal(0));
        Assert.assertEquals(4, (int) a.getVal(2));
    }

    @Test
    public void testGetValFromNotEmptyWrongIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        Assert.assertNull(a.getVal(-1));
        Assert.assertNull(a.getVal(3));
    }

    @Test
    public void testAddValToEmpty() throws Exception {
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (null, true);
        a.addVal(1);
        Assert.assertEquals((int) a.getVal(0), 1);
        Assert.assertNull(a.getVal(1));
    }

    @Test
    public void testAddValToNotEmpty() throws Exception {
        Integer[] b= {1, 2, 4};
        Integer[] res = {1, 2, 3, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        a.addVal(3);
        OrderedListATD<Integer> resList = new OrderedListATD<Integer> (res, true);
        Assert.assertEquals(a, resList);
    }

    @Test
    public void testAddValToFullArray() throws Exception {
        Integer[] b= {1, 4};
        Integer[] res = {-1, 1, 3, 4, 6};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        a.addVal(3);
        a.addVal(6);
        //actual testing is in the line below.
        a.addVal(-1);
        OrderedListATD<Integer> resList = new OrderedListATD<Integer> (res, true);
        Assert.assertEquals(a, resList);
    }

    @Test
    public void testDelValFromEmpty() throws Exception {
        //we basically test that we don't crash with some exception
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (null, true);
        a.delVal(0);
        a.delVal(-1);
        a.delVal(4);
    }

    @Test
    public void testDelValFromNotEmptyRightIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        Integer[] res = {1};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        OrderedListATD<Integer> resList = new OrderedListATD<Integer> (res, true);
        a.delVal(1);
        a.delVal(1);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testDelValFromNotEmptyWrongIndices() throws Exception {
        Integer[] b= {1, 2, 4};
        Integer[] res = {1, 2, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        OrderedListATD<Integer> resList = new OrderedListATD<Integer> (res, true);
        a.delVal(-1);
        a.delVal(4);
        a.delVal(17);
        Assert.assertTrue(a.equals(resList));
    }

    @Test
    public void testHashCodeForEqual() throws Exception {
        Integer[] in1 = {1, 2, 4};
        Integer[] in2 = {1, 2, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (in1, true);
        OrderedListATD<Integer> b = new OrderedListATD<Integer> (in2, true);
        Assert.assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testHashCodeForInequal() throws Exception {
        Integer[] in1 = {1, 2, 4};
        Integer[] in2 = {4, 3, 1};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (in1, true);
        OrderedListATD<Integer> b = new OrderedListATD<Integer> (in2, true);
        Assert.assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testEquals1() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        OrderedListATD<Integer> c = new OrderedListATD<Integer> (b, true);
        Assert.assertTrue(a.equals(c));
        a.addVal(12);
        Assert.assertFalse(a.equals(c));
        c.addVal(12);
        Assert.assertTrue(a.equals(c));
    }

    @Test
    public void testEqualsDifferentImplementations() throws Exception {
        Integer[] b= {1, 2, 4};
        OrderedListATD<Integer> a = new OrderedListATD<Integer> (b, true);
        OrderedListAr<Integer> c = new OrderedListAr<Integer> (b, true);
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
        OrderedListATD<Integer> c = new OrderedListATD<Integer> (a, true);
        OrderedListATD<Integer> descending = new OrderedListATD<Integer> (b, false);
        OrderedListATD<Integer> ascending  = new OrderedListATD<Integer> (b, true);
        Assert.assertFalse(c.equals(descending));
        Assert.assertTrue(c.equals(ascending));
    }

    @Test
    public void testEqualsDifferentVals() throws Exception {
        Integer[] a = {16, 2, -18};
        Integer[] b = {4, -1, 1};
        OrderedListATD<Integer> c = new OrderedListATD<Integer> (a, true);
        OrderedListATD<Integer> descending = new OrderedListATD<Integer> (b, false);
        OrderedListATD<Integer> ascending  = new OrderedListATD<Integer> (b, true);
        Assert.assertFalse(c.equals(descending));
        Assert.assertFalse(c.equals(ascending));
    }

    @Test
    public void testCompareSameSizeNotEqual() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1, 4};
        OrderedListATD<Integer> aList = new OrderedListATD<Integer> (a, true);
        OrderedListATD<Integer> bList = new OrderedListATD<Integer> (b, true);
        Assert.assertEquals(-1, aList.compareTo(bList));
    }

    @Test
    public void testCompareSameSizeEqual() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1, 2};
        OrderedListATD<Integer> aList = new OrderedListATD<Integer> (a, true);
        OrderedListATD<Integer> bList = new OrderedListATD<Integer> (b, true);
        Assert.assertEquals(0, aList.compareTo(bList));
    }

    @Test
    public void testCompareSameSizeEqualDifferentImplementations() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1, 2};
        OrderedListATD<Integer> aList = new OrderedListATD<Integer> (a, true);
        OrderedListAr<Integer> bList = new OrderedListAr<Integer> (b, true);
        Assert.assertEquals(0, aList.compareTo(bList));
    }

    @Test
    public void testCompareDiffSize() throws Exception {
        Integer[] a = {-1, 1, 2};
        Integer[] b = {-1, 1};
        OrderedListATD<Integer> aList = new OrderedListATD<Integer> (a, true);
        OrderedListATD<Integer> bList = new OrderedListATD<Integer> (b, true);
        Assert.assertEquals(1, aList.compareTo(bList));
    }
}
