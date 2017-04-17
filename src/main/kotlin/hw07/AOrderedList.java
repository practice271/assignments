package hw07;

/**
 * Created by Mikhail on 01.11.2015.
 */
import java.util.Iterator;

public abstract class AOrderedList<A extends Comparable<? super A>>
                implements Comparable<AOrderedList<? extends A>>, Iterable<A> {
    public abstract A get(int index);
    public abstract void add(A item);
    public abstract int size();
    public abstract boolean contains(A item);

    @Override
    public int compareTo(AOrderedList<? extends A> other) {
        if (size() > other.size()) return 1;
        if (size() < other.size()) return -1;
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AOrderedList)) return false;
        AOrderedList otherOrderedList = (AOrderedList) other;
        Iterator iterateThis = iterator();
        Iterator iterateOther = ((AOrderedList) other).iterator();
        if (size() != otherOrderedList.size()) return false;
        while (iterateThis.hasNext()) {
            if (iterateThis.next() != iterateOther.next()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (A item : this) res = res * 23 + item.hashCode();
        return res;
    }
}
