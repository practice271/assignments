/*
 * Homework 7 (27.10.2015)
 * Implementation of list using ADT.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07;

import java.util.Iterator;

/** Implementation of IOrderedList<T> using abstract data type. */
public class ADTList<T extends Comparable<T>> extends IOrderedList<T> {

    /** ADT for list. */
    private class Cons {
        public T head;
        public Cons tail;

        public Cons(T head, Cons tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    private Cons list = null;
    private int currentSize = 0;

    /** Checks if the list is empty. */
    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    /** Returns length of the list. */
    @Override
    public int length() {
        return currentSize;
    }

    /** Inserts element into given ADT list. */
    private Cons insert(Cons l, T elem) {
        if ((l != null) && (elem.compareTo(l.head) < 0))
            return new Cons(l.head, insert(l.tail, elem));
        else return new Cons(elem, l);
    }

    /** Puts element into the list. */
    @Override
    public void push(T elem) {
        currentSize++;
        list = insert(list, elem);
    }

    /** Returns element on given index in ADT list. */
    private T takeElem(Cons l, int n) {
        if (n == 0)
            if (l == null) return null;
            else return l.head;
        else return takeElem(l.tail, n - 1);
    }

    /** Returns element on given index. */
    @Override
    public T take(int index) {
        return takeElem(list, index);
    }

    /** Deletes element on given index from ADT list. */
    private Cons delete(Cons l, int n) {
        if (n == 0)
            if (l.tail == null) return null;
            else return new Cons((l.tail).head, (l.tail).tail);
        return new Cons(l.head, delete(l.tail, n - 1));
    }

    /** Deletes element on given index. */
    @Override
    public void remove(int index) {
        currentSize--;
        list = delete(list, index);
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
        ADTList<T> temp = new ADTList<>();

        for (int i = 0; i < currentSize; i++) {
            temp.push(list.head);
            list = list.tail;
        }
        for (T t : newList) temp.push(t);

        currentSize += newList.length();
        for (T t : temp) list = insert(list, t);
    }

    /** Gets string from given ADT list. */
    private String getString(Cons l) {
        if (l != null)
            return (l.head).toString() + ", " + getString(l.tail);
        return "null";
    }

    /** Prints the list. */
    @Override
    public void print() {
        System.out.println("[" + getString(list) + "]");
    }

    /** Iterator for ADTList. */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Cons t = list;

            @Override
            public boolean hasNext() {
                return (t != null);
            }

            @Override
            public T next() {
                T temp = t.head;
                t = t.tail;
                return temp;
            }

            @Override
            public void remove() {}
        };
    }
}