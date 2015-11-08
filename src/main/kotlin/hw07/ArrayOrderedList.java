package hw07;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayOrderedList<T extends Comparable<T>>
        extends OrderedList<T> {

    private class ListIterator implements Iterator<T> {
        public ListIterator() {
            current = 0;
        }

        public int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return array[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private T[] array = (T[]) new Comparable[8];
    private int size = 0, cap = 8;

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
        int place = 0;
        while (place < size && array[place].compareTo(t) < 0)
            place++;
        for (int i = size - 1; i >= place; i--)
            array[i + 1] = array[i];
        array[place] = t;
        size++;
        return true;
    }

}
