package hw07;

import java.util.Iterator;

public abstract class JavaOrderedList<A extends Comparable<? super A>>
        implements Comparable<IOrderedList<? extends A>>, IOrderedList<A> {

    protected int length = 0;

    abstract public void add(A newElem);

    abstract public A getByIndex(int index);

    abstract public boolean removeAt(int index);

    public int getLength() {
        return length;
    }

    public int compareTo(IOrderedList<? extends A> other) {
        int thisLength = getLength();
        int otherLength = other.getLength();
        int minLength = Math.min(thisLength, otherLength);
        Iterator<A> thisIterator = iterator();
        Iterator<A> otherIterator = (Iterator<A>) other.iterator();
        for (int i = 0; i < minLength; i++) {
            int compare = thisIterator.next().compareTo(otherIterator.next());
            if (compare != 0) return compare;
        }
        if (thisLength > otherLength) return -1;
        if (otherLength > thisLength) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IOrderedList))
            return false;
        IOrderedList otherList = (IOrderedList) other;
        if (getLength() != otherList.getLength()) return false;
        Iterator<A> thisIterator = iterator();
        Iterator<A> otherIterator = otherList.iterator();
        while (thisIterator.hasNext())
            if (!thisIterator.next().equals(otherIterator.next())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        Iterator<A> iterator = iterator();
        while (iterator.hasNext())
            hash = hash * 31 + iterator.next().hashCode();
        return hash;
    }

    public A[] toArray() {
        A[] newArray = (A[]) new Comparable[getLength()];
        Iterator<A> iterator = iterator();
        for (int index = 0; index < getLength(); index++)
            newArray[index] = iterator.next();
        return newArray;
    }
}