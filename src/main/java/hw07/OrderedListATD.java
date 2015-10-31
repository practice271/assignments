package hw07;

import java.util.Arrays;

public class OrderedListATD <A extends Comparable<? super A>>
        extends OrderedList<A> {
    private List vals;
    private int size;
    private boolean isAscending;

    abstract protected class List {};
    protected class ListEmpty extends List {};

    protected class ListNotEmpty extends List {
        A head;
        List tail;

        ListNotEmpty(A[] data) {
            Arrays.sort(data, (a, b) -> isInOrderInt(a, b) * (-1));
            //we should sort in the wrong order 'cause pushing will reverse the array
            head = data[0];
            tail = new ListEmpty();
            for (int i = 1; i < data.length; i++) push(data[i]);
        }

        ListNotEmpty(A headIn, List tailIn) {
            head = headIn;
            tail = tailIn;
        }

        private void push(A val) {//without regard for demanded order.
            tail = new ListNotEmpty(head, tail);
            head = val;
        }
    }

    private int isInOrderInt(A fst, A snd) {
        if (isInOrder(fst, snd)) return -1;
        return 1;
    }

    OrderedListATD(A[] array, boolean ifAscending) {
        isAscending = ifAscending;
        if (array == null) {
            size = 0;
            vals = new ListEmpty();
        } else {
            size = array.length;
            vals = new ListNotEmpty(array);
        }
    }

    private boolean isInOrder(A fst, A snd) {
        return ((fst.compareTo(snd) <= 0) == isAscending);
        /*ascending == ascending
        descending != ascending
        ascending != descending
        descending == descending
         */
    }

    private ListNotEmpty addVal(A val, List l) {
        if (l instanceof OrderedListATD.ListEmpty) return (new ListNotEmpty(val, l));
        ListNotEmpty listGood = ((ListNotEmpty) l);
        if (isInOrder(val, listGood.head)) return (new ListNotEmpty(val, l));
        return (new ListNotEmpty(listGood.head, addVal(val, listGood.tail)));
    }

    @Override
    public void addVal(A val) {
        vals = addVal(val, vals);
        size++;
    }

    @Override
    public int getSize() {
        return size;
    }

    public A getFromDepth(int depth, List l) {
        if (l instanceof OrderedListATD.ListEmpty) return null;
        if (depth == 0) {
            return ((ListNotEmpty) l).head;
        }
        return getFromDepth(depth - 1, ((ListNotEmpty) l).tail);
    }

    @Override
    public A getVal(int index) {
        if (index >= size) return null;
        return getFromDepth(index, vals);
    }

    public List delFromDepth(int depth, List l) {
        if (l instanceof OrderedListATD.ListEmpty) return l;
        if (depth == 0) {
            return ((ListNotEmpty) l).tail;
        }
        //return delFromDepth(depth - 1, ((ListNotEmpty) l).tail);
        return new ListNotEmpty(((ListNotEmpty) l).head, delFromDepth(depth - 1, ((ListNotEmpty) l).tail));
    }

    @Override
    public void delVal(int index) {
        if (index < size && index > -1) {
            vals = delFromDepth(index, vals);
            size--;
        }
    }

    private int calcHashCode(int hash, List l) {
        if (l instanceof OrderedListATD.ListEmpty) return hash;
        ListNotEmpty listGood = ((ListNotEmpty) l);
        return (calcHashCode(hash * 31 + listGood.head.hashCode(), listGood.tail));
    }

    @Override
    public int hashCode() {
        return calcHashCode(1, vals);
    }

    private boolean checkEquality(List l, OrderedList other, int ind) {
        if (other.getVal(ind) == null) {
            return (l instanceof OrderedListATD.ListEmpty);
        }
        if (l instanceof OrderedListATD.ListEmpty) return false;
        ListNotEmpty listGood = ((ListNotEmpty) l);
        return listGood.head.equals(other.getVal(ind)) && checkEquality(listGood.tail, other, ind + 1);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedList)) return false;
        if (((OrderedList) other).getSize() == 0) {
            if (vals instanceof OrderedListATD.ListEmpty) return true;
            return false;
        }
        OrderedList otherList = (OrderedList) other;
        return size == otherList.getSize() && checkEquality(vals, otherList, 0);
    }

    @Override
    public int compareTo(OrderedList<? extends A> other) {
        int minSize = Math.min(size, other.getSize());
        for (int i = 0; i < minSize; i++) {
            int curCompare = getVal(i).compareTo(other.getVal(i));
            if (curCompare != 0) return curCompare;
        }
        if (size < other.getSize()) return -1;
        if (size > other.getSize()) return 1;
        return 0;
    }
}