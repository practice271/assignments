package hw07;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList<T extends Comparable<T>>
        extends AbstractCollection<T>
        implements Comparable<OrderedList<T>>,
                   Collection<T> {

    private class ListNode<T> {

        public T value;
        public ListNode<T> next;

        public ListNode(T v, ListNode<T> n) {
            value = v;
            next = n;
        }
    }

    private class ListIterator<T> implements Iterator<T> {
        public ListIterator(ListNode<T> head) {
            currentNode = head;
        }

        public ListNode<T> currentNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (currentNode == null)
                throw new NoSuchElementException();

            T res = currentNode.value;
            currentNode = currentNode.next;
            return res;
        }

        @Override
        public void remove() {
            return;
        }
    }

    private ListNode<T> head = null;
    private int size = 0;

    @Override
    public int compareTo(OrderedList<T> obj) {
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
    public boolean equals(Object obj) {
        return (obj instanceof OrderedList &&
                compareTo((OrderedList<T>) obj) == 0);
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
        return new ListIterator<T>(head);
    }

    @Override
    public boolean add(T t) {
        ListNode<T> current = head, last = null;
        while (current != null &&
               current.value.compareTo(t) < 0) {
            last = current;
            current = current.next;
        }
        ListNode<T> node = new ListNode<T>(t, current);
        if (current == head) {
            head = node;
        } else {
            last.next = node;
        }
        size++;
        return true;
    }

}
