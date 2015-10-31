package hw07;

public abstract class OrderedList <A extends Comparable<? super A>>
        implements Comparable<OrderedList<? extends A>> {
    abstract public A getVal(int index);
    abstract public void addVal(A val);
    abstract public void delVal(int index);

    abstract public int getSize();

    abstract public int hashCode();
    abstract public boolean equals(Object other);
    abstract public int compareTo (OrderedList<? extends A> other);
}
