package homework.hw07.javaimp;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

/**
 * Array implementation of AbstractOrderedList
 *
 * @author Kirill Smirenko, group 271
 */
public class ArrayOrderedList<T extends Comparable<T>> extends AbstractOrderedList<T> {
    private final int CLUSTER_SIZE = 10;

    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayOrderedList() {
        items = (T[]) new Comparable[CLUSTER_SIZE];
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(T value) {
        // expanding the array when needed
        if (size + 1 >= items.length) {
            T[] newItems = (T[]) new Comparable[items.length + CLUSTER_SIZE];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
        // finding a spot to put the new value
        int index = 0;
        while ((index < size) && (items[index].compareTo(value) <= 0)) {
            index++;
        }
        // inserting new value
        if ((size > 0) && (index < size)) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = value;
        size++;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
        return items[index];
    }

    @Override
    public void removeAt(int index) {
        if ((index <= 0) || (index >= size)) {
            return;
        }
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ArrayOrderedList)) {
            return false;
        }
        ArrayOrderedList otherList = (ArrayOrderedList) other;
        if (otherList.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (items[i] != otherList.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (int i = 0; i < size; i++) {
            h = 23 * h + items[i].hashCode();
        }
        return h;
    }
}