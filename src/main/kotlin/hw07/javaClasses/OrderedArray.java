package hw07.javaClasses;

/* OrderedArray made by Guzel Garifullina
   Estimated time  1 hour
   real time       2 hours
*/
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArray<E extends Comparable<? super E>>
        extends AbstractOrderedList< E > {
    private int size_basic = 100;
    private int size = 0;
    private E[] arr;

    public OrderedArray(int size, E defaultElem) {
        size_basic = Math.max(size_basic, size);
        arr =  (E[]) Array.newInstance(defaultElem.getClass(), size_basic);
        for (int i = 0 ; i < size; i++){
            arr[i] =  defaultElem;
        }
        this.size = size;
    }
    private class AIterator implements Iterator<E> {
        private int index = 0;
        public boolean hasNext(){
            return (index < size()) ;
        }
        public E next(){
            if (hasNext()){
                return arr[index ++];
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }
    public Iterator<E> iterator(){
        return new AIterator();
    }
    @Override
    public  int	size(){
        return size;
    }
    private void resize () {
        size_basic *= 2;
        E[] arr2 = (E[]) Array.newInstance(arr[0].getClass(), size_basic);
        for (int i = 0; i < size; i++) {
            arr2[i] = arr[i];
        }
        arr = arr2;
    }
    @Override
    public void add (E elem){
        if (size == size_basic) {
            resize();
        }
        for (int i = size - 1; i >= 0; i -- ){
            if (arr[i].compareTo(elem) > 0){
                arr[i + 1] = arr[i];
            }
            else {
                arr[i + 1] = elem;
                size ++;
                return;
            }
        }
        //elem is smallest
        arr [0] = elem;
        size ++;
        return;
    }
    @Override
    public  E get(int index) throws OutOfBoundException {
        if ((index < size) && (0 <= index)){
            return arr[index];
        }
        throw new OutOfBoundException();
    }
    @Override
    public void removeAt(int index) throws OutOfBoundException {
        if ((index >= size) || (0 > index)){
            throw new OutOfBoundException();
        }
        for (int i = index; i < size - 1; i ++){
            arr[i] = arr [i + 1];
        }
        size --;
    }
    @Override
    public void	clear() {
        size = 0;
    }
}
