package homeworks.hw07;

public class JavaOrderedListATD<T extends Comparable<? super T>> extends KotlinOrderedList<T>  {
    private Node head;
    private int size;

    public JavaOrderedListATD() {
        this.head = null;
        this.size = 0;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    @Override
    public void add(T object) {
        if (head == null) {
            head = new Node(object);
            size++;
            return;
        }
        if (object.compareTo(head.value) < 0) {
            head.next = new Node(head.value, head.next);
            head.value = object;
            size++;
            return;
        }
        Node node = head;
        while ((node.next != null) && (node.next.value.compareTo(object) <= 0)) {
            node = node.next;
        }
        node.next = new Node(object, node.next);
        size++;
    }

    @Override
    public void removeAt(int index) {
        if ((index <= 0 || index >= size)) {
            return;
        }
        Node node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        size--;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        Node node = head;
        for (int i = 0; i < size; i++) {
            hash = hash * 31 + node.value.hashCode();
            node = node.next;
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JavaOrderedListATD)) {
            return false;
        }
        JavaOrderedListATD otherList  = (JavaOrderedListATD) other;
        if (size() != otherList.size()) {
            return false;
        }
        Node node = head;
        for (int i = 0; i < size(); i++) {
            if (node.value != otherList.get(i)) {
                return false;
            }
            node = node.next;
        }
        return true;
    }
}
