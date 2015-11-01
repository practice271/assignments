package homeworks.hw07;

public abstract class JavaOrderedList<T extends Comparable<? super T>>
                implements Comparable<JavaOrderedList<? extends T>> {
    abstract public T get(int index);
    abstract public int size();

    abstract public void add(T value);
    abstract public void removeAt(int index);

    abstract public int hashCode();
    abstract public boolean equals(Object other);

    public int compareTo(JavaOrderedList<? extends T> other) {
        int len = Math.min(size(), other.size());
        for (int i = 0; i < len; i++) {
            T object1 = get(i);
            T object2 = other.get(i);
            int result = object1.compareTo(object2);
            if (result != 0) {
                return result;
            }
        }
        return size() - other.size();
    }
}
