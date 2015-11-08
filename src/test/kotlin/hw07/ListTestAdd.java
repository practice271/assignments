package hw07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class ListTestAdd {

    private static  int[][] data =
            {{1,2,3}, {1,3,2},
             {2,1,3}, {2,3,1},
             {3,1,2}, {3,2,1}};

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        List<Object[]> testData = new LinkedList();
        Integer[] expected = new Integer[3];
        for (int i = 0; i < 3; i++)
            expected[i] = new Integer(i + 1);
        for (int i = 0; i < data.length; i++) {
            Integer[] input = new Integer[3];
            for (int j = 0; j < data[i].length; j++)
                input[j] = new Integer(data[i][j]);
            Object[] pair = new Object[2];
            pair[0] = input;
            pair[1] = expected;
            testData.add(pair);
        }
        return testData;
    }

    private Integer[] input;
    private Integer[] expected;

    public ListTestAdd(Integer[] input, Integer[] expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testLinked() {
        OrderedList<Integer> ord = new LinkedOrderedList();
        for (int a : input)
            ord.add(a);
        Integer[] res = new Integer[ord.size()];
        ord.toArray(res);
        Assert.assertArrayEquals(expected, res);
    }

    @Test
    public void testArray() {
        OrderedList<Integer> ord = new ArrayOrderedList();
        for (int a : input)
            ord.add(a);
        Integer[] res = new Integer[ord.size()];
        ord.toArray(res);
        Assert.assertArrayEquals(expected, res);
    }
}
