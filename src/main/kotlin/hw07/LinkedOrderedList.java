package hw07;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedOrderedList<T extends Comparable<T>>
        extends OrderedList<T> {

    private class ListNode {

        public T value;
        public ListNode next;

        public ListNode(T v, ListNode n) {
            value = v;
            next = n;
        }
    }

    private class ListIterator implements Iterator<T> {
        public ListIterator(ListNode head) {
            currentNode = head;
        }

        public ListNode currentNode;

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

    private ListNode head = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(head);
    }

    @Override
    public boolean add(T t) {
        ListNode current = head, last = null;
        while (current != null &&
                current.value.compareTo(t) < 0) {
            last = current;
            current = current.next;
        }
        ListNode node = new ListNode(t, current);
        if (current == head) {
            head = node;
        } else {
            last.next = node;
        }
        size++;
        return true;
    }

}
