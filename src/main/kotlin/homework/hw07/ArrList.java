package homework.hw07;

import java.util.Iterator;

public class ArrList<T extends Comparable<T>> extends AbstractList<T> {
    //just support property
    private int size    =  2;
    // shows how many elements we have. -1 is default value.
    // It means that list is empty
    private int pointer = -1;
    protected T[] list;
    private int limit = 2147483647;

    private void createArr(){
       if (list == null) {
           list = (T[]) new Comparable[size];
       }
        else if (list.length-1 == pointer) {
           T[] list2 = (T[]) new Comparable[size]; // 'size' already changed to new value
           System.arraycopy(list, 0, list2, 0, size/2);
           list = list2;
       }
    }

    @Override
    public boolean isEmpty(){
        return (pointer==0);
    }

    @Override
    public int size(){
        return pointer+1;
    }

    @Override
    public T get(int index){
        if (index<=pointer) return list[index];
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public void add(T elem){
        if (pointer<size-1){
            createArr();
            pointer++;
            list[pointer] = elem;
        }
        else {
            size = size * 2;
            createArr();
            pointer++;
            list[pointer] = elem;
        }
    }

    public void print(){
        for (int i = 0; i <= pointer; i++)
            System.out.print(list[i] + " ");
    }

    @Override
    public void removeAt(int index){
        if (index>=0 && index<=pointer){
            System.arraycopy(list, index+1, list, index, pointer - index);
            pointer--;
        }
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public int hashCode(){
        final int number = 17;
        int result = 0;

        for (int i = 0; i <= pointer; i++){
            result += (list[i].hashCode() * number) % limit;
        }
        return result;
    }

    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof AbstractList)) {
            return false;
        }
        AbstractList argList = (AbstractList) arg;
        if (argList.size() != pointer+1) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (list[i] != argList.get(i)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrListIterator(this);
    }

    private class ArrListIterator implements Iterator<T> {
        private final ArrList<T> list;
        private int index;

        public ArrListIterator(ArrList<T> list) {
            this.list = list;
            index = 0;
        }

        public boolean hasNext() {
            return index < list.size;
        }

        public T next() {
            return list.get(index++);
        }

        public void remove() {
        }
    }
}
