package hw07;

public class OrdArray <A extends Comparable<? super A>> extends OrderedList<A>{
    private A[] array = (A[]) new Comparable[0];

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
        for (int k = i + 1; k < arr.length - 1; k++){
            mem = arr[k + 1];
            arr[k + 1] = arr[k];
            arr[k] = val;
            val = mem;
        }
        array = arr;
    }
}
