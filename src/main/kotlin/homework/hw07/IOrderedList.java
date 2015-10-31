/*
 * Homework 7 (27.10.2015)
 * Interface for ordered list
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07;

/** Interface of ordered list. */
public interface IOrderedList<T> {
    boolean isEmpty();
    int length();
    void push(T elem);
    T take(int index);
    void remove(int index);
    T pop(int index);
    void concat(IOrderedList<T> list);
    void print();
}