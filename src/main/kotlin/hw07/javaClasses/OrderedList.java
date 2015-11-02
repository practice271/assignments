package hw07.javaClasses;

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       3 hours
*/

public class OrderedList<E extends Comparable<? super E>>
        extends AbstractOrderedList< E >{
    private int size = 0;
    private E last;
    private OrderedList tail;

    public OrderedList(E last) {
        this.last = last;
        size = 1;
        tail = null;
    }
    private OrderedList(E last, OrderedList list) {
        this.last = last;
        this.tail = list;
        if (list == null){
            size = 1;
        }
        else {
            size = list.size() + 1;
        }
    }
    private OrderedList<E> addLoc (OrderedList l, E elem){
        if (l == null){
            return new OrderedList<E>(elem, null);
        }
        int comp = l.last.compareTo(elem);
        if (comp <= 0){
            return new OrderedList<E>(elem,l);
        }
        return new OrderedList<E>((E)l.last, addLoc(l.tail, elem));
    }
    @Override
    public  int	size(){
        return size;
    }
    @Override
    public void add ( E elem){
        if (size == 0){
            last = elem;
            size ++;
            return;
        }
        if (last.compareTo(elem) < 0){
            tail = new OrderedList(last, tail);
            last = elem;
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
        return (E) l.last;
    }
    @Override
    public  E get(int index){
        if ((index >= size) || (index < 0)){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == size -1){
            return last;
        }
        //our traversal from last to first
        return getLoc(tail, 0, size - index - 2);
    }

    private OrderedList remLoc (OrderedList l, int i, int index){
        if (i < index){
            return new OrderedList(l.last, remLoc(l.tail,i + 1, index));
        }
        if (i == size - 1){ //first elem
            return null;
        }
        return l.tail;
    }
    @Override
    public void removeAt(int index) {
        if ((index >= size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == size - 1) {
            last = (E) tail.last;
            tail = tail.tail;
            size --;
            return;
        }
        tail = remLoc(tail, 0, size - index - 2);
        size --;
    }
    @Override
    public void	clear() {
        size = 0;
        tail = null;
    }
}