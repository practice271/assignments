package homework.hw07;

/**
 * Hw07: a generic abstract ordered list.
 *
 * @author Kirill Smirenko, group 271
 */
public abstract class IOrderedList<T extends Comparable<? super T>>
        implements Comparable<IOrderedList<? extends T>> {
    public abstract void add(T value);

    public abstract T get(int index);

    public abstract void removeAt(int index);

    public abstract int size();
}
