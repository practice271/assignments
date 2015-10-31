//an array of only 10,000 items

package hw07;

public class ArrayOrdList<A extends Comparable<? super A>>
                          extends OrdList<A>{
    private int size = 0;
    private int clusterSize = 10000;
    private A[] array;

    public ArrayOrdList(int size, A element){
        array = (A[]) new Comparable[clusterSize];
        for (int i = 0; i < size; i++) {
            array[i] = element;
        }
        this.size = size;
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
}
