package homework.hw07;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Hw07: a generic abstract ordered list.
 *
 * @author Kirill Smirenko, group 271
 */
public abstract class AbstractOrderedList<T extends Comparable<? super T>>
        implements Comparable<AbstractOrderedList<? extends T>>, Iterable<T> {
    public abstract void add(T value);

    public abstract T get(int index);

    public abstract void removeAt(int index);

    public abstract int size();

    public int compareTo(@NotNull AbstractOrderedList<? extends T> other) {
        // compares lexicographically
        Iterator<? extends T> iterThis  = iterator();
        Iterator<? extends T> iterOther = other.iterator();
        while (iterThis.hasNext() && iterOther.hasNext()) {
            int res = iterThis.next().compareTo(iterOther.next());
            if (res != 0) {
                return res;
            }
        }
        return size() - other.size();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AbstractOrderedList)) {
            return false;
        }
        AbstractOrderedList otherList = (AbstractOrderedList) other;
        if (otherList.size() != this.size()) {
            return false;
        }
        Iterator iterThis  = iterator();
        Iterator iterOther = ((AbstractOrderedList) other).iterator();
        while (iterThis.hasNext()) {
            if (iterThis.next() != iterOther.next()) {
                return false;
            }
        }
        return true;
    }
}
