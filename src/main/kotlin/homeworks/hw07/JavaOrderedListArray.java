package homeworks.hw07;

import java.util.Arrays;
import java.util.Iterator;

public class JavaOrderedListArray<T extends Comparable<? super T>> extends JavaOrderedList<T> {
    private T[] array;
    private int size;
    private int currentIndex;

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
        this.currentIndex = 0;
    }

    @Override
    public void add(T object) {
        if (size == 0) {
            size++;
            array = Arrays.copyOf(array, size);
            array[0] = object;
        } else {
            currentIndex++;
            int index = binarySearch(object);
            if (currentIndex == size) {
                resize();
            }
            System.arraycopy(array, index, array, index + 1, currentIndex - index);
            array[index] = object;
        }
    }

    private void resize() {
        size *= 2;
        array = Arrays.copyOf(array, size);
    }

    private int binarySearch(T key) {
        int low = 0;
        int high = currentIndex;
        int result = 0;
        int mid = (low + high) / 2;
        while (low <= high && mid  != currentIndex) {
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
        //Not matter whether the key is found, return to the place where the insert
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

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            public boolean hasNext() {
                return (index <= currentIndex);
            }

            public T next() {
                index++;
                return array[index - 1];
            }
        };
    }
}
