package homework.hw07;

public class ordList<T extends Comparable<T>> extends abstractList<T> {
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
        else throw new ArrayIndexOutOfBoundsException();
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
        else throw new ArrayIndexOutOfBoundsException();
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

    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof abstractList)) {
            return false;
        }
        ordList argList = (ordList) arg;
        if (argList.size != size) {
            return false;
        }
        Node pointer = head;
        Node pointer2 = argList.head;
        while (pointer != null) {
            if (pointer.value != pointer2.value) {
                return false;
            }
            pointer = pointer.next;
            pointer2 = pointer2.next;
        }
        return true;
    }
}
