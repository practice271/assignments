package hw07.javaClasses;

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       3 hours
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedList<E extends Comparable<? super E>>
        extends AbstractOrderedList<E>{
    private int size = 0;
    private E first;
    private OrderedList tail;

    public OrderedList(E first) {
        this.first = first;
        size = 1;
        tail = null;
    }
    private OrderedList(E first, OrderedList tail) {
        this.first = first;
        this.tail = tail;
        if (tail == null){
            size = 1;
        }
        else {
            size = tail.size() + 1;
        }
    }
    private class LIterator implements Iterator<E>{
        private OrderedList<E> list;
        public LIterator(){
            if (size() == 0){list = null;}
            else {list = new OrderedList<E>(first, tail);}
        }
        public boolean hasNext(){
            return (list != null) ;
        }
        public E next(){
            if (hasNext()){
                E f = list.first;
                list = list.tail;
                return f;
            }
            else {
                throw new NoSuchElementException();
            }
        }
        public void remove() {
            throw new UnsupportedOperationException("no changes allowed");}
    }
    public Iterator<E> iterator(){
        return new LIterator();
    }
    private OrderedList<E> addLoc (OrderedList l, E elem){
        if (l == null){
            return new OrderedList<E>(elem, null);
        }
        int comp = l.first.compareTo(elem);
        if (comp >= 0){
            return new OrderedList<E>(elem,l);
        }
        return new OrderedList<E>((E)l.first, addLoc(l.tail, elem));
    }
    @Override
    public  int	size(){
        return size;
    }
    @Override
    public void add ( E elem){
        if (size == 0){
            first = elem;
            size ++;
            return;
        }
        if (first.compareTo(elem) >= 0){
            tail = new OrderedList(first, tail);
            first = elem;
            size ++;
            return;
        }
        tail = addLoc(tail, elem);
        size ++;
    }

    private E getLoc (OrderedList l, int i, int index){
        if (i < index){
            return getLoc(l.tail, i + 1, index);
        }
        return (E) l.first;
    }
    @Override
    public  E get(int index) throws OutOfBoundException {
        if ((index >= size) || (index < 0)){
            throw new OutOfBoundException();
        }
        if (index == 0){
            return first;
        }
        return getLoc(tail, 1, index);
    }

    private OrderedList remLoc (OrderedList l, int i, int index){
        if (i < index){
            return new OrderedList(l.first, remLoc(l.tail,i + 1, index));
        }
        if (i == size - 1){ //last elem
            return null;
        }
        return l.tail;
    }
    @Override
    public void removeAt(int index) throws OutOfBoundException {
        if ((index >= size) || (index < 0)) {
            throw new OutOfBoundException();
        }
        if (index == 0) {
            first = (E) tail.first;
            tail = tail.tail;
            size --;
            return;
        }
        tail = remLoc(tail, 1, index);
        size --;
    }
    @Override
    public void	clear() {
        size = 0;
        tail = null;
    }
}