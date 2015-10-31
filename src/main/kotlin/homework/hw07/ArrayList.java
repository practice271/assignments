/*
 * Homework 7 (27.10.2015)
 * Implementation of list using arrays.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07;

import java.util.Arrays;

/** Implementation of IOrderedList<T> using arrays. */
public class ArrayList<T extends Comparable<T>> implements IOrderedList<T> {
    private int currentSize = 10;
    private T[] list = newArray();
    private int currentIndex = 0;

    /** Creates new array. */
    private T[] newArray() {
        return (T[]) new Comparable[currentSize];
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
        T[] temp = newArray();
        currentIndex++;

        if (currentIndex > currentSize - 1) {
            currentSize += 10;
            temp = newArray();
        }

        int pos = 0;
        for (int i = 0; i < currentIndex - 1; i++) {
            if (list[i] == null || elem.compareTo(list[i]) > 0) break;
            else pos++;
        }

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
        System.arraycopy(list, index + 1, list, index, currentSize - 1 - index);
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
        ArrayList<T> temp = new ArrayList<T>();

        for (int i = 0; i <  currentIndex; i++)
            temp.push(list[i]);
        for (int i = 0; i < newList.length(); i++)
            temp.push(newList.take(i));

        currentIndex = temp.length();
        if (currentSize < temp.length()) {
            currentSize = temp.length();
            list = newArray();
        }
        for (int i = 0; i < temp.length(); i++) list[i] = temp.take(i);
    }

    /** Prints the list. */
    @Override
    public void print() {
        System.out.println(Arrays.toString(list));
    }

    /** Returns hash code of the list. */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < currentIndex; i++)
            hash = Math.abs((hash + list[i].hashCode()) * 31);
        return hash;
    }

    /** Checks equality between given object and the list. */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IOrderedList && ((IOrderedList) obj).length() == currentIndex) {
            for (int i = 0; i < currentIndex; i++)
                if (list[i] != ((IOrderedList) obj).take(i)) return false;
            return true;
        }
        return false;
    }
}