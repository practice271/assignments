package hw07;

import java.util.Iterator;

/**
 * Created by Mikhail on 01.11.2015.
 */
public class ArrayOrderedList<A extends Comparable<A>>
                                extends AOrderedList<A> {
    private final int SIZE = 10;
    private A[] items;
    private int size;

    public ArrayOrderedList() {
        items = (A[]) new Comparable[SIZE];
        size = 0;
    }

    @Override
    public void add(A item) {
        if (size + 1 >= items.length) {
            A[] newItems = (A[]) new Comparable[items.length + SIZE];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].compareTo(item) > 0) {
                items[i+1] = items[i];
            } else {
                items[i+1] = item;
                size++;
                return;
            }
        }
        items[0] = item;
        size++;
        return;
    }

    @Override
    public A get(int index) {
        return items[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(A item) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<A> iterator() {
        return new Iterator<A>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public A next() {
                index++;
                return items[index - 1];
            }

            @Override
            public void remove() {}
        };
    }
}
