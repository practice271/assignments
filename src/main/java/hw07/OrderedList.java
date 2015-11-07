package hw07;

import java.util.Iterator;

public abstract class OrderedList <A extends Comparable<? super A>>
        implements Comparable<OrderedList<? extends A>>, Iterable<A> {
    protected boolean isAscending;
    abstract public A getVal(int index);
    abstract public void addVal(A val);
    abstract public void delVal(int index);

    abstract public int getSize();

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedList)) return false;

        OrderedList otherList = (OrderedList) other;

        Iterator<A> thisIter  = iterator();
        Iterator<A> otherIter = otherList.iterator();

        boolean res = true;
        int thisSize = getSize();
        if (thisSize!= otherList.getSize()) return false;

        for (int i = 0; i < thisSize; i++) {
            A curThis = thisIter.next();
            A curOther = otherIter.next();
            if (curThis == null && curOther != null) return false;
            if (curThis != null && curOther == null) return false;
            if (!(curThis == null && curOther == null))
                res = res && curThis.equals(curOther);
            //if both are nulls, we shouldn't do anything.
        }
        return res;
    }

    @Override
    public int compareTo(OrderedList<? extends A> other) {
        int thisSize = getSize();
        int minSize = Math.min(thisSize, other.getSize());
        OrderedList otherList = (OrderedList) other;

        Iterator<A> thisIter   = iterator();
        Iterator<A> otherIter  = otherList.iterator();

        for (int i = 0; i < minSize; i++) {
            int curCompare = thisIter.next().compareTo(otherIter.next());
            if (curCompare != 0) return curCompare;
        }
        if (thisSize < other.getSize()) return -1;
        if (thisSize > other.getSize()) return 1;
        return 0;
    }
}
