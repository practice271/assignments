package hw07.javaClasses;

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       3 hours
*/

public class OrderedList<E extends Comparable<? super E>>
        extends AbstractOrderedList< E >{
    private int size = 0;
    private E last;
    private OrderedList list;

    public OrderedList(E last) {
        this.last = last;
        size = 1;
        list = null;
    }
    private OrderedList(E last, OrderedList list) {
        this.last = last;
        this.list = list;
        if (list == null){
            size = 1;
        }
        else {
            size = list.size() + 1;
        }
    }
    @Override
    public  int	size(){
        return size;
    }

    private OrderedList<E> addLoc (OrderedList l, E elem){
        if (l == null){
            return new OrderedList<E>(elem, null);
        }
        int comp = l.last.compareTo(elem);
        if (comp <= 0){
            return new OrderedList<E>(elem,l);
        }
        return new OrderedList<E>((E)l.last, addLoc(l.list, elem));
    }
    @Override
    public void add ( E elem){
        if (size == 0){
            last = elem;
            size ++;
            return;
        }
        if (last.compareTo(elem) < 0){
            list = new OrderedList(last, list);
            last = elem;
            size ++;
            return;
        }

        list = addLoc(list, elem);
        size ++;
    }

    private E getLoc (OrderedList l, int i, int index){
        if (i < index){
            return getLoc(l.list, i + 1, index);
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
        return getLoc(list, 0, size - index - 2);
    }

    private OrderedList remLoc (OrderedList l, int i, int index){
        if (i < index){
            return new OrderedList(l.last, remLoc(l.list,i + 1, index));
        }
        if (i == size - 1){ //first elem
            return null;
        }
        return l.list;
    }
    @Override
    public void removeAt(int index) {
        if ((index >= size) || (index < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == size - 1) {
            last = (E) list.last;
            list = list.list;
            size --;
            return;
        }
        list = remLoc(list, 0, size - index - 2);
        size --;
    }
    @Override
    public void	clear() {
        size = 0;
        list = null;
    }
}

