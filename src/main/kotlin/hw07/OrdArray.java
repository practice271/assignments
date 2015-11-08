package hw07;

import java.util.*;

public class OrdArray <A extends Comparable<? super A>> extends OrderedList<A>{
    private A[] array = (A[]) new Comparable[0];

    private class OrdArrayIter implements Iterator<A> {
        int index = 0;
        public boolean hasNext(){
            return (index < array.length);
        }
        public A next(){
            if (hasNext()) {
                A next = (A) array[index];
                index++;
                return next;
            }
            return null;
        }
    }

    public Iterator iterator(){
        return new OrdArrayIter();
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public A get(int index) {
        if(index > this.size() - 1){return null;}
        return array[index];
    }

    @Override
    public void set(A value) {
        A[] arr = (A[]) new Comparable[this.size() + 1];
        for(int i = 0; i < array.length; i++){
            arr[i] = array[i];
        }
        arr[array.length] = value;
        int i = 0;
        while (arr[i].compareTo(value) == -1){
            i++;
        }
        A val = arr[i];
        A mem;
        arr[i] =  value;
        for (int k = i + 1; k < arr.length; k++){
            mem = arr[k];
            arr[k] = val;
            val = mem;
        }
        array = arr;
    }
}