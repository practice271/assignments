package hw07;

import java.util.AbstractCollection;
import java.util.Iterator;

public abstract class OrderedList<T extends Comparable<T>>
        extends AbstractCollection<T>
        implements Ordered<T> {

    @Override
    public int compareTo(Ordered<T> obj) {
        Iterator<T> itA = iterator(), itB = obj.iterator();
        while (itA.hasNext() && itB.hasNext()) {
            T a = itA.next(), b = itB.next();
            int compareResult = a.compareTo(b);
            if (compareResult != 0)
                return compareResult;
        }

        if (!itA.hasNext() && !itB.hasNext()) {
            return 0;
        } else if (!itA.hasNext()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Ordered<T> x) {
        return (compareTo(x) == 0);
    }

    @Override
    public int hashCode() {
        int h = 1777;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            h = h * 31 + it.next().hashCode();
        }
        return h;
    }
}
