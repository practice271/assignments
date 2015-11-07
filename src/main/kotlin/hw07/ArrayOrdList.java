
package hw07;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayOrdList<A extends Comparable<? super A>>
                          extends OrdList<A>{
    private int size = 0;
    private static int clusterSize = 100;
    private A[] array;

    public ArrayOrdList(int size, A element){
        array = (A[]) new Comparable[clusterSize];
        for (int i = 0; i < size; i++) {
            array[i] = element;
        }
        this.size = size;
    }
    protected class Iterator_ArrayOrdList implements Iterator<A> {
        private int index = 0;
        public boolean hasNext() {
            return (index < size());
        }
        public A next() {
            if (hasNext()) return array[index++];
            else throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<A> iterator() {
        return new Iterator_ArrayOrdList();
    }


    @Override
    public int size(){
        return size;
    }

    @Override
    public A get(int index){
        if (index >= 0 && index < size) return array[index];
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%s ", get(i));
        }
        System.out.println();
    }

    @Override
    public void add(A elem){
        if (size == clusterSize) {
            resize();
        }
        for (int i = size - 1; i >= 0; i--) {
            array[i + 1] = elem;
            size++;
            return;
        }
        array[0] = elem;
        size++;
    }

    @Override
    public void remove(int index){
        if (index >= 0 && index < size){
            for (int i = index; i < size; i++){
                array[i] = array[i + 1];
            }
            size--;
        }
    }

    private void resize () {
        clusterSize *= 2;
        A[] array1 = ((A[]) new Comparable[clusterSize]);
        for (int i = 0; i < size; i++) {
            array1[i] = array[i];
        }
        array = array1;
    }
}
