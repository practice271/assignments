package hw07;

public abstract class OrderedList <A extends Comparable<? super A>>
        implements Comparable<OrderedList<? extends A>> {
    abstract public A getVal(int index);
    abstract public void addVal(A val);
    abstract public void delVal(int index);

    abstract public int getSize();
}
