package homework.hw07.javaimp;

import org.jetbrains.annotations.NotNull;

/**
 * Hw07: a generic abstract ordered list.
 *
 * @author Kirill Smirenko, group 271
 */
public abstract class AbstractOrderedList<T extends Comparable<? super T>>
        implements Comparable<AbstractOrderedList<? extends T>> {
    public abstract void add(T value);

    public abstract T get(int index);

    public abstract void removeAt(int index);

    public abstract int size();

    public int compareTo(@NotNull AbstractOrderedList<? extends T> other) {
        // compares lexicographically
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
