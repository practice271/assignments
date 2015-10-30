package homework.hw07.javaimp;

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
        Node curNode = head;
        while ((head.next != null) && (curNode.value.compareTo(value) <= 0)) {
            curNode = curNode.next;
        }
        curNode.next = new Node(curNode.value, curNode.next);
        curNode.value = value;
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
    public boolean equals(Object other) { // TODO
        if (!(other instanceof LinkedOrderedList)) {
            return false;
        }
        LinkedOrderedList otherList = (LinkedOrderedList) other;
        if (otherList.size() != size) {
            return false;
        }
        int i = 0;
        Node curNode = head;
        while (i < size) {
            if (curNode.value != otherList.get(i)) {
                return false;
            }
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    @Override
    public int hashCode() { // TODO
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
}
