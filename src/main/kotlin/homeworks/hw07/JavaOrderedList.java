package homeworks.hw07;

import java.util.Iterator;

public abstract class JavaOrderedList<T extends Comparable<? super T>>
        implements Comparable<JavaOrderedList<? extends T>>, Iterable< T> {

    abstract public T get(int index);
    abstract public int size();

    abstract public void add(T value);
    abstract public void removeAt(int index);

    @Override
    public int hashCode() {
        int hash = 0;
        for (T t: this) {
            hash = Math.abs((hash + t.hashCode()) * 31);
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JavaOrderedList)) {
            return false;
        }

        int size = size();
        if (size != ((JavaOrderedList) other).size()) {
            return false;
        }

        Iterator listIter  = iterator();
        Iterator otherIter = ((JavaOrderedList) other).iterator();

        while (listIter.hasNext()) {
            if (!listIter.next().equals(otherIter.next())) {
                return false;
            }
        }
        return true;
    }

    public int compareTo(JavaOrderedList<? extends T> other) {
        int size = size();
        int minSize = Math.min(size, other.size());

        if (size > other.size()) {
            return 1;
        }
        if (size < other.size()) {
            return -1;
        }

        Iterator listIter  = iterator();
        Iterator otherIter = other.iterator();

        for (int i = 0; i < minSize; i++) {
            int result = ((T)listIter.next()).compareTo((T)otherIter.next());
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
