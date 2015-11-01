package homeworks.hw07;

import java.util.Arrays;

public class JavaOrderedListArray<T extends Comparable<? super T>> extends JavaOrderedList<T> {
    private T[] array;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    public JavaOrderedListArray() {
        this.array = (T[]) new Comparable[0];
        this.size = array.length;
    }

    @Override
    public void add(T object) {
        if (size == 0) {
            size++;
            array = Arrays.copyOf(array, size);
            array[0] = object;
        } else {
            size++;
            int index = binarySearch(object);
            array = Arrays.copyOf(array, size);

            for (int i = size - 1; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = object;
        }
    }

    private int binarySearch(T key) {
        int low = 0;
        int high = size - 1;
        int result = 0;
        int mid = (low + high) / 2;
        while (low <= high && mid  != size - 1) {
            T midVal = array[mid];
            int cmp = midVal.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
                result = low;
            } else if (cmp > 0) {
                high = mid - 1;
                result = mid;
            } else {
                return mid; // key found
            }
            mid = (low + high) / 2;
        }
        return result; //key not found, return position to add
    }

    @Override
    public void removeAt(int index) {
        if ((index <= 0 || index >= size)) {
            return;
        }
        size--;
        T[] temp = array;
        System.arraycopy(array, index + 1, temp, index, size - index);
        array = Arrays.copyOf(temp, size);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size(); i++) {
            hash = hash * 31 + get(i).hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JavaOrderedListArray)) {
            return false;
        }
        JavaOrderedListArray otherList  = (JavaOrderedListArray) other;
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (get(i) != otherList.get(i)) {
                return false;
            }
        }
        return true;
    }
}
