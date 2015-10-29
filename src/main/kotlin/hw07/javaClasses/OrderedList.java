package hw07.javaClasses;

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       2 hours
*/

public class OrderedList<E extends Comparable<? super E>>
        extends AbstractOrderedList< E >{
    private int size = 0;
    private E last;
    private OrderedList list;

    public OrderedList(E last, OrderedList list) {
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
    @Override
    public void add (E elem){
        if (size == 0){
            last = elem;
            size ++;
            return;
        }
        list = new OrderedList(last, list);
        last = elem;
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
    private OrderedList setLoc (OrderedList l,E elem, int i, int index){
        if (i < index){
            return new OrderedList(l.last, setLoc(l.list, elem, i + 1, index));
        }
        return new OrderedList(elem, l.list);
    }
    @Override
    public  void set(int index, E elem){
        if ((index >= size) || (index < 0)){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == size -1){
            last = elem;
        }
        else {
            list = setLoc(list, elem, 0, size - index - 2);
        }
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
    private OrderedList reverseLoc (OrderedList l, OrderedList acc){
        if (l.list == null){
            return new OrderedList(l.last, acc );
        }
        return reverseLoc (l.list, new OrderedList (l.last, acc));
    }
    @Override
    public void	reverse() {
        if (size == 0 || size == 1){
            return;
        }
        OrderedList<E> l = reverseLoc(list, new  OrderedList<E>(last, null) );
        list = l.list;
        last = l.last;
    }
}

