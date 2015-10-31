package hw07;

/**
 * Created by Antropov Igor on 31.10.2015.
 */


public class OrderedList<E extends Comparable<? super E>> extends AbstractOrderedList<E> {

    class Node<E> {
        private Node next;
        private Node previous;
        private E t;

        public Node() {
            this.next = null;
            this.previous = null;
        }

        public Node(Node previous) {
            this.next = null;
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public E getE() {
            return t;
        }

        public void setE(E t) {
            this.t = t;
        }
    }

    private Node<E> firstNode = new Node<E>(null);
    private int size = 0;

    public OrderedList(E elem) {
        firstNode.setE(elem);
        size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int i) {
        Node<E> temp = firstNode;
        for (int j = 0; j < i; j++) {
            temp = temp.getNext();
        }
        return temp.getE();
    }

    @Override
    public void add(E elem) {
        Node<E> beforeOne = firstNode;
        while (elem.compareTo(beforeOne.getE()) > 0) {
            beforeOne = beforeOne.getNext();
        }
        Node<E> afterOne = beforeOne.getPrevious();
        Node<E> newElem = new Node<E>(afterOne);
        newElem.setNext(beforeOne);
        newElem.setE(elem);
        if (afterOne == null) {
            firstNode = newElem;
        } else {
            afterOne.setNext(newElem);
        }
        beforeOne.setPrevious(newElem);
        size++;
    }


}
