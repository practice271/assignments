package hw07;

public interface IOrderedList<A extends Comparable<? super A>> extends Iterable<A> {
    void add(A newElem);

    A getByIndex(int index);

    boolean removeAt(int index);

    int getLength();

    int compareTo(IOrderedList<? extends A> other);

    A[] toArray();
}