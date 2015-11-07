package homework.hw07;

import java.util.Iterator;

public class OrdList<T extends Comparable<T>> extends AbstractList<T> {
    Node head;
    Node tail;
    int size = 0;
    private int limit = 2147483647;

    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void print() {
        if (head != null) {
            Node pointer = head;
            while (pointer != null) {
                System.out.print(pointer.value + " ");
                pointer = pointer.next;
            }
        }
        else System.out.print("List is empty!");
    }

    @Override
    public boolean isEmpty(){
        return (head==null);
    }

    @Override
    public void add(T elem){
        if (size == 0){
            Node node = new Node(elem,null);
            head = node;
            tail = head;
            size++;
        }
        else {
            Node node = new Node(elem,null);
            tail.next = node;
            tail = node;
            size++;
        }
    }

    @Override
    public T get(int index){
        if (index>=0 && index<=size) {
            int counter = 1;
            Node pointer = head;
            while (counter < index){
                counter++;
                pointer = pointer.next;
            }
            return pointer.value;
        }
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void removeAt(int index){
        if (index>1 && index<=size) {
            int counter = 1;
            Node pointer = head;
            while (counter < index-1) {
                counter++;
                pointer = pointer.next;
            }
            pointer.next = pointer.next.next;
        }
        else if(index==1) head = head.next;
    }

    @Override
    public int hashCode(){
        final int number = 17;
        int result = 0;
        if (head!=null){
            Node pointer = head;
            while(pointer!=null){
                result = (pointer.value.hashCode() * number) % limit;
                pointer = pointer.next;
            }
        }
        return result;
    }

    public Iterator<T> iterator() {
        return new LinkedOrderedListIterator(this);
    }

    private class LinkedOrderedListIterator implements Iterator<T> {
        private Node list;

        public LinkedOrderedListIterator(OrdList list) {
            this.list = list.head;
        }

        public boolean hasNext() {
            return list.next != null;
        }

        public T next() {
            list = list.next;
            return list.value;
        }

        public void remove() {
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AbstractList)) {
            return false;
        }

        AbstractList otherList = (AbstractList) other;
        if (otherList.size() != this.size()) {
            return false;
        }
        Iterator iterator  = iterator();
        Iterator iterOther = ((AbstractList) other).iterator();
        while (iterator.hasNext()) {
            if (iterator.next() != iterOther.next()) {
                return false;
            }
        }
        return true;
    }

}
