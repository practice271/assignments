package hw07;

public interface IOrderedList<A extends Comparable<? super A>> {
    void add(A newElem);

    A getByIndex(int index);

    boolean removeAt(int index);

    int getLength();

    int compareTo(IOrderedList<? extends A> other);

    boolean equals(Object other);

    int hashCode();

    A[] toArray();
}
