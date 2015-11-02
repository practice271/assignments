/*
 * Homework 7 (27.10.2015)
 * Implementation of list using arrays.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07;

import java.util.Arrays;
import java.util.Iterator;

/** Implementation of IOrderedList<T> using arrays. */
public class ArrayList<T extends Comparable<T>> extends IOrderedList<T> {
    private T[] list = newArray(10);
    private int currentIndex = 0;

    /** Creates new array. */
    private T[] newArray(int size) {
        return (T[]) new Comparable[size];
    }

    /** Checks if the list is empty. */
    @Override
    public boolean isEmpty() {
        return (currentIndex == 0);
    }

    /** Returns length of the list. */
    @Override
    public int length() {
        return currentIndex;
    }

    /** Puts element into the list. */
    @Override
    public void push(T elem) {
        int size = list.length;
        currentIndex++;

        if (currentIndex > list.length) size += 10;
        T[] temp = newArray(size);

        int pos;
        for (pos = 0; pos < currentIndex - 1; pos++)
            if (elem.compareTo(list[pos]) > 0) break;

        if (pos > 0) System.arraycopy(list, 0, temp, 0, pos);
        temp[pos] = elem;
        System.arraycopy(list, pos, temp, pos + 1, currentIndex - 1 - pos);
        list = temp;
    }

    /** Returns element on given index. */
    @Override
    public T take(int index) {
        return list[index];
    }

    /** Deletes element on given index. */
    @Override
    public void remove(int index) {
        currentIndex--;
        System.arraycopy(list, index + 1, list, index, list.length - 1 - index);
    }

    /** Returns element on given index and deletes it from the list. */
    @Override
    public T pop(int index) {
        T temp = take(index);
        remove(index);
        return temp;
    }

    /** Adds elements from given list into current list. */
    @Override
    public void concat(IOrderedList<T> newList) {
        ArrayList<T> temp = new ArrayList<>();

        for (int i = 0; i <  currentIndex; i++) temp.push(list[i]);
        for (T t : newList) temp.push(t);

        currentIndex = temp.length();
        if (list.length < temp.length()) list = newArray(temp.length());

        Iterator<T> iter = temp.iterator();
        for (int i = 0; i < temp.length(); i++) list[i] = iter.next();
    }

    /** Prints the list. */
    @Override
    public void print() {
        System.out.println(Arrays.toString(list));
    }

    /** Iterator for ArrayList. */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return (index < currentIndex);
            }

            @Override
            public T next() {
                index++;
                return list[index - 1];
            }

            @Override
            public void remove() {}
        };
    }
}