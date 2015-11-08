package hw07;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList<T extends Comparable<T>>
        extends AbstractCollection<T>
        implements Ordered<T> {

    private class ListIterator implements Iterator<T> {
        public ListIterator() {
            current = 0;
        }

        public int current = 0;

        @Override
        public boolean hasNext() {
            return current >= size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return array[current++];
        }

        @Override
        public void remove() {
            return;
        }
    }

    private T[] array = (T[]) new Comparable[8];
    private int size = 0, cap = 8;

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public boolean add(T t) {
        if (size == cap) {
            cap *= 2;
            T[] newa = (T[]) new Comparable[cap];
            for (int i = 0; i < size; i++)
                newa[i] = array[i];
            array = newa;
        }
        array[size++] = t;
        return true;
    }

}
