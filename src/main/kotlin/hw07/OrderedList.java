package hw07;

import java.util.Iterator;

/**
 * Created by Mikhail on 01.11.2015.
 */
public class OrderedList<A extends Comparable<? super A>>
                           extends AOrderedList<A> {

    public class Node {
        public A item;
        public Node next;

        public Node(A i) {
            item = i;
            next = null;
        }
    }

    private Node head;
    private int size;

    public OrderedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(A item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
            size++;
            return;
        } else if (item.compareTo(head.item) < 0) {
            newNode.next = head;
            head = newNode;
            size++;
        } else {
            Node after = head.next;
            Node before = head;
            while (after != null) {
                if (item.compareTo(after.item) < 0) break;
                before = after;
                after = after.next;
            }
            newNode.next = before.next;
            before.next = newNode;
            size++;
        }
    }

    @Override
    public boolean contains(A item) {
        Node n = head;
        while (n != null) {
            if (item.compareTo(n.item) == 0) return true;
            n = n.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public A get(int index) {
        int i = 0;
        Node curNode = head;
        while (i < index) {
            curNode = curNode.next;
            i++;
        }
        return curNode.item;
    }

    public class LIterator implements Iterator<A> {
        private Node node;

        public LIterator(Node node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return (node != null);
        }

        @Override
        public A next() {
            A i = node.item;
            node = node.next;
            return i;
        }

        @Override
        public void remove() {}
    }

    @Override
    public Iterator<A> iterator() {
        return new LIterator(head);
    }
}

