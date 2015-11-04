package homework.hw07.javaimp;

import homework.hw07.AbstractOrderedList;

import java.util.Iterator;

/**
 * Linked list implementation of AbstractOrderedList
 *
 * @author Kirill Smirenko, group 271
 */
public class LinkedOrderedList<T extends Comparable<T>> extends AbstractOrderedList<T> {
    private Node head;
    private int size;

    public LinkedOrderedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(T value) {
        if (head == null) {
            head = new Node(value);
            size++;
            return;
        }
        if (value.compareTo(head.value) < 0) {
            head.next = new Node(head.value, head.next);
            head.value = value;
            size++;
            return;
        }
        Node curNode = head;
        while ((curNode.next != null) && (curNode.next.value.compareTo(value) <= 0)) {
            curNode = curNode.next;
        }
        curNode.next = new Node(value, curNode.next);
        size++;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Node curNode = head;
        while (i < index) {
            curNode = curNode.next;
            i++;
        }
        return curNode.value;
    }

    @Override
    public void removeAt(int index) {
        if ((index <= 0) || (index >= size)) {
            return;
        }
        int i = 0;
        Node curNode = head;
        while (i < index - 1) {
            curNode = curNode.next;
            i++;
        }
        curNode.next = curNode.next.next;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int hashCode() {
        int h = 0;
        int i = 0;
        Node curNode = head;
        while (i < size) {
            h = 23 * h + curNode.value.hashCode();
            curNode = curNode.next;
            i++;
        }
        return h;
    }

    public Iterator<T> iterator() {
        return new LinkedOrderedListIterator(head);
    }

    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private class LinkedOrderedListIterator implements Iterator<T> {
        private Node node;

        public LinkedOrderedListIterator(Node node) {
            this.node = node;
        }

        public boolean hasNext() {
            return node != null;
        }

        public T next() {
            T val = node.value;
            node = node.next;
            return val;
        }

        public void remove() {
        }
    }
}
