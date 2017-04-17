package homework.hw07;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractList<T extends Comparable<? super T>>
        implements Comparable<AbstractList<? extends T>>, Iterable<T>{
    protected abstract void add(T elem);
    protected abstract T get(int index);
    protected abstract boolean isEmpty();
    protected abstract int size();
    protected abstract void removeAt(int index);


    public int compareTo(@NotNull AbstractList<? extends T> other) {
        int lim = Math.min(size(), other.size());
        int i = 0;
        while (i < lim) {
            T v1 = get(i);
            T v2 = other.get(i);
            int res = v1.compareTo(v2);
            if (res != 0) {
                return res;
            }
            i++;
        }
        return size() - other.size();
    }
}
