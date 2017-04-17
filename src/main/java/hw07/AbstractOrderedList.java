package hw07;


import java.util.Iterator;

/**
 * Created by Antropov Igor on 31.10.2015.
 */


public abstract class AbstractOrderedList<E extends Comparable<? super E>>
        implements Comparable<AbstractOrderedList<? extends E>>, Iterable<E> {

    public boolean equals(Object other) {
        if (!(other instanceof AbstractOrderedList)) {
            return false;
        }
        AbstractOrderedList<E> otherList = (AbstractOrderedList) other;
        int size = size();
        if (otherList.size() != size) return false;
        Iterator<E> j = otherList.iterator();
        E val2 = j.next();
        for (Iterator<E> i = this.iterator(); i.hasNext(); ) {
            if (i.next() != val2) return false;
            if (j.hasNext()) val2 = j.next();
        }
        return true;
    }


    public int hashCode() {
        int res = 0;
        for (int i = 0; i < this.size(); i++) {
            res = res * 31 + this.get(i).hashCode();
        }
        return res;
    }

    public int compareTo(AbstractOrderedList<? extends E> other) {
        int size = size();
        int otherSize = other.size();
        if (size > otherSize) return 1;
        else if (size < otherSize) return -1;
        double compare;
        Iterator<E> j = other.iterator();
        E val2 = j.next();
        for (Iterator<E> i = this.iterator(); i.hasNext(); ) {
            E val1 = i.next();
            compare = val1.compareTo(val2);
            if (compare > 0) {
                return 1;
            }
            if (compare < 0) {
                return -1;
            }
            if (j.hasNext()) val2 = j.next();
        }
        return 0;
    }

    public abstract Iterator iterator();

    public abstract int size();

    public abstract E get(int index);

    public abstract void add(E elem);
}
