package hw07;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListOrdList<A extends Comparable<? super A>>
        extends OrdList<A> {
    private int size = 0;
    private A head;
    private ListOrdList tail;

    public ListOrdList(A head, ListOrdList tail){
        this.head = head;
        this.tail = tail;
        if (tail == null) size = 1;
        else size = tail.size() + 1;
    }

    private class Iterator_ListOrdList implements Iterator<A>{
        private ListOrdList<A> list;
        public Iterator_ListOrdList(){
            if (size() == 0) list = null;
            else list = new ListOrdList<A>(head, tail);
        }
        public boolean hasNext(){
            return (list.tail != null) ;
        }
        public A next(){
            if (hasNext()){
                A next = list.head;
                list = list.tail;
                return next;
            }
            else throw new NoSuchElementException();
        }
        public void remove(){}
    }
    public Iterator<A> iterator(){
        return new Iterator_ListOrdList();
    }

    @Override
    public  int	size(){
        return size;
    }

    private ListOrdList<A> ad (ListOrdList l, A elem){
        if (l == null) {
            return new ListOrdList<A>(elem, null);
        }
        return new ListOrdList<A>((A)l.head, ad(l.tail, elem));
    }

    @Override
    public void add ( A elem) {
        tail = ad(tail, elem);
        size ++;
    }

    private A g (ListOrdList l, int i, int index){
        if (i < index){
            return g(l.tail, i + 1, index);
        }
        return (A) l.head;
    }

    @Override
    public  A get(int index){
        if ((index <= size) && (index >= 0)) {
            if (index == 0) {
                return head;
            }
            return g(tail, 1, index);
        }
        throw new NoSuchElementException();
    }

    private ListOrdList rem (ListOrdList l, int i, int index){
        if (i < index){
            return new ListOrdList(l.head, rem(l.tail, i + 1, index));
        }
        return l.tail;
    }

    @Override
    public void remove(int index) {
        if ((index <= size) && (index >= 0)) {
            if (index == 0) {
                head = (A) tail.head;
                tail = tail.tail;
                size --;
                return;
            }
            tail = rem(tail, 1, index);
            size --;
            return;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%s ", get(i));
        }
        System.out.println();
    }

}

