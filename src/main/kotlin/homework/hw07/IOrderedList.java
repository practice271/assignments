/*
 * Homework 7 (27.10.2015)
 * Abstract class for ordered list
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/** Abstract class for ordered list. */
public abstract class IOrderedList<T> implements Iterable<T>, Comparable<IOrderedList<T>> {
    abstract boolean isEmpty();
    abstract int length();
    abstract void push(T elem);
    abstract T take(int index);
    abstract void remove(int index);
    abstract T pop(int index);
    abstract void concat(IOrderedList<T> list);
    abstract void print();

    /** Returns hash code of the list. */
    @Override
    public int hashCode() {
        int hash = 0;
        for (T t : this) hash = Math.abs((hash + t.hashCode()) * 31);
        return hash;
    }

    /** Checks equality between given object and the list. */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IOrderedList)) return false;
        else {
            IOrderedList<T> list = (IOrderedList) obj;
            if (list.length() == this.length()) {
                Iterator<T> iter = this.iterator();
                for (T t : list)
                    if (iter.next() != t) return false;
                return true;
            }
            else return false;
        }
    }

    /** Compares two lists. */
    @Override
    public int compareTo(@NotNull IOrderedList<T> o) {
        if (this.length() < o.length()) return -1;
        if (this.length() < o.length()) return 1;
        return 0;
    }
}