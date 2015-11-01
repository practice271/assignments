package hw07;

public class OrdList<A extends Comparable<? super A>> extends OrderedList<A>{
    private class Node<A extends Comparable<? super A>>{
        public int index;
        public A data;
        public Node next;

        public Node(int index,A data,Node next){
            this.index = index;
            this.data = data;
            this.next = next;
        }
    }

    Node beginning = null;

    @Override
    public int size(){
        if (beginning == null) {return 0;}
        Node node = beginning;
        while (node.next != null){
            node = node.next;
        }
        return node.index + 1;
    }

    private Node getNode (int indexToFind){
        if (this.size() - 1 < indexToFind) { return null;}
        Node node = beginning;
        while (node.index < indexToFind){
            node = node.next;
        }
        return (Node) node;
    }

    @Override
    public A get(int index) {
        return (A) getNode(index).data;
    }


    @Override
    public void set(A value) {
        if (beginning == null){
            beginning = new Node(0, value, null);
        }
        else {
            Node node = beginning;
            Node newNode = new Node(this.size(), value, null);
            int counter = 0;
            while (counter != this.size() && node.data.compareTo(value) < 0) {
                if (node.next != null) {node = node.next;}
                counter++;
            }
            getNode(this.size() - 1).next = newNode;
            if(node.data.compareTo(value) >= 0) {
                A newData = (A) node.data;
                int count = node.index;
                node.data = value;
                A mem;
                for (int i = count; i < this.size() - 2; i++) {
                    mem = (A) node.next.data;
                    node.next.data = node.data;
                    node.data = newData;
                    newData = mem;
                    node = node.next;
                }
                node.next.data = newData;
            }
        }
    }
}