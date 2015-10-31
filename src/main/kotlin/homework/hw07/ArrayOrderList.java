package homework.hw07;

public class ArrayOrderList<T extends Comparable<? super T>>
        extends IOrderList<T> {

    private T[] arr;
    private Integer count;

    public ArrayOrderList() {

        arr = (T[]) new Comparable[10];
        count = 0;
    }


    @Override
    public void add(T val) {
        if (count == arr.length - 1) bigger();
        int i = 0;
        while (i < count && arr[i].compareTo(val) == -1) i++;
        toRight(i);
        arr[i] = val;
        count++;
    }

    private void bigger() {
        T[] newarr = (T[]) new Comparable[arr.length + 10];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i];
        }
        arr = newarr;
    }

    private void toRight(int index) {

        if (count == 0) arr[1] = arr[0];
        else
            for (int i = count; i > index; i--) {
                arr[i] = arr[i - 1];
            }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public T get(int index) {
        return arr[index];
    }

    public void print() {
        int i = 0;
        while (i < size() & arr[i] != null) {
            System.out.println(arr[i]);
            i++;
        }
    }

}